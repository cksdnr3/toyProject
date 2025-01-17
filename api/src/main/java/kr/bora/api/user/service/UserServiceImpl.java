package kr.bora.api.user.service;

import kr.bora.api.common.response.CommonResponse;
import kr.bora.api.common.response.Status;
import kr.bora.api.todo.repository.TodoRepository;
import kr.bora.api.user.domain.User;
import kr.bora.api.user.dto.UserRequestDto;
import kr.bora.api.user.dto.UserResponseDto;
import kr.bora.api.user.repository.UserRepository;
import kr.bora.api.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final TodoRepository todoRepository;

    public UserResponseDto getUserInfo(String email) {
        return repository.findByusername(email)
                .map(UserResponseDto::of)
                .orElseThrow(() -> new RuntimeException("사용자 정보가 없습니다."));
    }

    public UserResponseDto getMyInfo() {
        return repository.findById(SecurityUtil.getCurrentUserId())
                .map(UserResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    public UserRequestDto modify(UserRequestDto userRequestDto) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = repository.getById(userRequestDto.getUserId());

        user.changePassword(passwordEncoder.encode(userRequestDto.getPassword()));

        user.changeNickname(userRequestDto.getNickName());

        repository.save(user);

        UserRequestDto dtoEntity = entityDto(user, passwordEncoder);

        return dtoEntity;
    }
    public void deleteUserRelate(UserRequestDto dto){
        User user = dto.toUserEntity(dto);
        todoRepository.deleteTodoUserId(user.getUserId());
    }

    public CommonResponse<UserResponseDto> deleteUser(UserRequestDto dto) {
        User user = dto.toUserEntity(dto);
        deleteUserRelate(dto);
        try {
            repository.deleteById(user.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResponse.fail(Status.JPACONDUCTERROR);
        }
        return CommonResponse.success();
    }


}
package kr.bora.api.todo.dto;

import kr.bora.api.user.dto.UserRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Getter
@NoArgsConstructor
@Log4j2
public class TodoDto {

    private Long todoId;

    private UserRequestDto userId;

    private String title;

    private String start;

    private String end;

    private String description;

    private String viewer;

    private int priority;

    private UserRequestDto username;


    private LocalDateTime regDate;
    private LocalDateTime modDate;

    @Builder
    public TodoDto(Long todoId, UserRequestDto userId, String title, String start, String end, String description, String viewer, int priority, UserRequestDto username, LocalDateTime regDate, LocalDateTime modDate) {
        this.todoId = todoId;
        this.userId = userId;
        this.title = title;
        this.start = start;
        this.end = end;
        this.description = description;
        this.viewer = viewer;
        this.priority = priority;
        this.username = username;
        this.regDate = regDate;
        this.modDate = modDate;
    }

}

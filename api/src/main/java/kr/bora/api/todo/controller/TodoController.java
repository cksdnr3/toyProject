package kr.bora.api.todo.controller;

//import kr.bora.api.common.response.CommonResponse;
//import kr.bora.api.todo.domain.Todo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.bora.api.todo.dto.PageRequestDto;
import kr.bora.api.todo.dto.PageResultDto;
import kr.bora.api.todo.dto.TodoDto;
import kr.bora.api.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
@Api(tags={"2. Todo"})
@RestController
@Log4j2
@RequestMapping("/todos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TodoController {

    private final TodoService service;

    @ApiOperation(value="Todo 리스트", notes="Todo 리스트를 보여줍니다.")
    @GetMapping("/list/pages")
    public ResponseEntity<PageResultDto<TodoDto, Object[]>> todoList(PageRequestDto pageRequestDto) {
        return ResponseEntity.ok(service.getList(pageRequestDto));
    }

    @ApiOperation(value="Todo 등록", notes="Todo 리스트를 등록합니다.")
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> todoSave(@RequestBody TodoRequestCommand.TodoRequest todoDto) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("Save Success Todo", todoDto.toDto().getUserId().getUserId() + "번 유저가 todo를 등록");
        service.save(todoDto.toDto());
        return ResponseEntity.ok(obj);
    }

    @ApiOperation(value="Todo 확인", notes="Todo를 확인합니다.")
    @GetMapping("/read/{todoId}")
    public ResponseEntity<TodoDto> todoRead(@ApiParam(value="Todo 번호", required=true) @PathVariable("todoId") Long todoId) {

        return ResponseEntity.ok(service.get(todoId));
    }

    @ApiOperation(value="Todo 변경", notes=" Todo를 변경합니다.")
    @PutMapping("/modify/{todoId}")
    public ResponseEntity<String> todoModify(@ApiParam(value="Todo 번호", required=true) @PathVariable("todoId") Long todoId, @RequestBody TodoDto todoDto) {

        service.modify(todoId, todoDto);

        return ResponseEntity.ok(todoId + "번 TODO가 수정되었습니다.");
    }

    @ApiOperation(value="Todo 삭제", notes="Todo를 삭제합니다.")
    @DeleteMapping("/remove/{todoId}")
    public ResponseEntity<Map<String, Object>> todoRemove(@ApiParam(value="Todo 번호", required=true) @PathVariable("todoId") Long todoId) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("Result", todoId + " 번 Todo 삭제");
        service.todoRemove(todoId);

        return ResponseEntity.ok(resultMap);
    }

}

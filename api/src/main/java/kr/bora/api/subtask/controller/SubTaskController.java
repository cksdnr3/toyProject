package kr.bora.api.subtask.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import kr.bora.api.subtask.dto.SubTaskDto;
import kr.bora.api.subtask.service.SubTaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(tags={"3. SubTask"})
@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/subtasks")
public class SubTaskController {

    private final SubTaskService service;

    @ApiOperation(value="SubTask 등록", notes="SubTask를 등록 합니다.")
    @PostMapping("/save/{todoId}")
    public ResponseEntity<Map<String, Object>> subTaskSave(@RequestBody SubTaskRequestCommand.SubTaskRequest subTaskDto, @ApiParam(value="Todo 번호", required=true) @PathVariable Long todoId) {

        Map<String, Object> result = new HashMap<>();
        result.put("Save Success SubTask", subTaskDto.toDto(todoId).getTodoId() + "번 Todo의 Subtask가 등록되었습니다.");
        service.save(subTaskDto.toDto(todoId), todoId);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value="SubTask 목록", notes="SubTask 목록을 보여줍니다.")
    @GetMapping("/list/{todoId}")
    public ResponseEntity<List<SubTaskDto>> subTaskList(@ApiParam(value="Todo 번호", required=true) @PathVariable("todoId") Long todoId) {

        return ResponseEntity.ok(service.getList(todoId));
    }

    @ApiOperation(value="SubTask 변경", notes="SubTask를 변경 합니다.")
    @PutMapping("/modify/{subTaskId}")
    public ResponseEntity<String> subTaskModify(@ApiParam(value="SubTask 번호", required=true) @PathVariable("subTaskId") Long subTaskId, @RequestBody SubTaskDto subTaskDto) {

        service.modify(subTaskId, subTaskDto);

        return ResponseEntity.ok(subTaskId + "번 SubTask가 수정되었습니다.");
    }

    @ApiOperation(value="SubTask 삭제", notes="SubTask를 삭제 합니다.")
    @DeleteMapping("/remove/{subTaskId}")
    public ResponseEntity<String> subTaskRemove(@ApiParam(value="SubTask 번호", required=true) @PathVariable("subTaskId") Long subTaskId) {
        service.remove(subTaskId);

        return ResponseEntity.ok(subTaskId + "번 SubTask가 성공적으로 삭제되었습니다.");
    }
}

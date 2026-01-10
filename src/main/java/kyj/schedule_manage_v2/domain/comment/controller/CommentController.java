package kyj.schedule_manage_v2.domain.comment.controller;

import kyj.schedule_manage_v2.common.annotation.LoginSessionCheck;
import kyj.schedule_manage_v2.domain.comment.dto.CommentCreateRequest;
import kyj.schedule_manage_v2.domain.comment.dto.CommentCreateResponse;
import kyj.schedule_manage_v2.domain.comment.dto.SearchCommentResponse;
import kyj.schedule_manage_v2.domain.comment.service.CommentService;
import kyj.schedule_manage_v2.domain.user.dto.LoginSessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kyj.schedule_manage_v2.common.util.Constants.LOGIN_SESSION_NAME;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/schedules/{schedule_id}/comments")
    @LoginSessionCheck
    public ResponseEntity<CommentCreateResponse> createComment(
            @PathVariable(name = "schedule_id") Long scheduleId
            , @RequestBody CommentCreateRequest request
            , @SessionAttribute(name = LOGIN_SESSION_NAME) LoginSessionData loginSessionData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.saveComment(scheduleId, request, loginSessionData));
    }

    @GetMapping("/api/schedules/{schedule_id}/comments/{comment_id}")
    public ResponseEntity<SearchCommentResponse> getComment(
            @PathVariable(name = "schedule_id") Long scheduleId
            , @PathVariable(name = "comment_id") Long commentId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComment(scheduleId, commentId));
    }

    @GetMapping("/api/schedules/{schedule_id}/comments")
    public ResponseEntity<List<SearchCommentResponse>> getAllComment(
            @PathVariable(name = "schedule_id") Long scheduleId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllComment(scheduleId));
    }
}

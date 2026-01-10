package kyj.schedule_manage_v2.domain.comment.service;

import kyj.schedule_manage_v2.common.exception.NotFoundDataErrorException;
import kyj.schedule_manage_v2.domain.comment.dto.CommentCreateRequest;
import kyj.schedule_manage_v2.domain.comment.dto.CommentCreateResponse;
import kyj.schedule_manage_v2.domain.comment.dto.SearchCommentResponse;
import kyj.schedule_manage_v2.domain.comment.entity.Comment;
import kyj.schedule_manage_v2.domain.comment.repository.CommentRepository;
import kyj.schedule_manage_v2.domain.schedule.entity.Schedule;
import kyj.schedule_manage_v2.domain.schedule.repository.ScheduleRepository;
import kyj.schedule_manage_v2.domain.user.dto.LoginSessionData;
import kyj.schedule_manage_v2.domain.user.entity.User;
import kyj.schedule_manage_v2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CommentCreateResponse saveComment(Long scheduleId, CommentCreateRequest request, LoginSessionData loginSessionData) {
        User user = userRepository.findById(loginSessionData.id()).orElseThrow(() -> new NotFoundDataErrorException("없는 유저 입니다"));
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new NotFoundDataErrorException("없는 일정 입니다"));

        Comment comment = new Comment(request.content(), user, schedule);
        Comment saveComment = commentRepository.save(comment);

        return CommentCreateResponse
                .builder()
                .id(saveComment.getId())
                .content(saveComment.getContent())
                .scheduleId(saveComment.getSchedule().getId())
                .userId(saveComment.getUser().getId())
                .createdAt(saveComment.getCreateAt())
                .updatedAt(saveComment.getUpdateAt())
                .build();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public SearchCommentResponse getComment(Long scheduleId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NotFoundDataErrorException("없는 댓글 입니다"));

        if(!comment.getSchedule().getId().equals(scheduleId))
            throw new NotFoundDataErrorException("해당 일정에 댓글이 없습니다");

        return SearchCommentResponse
                .builder()
                .id(comment.getId())
                .content(comment.getContent())
                .scheduleId(comment.getSchedule().getId())
                .userId(comment.getUser().getId())
                .createdAt(comment.getCreateAt())
                .updatedAt(comment.getUpdateAt())
                .build();
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<SearchCommentResponse> getAllComment(Long scheduleId) {
        return commentRepository.findCommentsByScheduleId(scheduleId)
                .stream().map(comment -> SearchCommentResponse
                        .builder()
                        .id(comment.getId())
                        .content(comment.getContent())
                        .scheduleId(comment.getSchedule().getId())
                        .userId(comment.getUser().getId())
                        .createdAt(comment.getCreateAt())
                        .updatedAt(comment.getUpdateAt())
                        .build()
                ).toList();
    }
}

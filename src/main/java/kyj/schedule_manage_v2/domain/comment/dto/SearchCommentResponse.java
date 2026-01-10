package kyj.schedule_manage_v2.domain.comment.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record SearchCommentResponse(
        Long id
        , String content
        , Long scheduleId
        , Long userId
        , LocalDateTime createdAt
        , LocalDateTime updatedAt
) {
}

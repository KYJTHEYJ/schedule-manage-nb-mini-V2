package kyj.schedule_manage_v2.domain.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CommentCreateRequest(
        @NotBlank(message = "댓글은 비어있을 수 없습니다")
        @Size(min = 1, max = 200, message = "댓글은 1자 이상, 200자 이하로 입력해주세요")
        String content
) {
}

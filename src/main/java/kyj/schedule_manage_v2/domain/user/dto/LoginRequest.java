package kyj.schedule_manage_v2.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequest(
        @NotBlank(message = "로그인 이메일이 비어있습니다")
        String email
        , @NotBlank(message = "로그인 비밀번호가 비어있습니다")
        String password) {
}

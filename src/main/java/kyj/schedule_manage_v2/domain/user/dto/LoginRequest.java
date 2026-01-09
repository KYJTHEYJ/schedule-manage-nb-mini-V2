package kyj.schedule_manage_v2.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginRequest(
        @NotBlank String email
        , @NotBlank String password) {
}

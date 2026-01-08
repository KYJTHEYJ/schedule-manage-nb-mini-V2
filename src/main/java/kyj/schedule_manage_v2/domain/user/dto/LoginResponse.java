package kyj.schedule_manage_v2.domain.user.dto;

import lombok.Builder;

@Builder
public record LoginResponse(Long id, String email, String userName) {
}

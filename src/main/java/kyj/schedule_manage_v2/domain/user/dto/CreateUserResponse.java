package kyj.schedule_manage_v2.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CreateUserResponse {
    private Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public CreateUserResponse(Long id, String userName, String email, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}

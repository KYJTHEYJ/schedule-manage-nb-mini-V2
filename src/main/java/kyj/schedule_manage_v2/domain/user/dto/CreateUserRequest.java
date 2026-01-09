package kyj.schedule_manage_v2.domain.user.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequest {
    @Size(min = 2, max = 20)
    private String userName;
    @Email
    private String email;
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&^])[A-Za-z\\d@$!%*?&^]{8,20}$")
    private String password;

    public CreateUserRequest(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}

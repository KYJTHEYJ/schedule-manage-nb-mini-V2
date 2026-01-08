package kyj.schedule_manage_v2.domain.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequest {
    private String userName;
    private String email;
    private String password;

    public CreateUserRequest(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}

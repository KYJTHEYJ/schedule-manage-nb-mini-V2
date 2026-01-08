package kyj.schedule_manage_v2.domain.user.dto;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
    private String email;
    private String userName;
    private String password;
}

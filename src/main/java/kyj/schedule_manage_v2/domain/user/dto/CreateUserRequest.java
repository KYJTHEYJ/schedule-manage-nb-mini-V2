package kyj.schedule_manage_v2.domain.user.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateUserRequest {
    @NotBlank(message = "이름은 비어 있을 수 없습니다")
    @Size(min = 2, max = 20, message = "이름은 2자 이상, 20자 이하로 입력해주세요")
    private String userName;
    @NotBlank(message = "이메일은 비어 있을 수 없습니다")
    @Email(message = "이메일 양식이 아닙니다")
    private String email;
    @NotBlank(message = "비밀번호는 비어 있을 수 없습니다")
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상, 20자 이하로 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&^])[A-Za-z\\d@$!%*?&^]{8,20}$"
            , message = "비밀번호는 @$!%*?&^ 중 하나의 특수문자, 영문 소문자와 대문자, 숫자가 한 글자씩 이상 포함되어야 합니다")
    private String password;

    public CreateUserRequest(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}

package kyj.schedule_manage_v2.domain.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateUserRequest {
    @Email(message = "이메일 양식이 아닙니다")
    private String email;
    @Size(min = 2, max = 20, message = "이름은 2자 이상, 20자 이하로 입력해주세요")
    private String userName;
    @Size(min = 8, max = 20, message = "비밀번호는 8자 이상, 20자 이하로 입력해주세요")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&^])[A-Za-z\\d@$!%*?&^]{8,20}$"
            , message = "비밀번호는 @$!%*?&^ 중 하나의 특수문자, 영문 소문자와 대문자, 숫자가 한 글자씩 이상 포함되어야 합니다")
    private String password;
}

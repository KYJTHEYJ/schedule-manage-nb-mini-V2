package kyj.schedule_manage_v2.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
public class UpdateScheduleRequest {
    @NotBlank(message = "제목은 비어 있을 수 없습니다")
    @Length(min = 1, max = 100, message = "제목은 100자 이하로 입력해주세요")
    private String title;
    @NotNull(message = "등록될 내용이 없습니다")
    private String content;
}

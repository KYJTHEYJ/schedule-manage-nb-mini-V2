package kyj.schedule_manage_v2.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateScheduleRequest {
    @NotBlank
    private Long userId;
    @NotBlank
    private String title;
    @NotNull
    private String content;

    public CreateScheduleRequest(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}

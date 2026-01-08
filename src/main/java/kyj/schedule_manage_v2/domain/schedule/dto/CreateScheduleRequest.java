package kyj.schedule_manage_v2.domain.schedule.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateScheduleRequest {
    private Long userId;
    private String title;
    private String content;

    public CreateScheduleRequest(Long userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
}

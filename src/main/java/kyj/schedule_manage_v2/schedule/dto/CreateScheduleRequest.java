package kyj.schedule_manage_v2.schedule.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateScheduleRequest {
    private String userName;
    private String title;
    private String content;

    public CreateScheduleRequest(String userName, String title, String content) {
        this.userName = userName;
        this.title = title;
        this.content = content;
    }
}

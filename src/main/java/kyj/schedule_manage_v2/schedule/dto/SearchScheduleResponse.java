package kyj.schedule_manage_v2.schedule.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SearchScheduleResponse {
    private final Long id;
    private final String userName;
    private final String title;
    private final String content;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public SearchScheduleResponse(Long id, String userName, String title, String content, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}

package kyj.schedule_manage_v2.domain.schedule.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SearchScheduleResponse {
    private final Long id;
    private final Long userId;
    private final String userName;
    private final String title;
    private final String content;
    private final Integer commentCount;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;

    public SearchScheduleResponse(Long id, Long userId, String userName, String title, String content, Integer commentCount, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}

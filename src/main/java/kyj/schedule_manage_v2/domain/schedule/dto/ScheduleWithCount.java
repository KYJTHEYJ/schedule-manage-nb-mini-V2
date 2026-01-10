package kyj.schedule_manage_v2.domain.schedule.dto;

import kyj.schedule_manage_v2.domain.schedule.entity.Schedule;

public interface ScheduleWithCount {
    Schedule getSchedule();
    String getUserName();
    Long getCommentCount();
}

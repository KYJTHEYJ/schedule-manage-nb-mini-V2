package kyj.schedule_manage_v2.domain.schedule.repository;

import kyj.schedule_manage_v2.domain.schedule.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}

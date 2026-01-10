package kyj.schedule_manage_v2.domain.schedule.repository;

import kyj.schedule_manage_v2.domain.schedule.dto.ScheduleWithCount;
import kyj.schedule_manage_v2.domain.schedule.dto.SearchScheduleResponse;
import kyj.schedule_manage_v2.domain.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("""
           SELECT s as schedule
                  , MAX(u.userName) as userName
                  , COUNT(c) as commentCount
           FROM Schedule s
           LEFT JOIN Comment c
           ON s.id = c.schedule.id
           INNER JOIN User u
           ON s.user.id = u.id
           GROUP BY s
           """)
    Page<ScheduleWithCount> findAllWithCount(Pageable pageable);

    @Query("""
           SELECT s as schedule
                  , MAX(u.userName) as userName
                  , COUNT(c) as commentCount
           FROM Schedule s
           LEFT JOIN Comment c
           ON s.id = c.schedule.id
           INNER JOIN User u
           ON s.user.id = u.id
           WHERE s.id = :schedule_id
           GROUP BY s
           """)
    Optional<ScheduleWithCount> findByIdWithCount(@Param("schedule_id") Long scheduleId);
}

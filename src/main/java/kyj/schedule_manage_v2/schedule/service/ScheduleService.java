package kyj.schedule_manage_v2.schedule.service;

import kyj.schedule_manage_v2.entity.Schedule;
import kyj.schedule_manage_v2.schedule.dto.*;
import kyj.schedule_manage_v2.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(request.getUserName(), request.getTitle(), request.getContent());
        Schedule saveSchedule = scheduleRepository.save(schedule);
        return CreateScheduleResponse
                .builder()
                .id(saveSchedule.getId())
                .userName(saveSchedule.getUserName())
                .title(saveSchedule.getTitle())
                .content(saveSchedule.getContent())
                .createAt(saveSchedule.getCreateAt())
                .updateAt(saveSchedule.getUpdateAt())
                .build();
    }

    public SearchScheduleResponse getSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalStateException("없는 일정 입니다"));
        return SearchScheduleResponse
                .builder()
                .id(schedule.getId())
                .userName(schedule.getUserName())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .createAt(schedule.getCreateAt())
                .updateAt(schedule.getUpdateAt())
                .build();
    }

    public List<SearchScheduleResponse> getAllSchedule() {
        return scheduleRepository.findAll()
                .stream()
                .map(schedule ->
                    SearchScheduleResponse
                            .builder()
                            .id(schedule.getId())
                            .userName(schedule.getUserName())
                            .title(schedule.getTitle())
                            .content(schedule.getContent())
                            .createAt(schedule.getCreateAt())
                            .updateAt(schedule.getUpdateAt())
                            .build())
                .toList();
    }

    public UpdateScheduleResponse updateSchedule(Long scheduleId, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new IllegalStateException("없는 일정 입니다"));
        schedule.update(request.getTitle(), request.getContent());
        return UpdateScheduleResponse
                .builder()
                .id(schedule.getId())
                .title(schedule.getTitle())
                .content(schedule.getContent())
                .userName(schedule.getUserName())
                .createAt(schedule.getCreateAt())
                .updateAt(schedule.getUpdateAt())
                .build();
    }

    public void deleteSchedule(Long scheduleId) {
        if(!scheduleRepository.existsById(scheduleId)) {
            throw new IllegalStateException("없는 일정 입니다");
        }

        scheduleRepository.deleteById(scheduleId);
    }
}

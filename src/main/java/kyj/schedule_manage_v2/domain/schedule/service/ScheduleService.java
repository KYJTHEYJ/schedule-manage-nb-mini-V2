package kyj.schedule_manage_v2.domain.schedule.service;

import kyj.schedule_manage_v2.domain.schedule.dto.*;
import kyj.schedule_manage_v2.domain.schedule.entity.Schedule;
import kyj.schedule_manage_v2.domain.user.entity.User;
import kyj.schedule_manage_v2.domain.schedule.repository.ScheduleRepository;
import kyj.schedule_manage_v2.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public CreateScheduleResponse saveSchedule(CreateScheduleRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new IllegalStateException("없는 유저 입니다"));

        Schedule schedule = new Schedule(user, request.getTitle(), request.getContent());
        Schedule saveSchedule = scheduleRepository.save(schedule);

        return CreateScheduleResponse
                .builder()
                .id(saveSchedule.getId())
                .userId(saveSchedule.getUser().getId())
                .userName(saveSchedule.getUser().getUserName())
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
                .userId(schedule.getUser().getId())
                .userName(schedule.getUser().getUserName())
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
                            .userId(schedule.getUser().getId())
                            .userName(schedule.getUser().getUserName())
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
                .userId(schedule.getUser().getId())
                .userName(schedule.getUser().getUserName())
                .title(schedule.getTitle())
                .content(schedule.getContent())
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

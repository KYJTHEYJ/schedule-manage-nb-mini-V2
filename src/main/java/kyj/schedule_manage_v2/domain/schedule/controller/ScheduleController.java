package kyj.schedule_manage_v2.domain.schedule.controller;

import jakarta.validation.Valid;
import kyj.schedule_manage_v2.common.annotation.LoginSessionCheck;
import kyj.schedule_manage_v2.domain.schedule.dto.*;
import kyj.schedule_manage_v2.domain.schedule.service.ScheduleService;
import kyj.schedule_manage_v2.domain.user.dto.LoginSessionData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kyj.schedule_manage_v2.common.util.Constants.LOGIN_SESSION_NAME;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/api/schedules")
    @LoginSessionCheck
    public ResponseEntity<CreateScheduleResponse> saveSchedule(@Valid @RequestBody CreateScheduleRequest request
            , @SessionAttribute(name = LOGIN_SESSION_NAME) LoginSessionData loginSessionData) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.saveSchedule(request, loginSessionData));
    }

    @GetMapping("/api/schedules/{schedule_id}")
    public ResponseEntity<SearchScheduleResponse> getSchedule(@PathVariable(name = "schedule_id") Long scheduleId) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getSchedule(scheduleId));

    }

    @GetMapping("/api/schedules")
    public ResponseEntity<List<SearchScheduleResponse>> getAllSchedule() {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.getAllSchedule());
    }

    @PutMapping("/api/schedules/{schedule_id}")
    @LoginSessionCheck
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@PathVariable(name = "schedule_id") Long scheduleId
            , @Valid @RequestBody UpdateScheduleRequest request
            , @SessionAttribute(name = LOGIN_SESSION_NAME) LoginSessionData loginSessionData) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(scheduleId, request, loginSessionData));

    }

    @DeleteMapping("/api/schedules/{schedule_id}")
    @LoginSessionCheck
    public ResponseEntity<Void> deleteSchedule(@PathVariable(name = "schedule_id") Long scheduleId
            , @SessionAttribute(name = LOGIN_SESSION_NAME) LoginSessionData loginSessionData) {
        scheduleService.deleteSchedule(scheduleId, loginSessionData);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}




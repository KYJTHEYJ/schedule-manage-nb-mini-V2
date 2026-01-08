package kyj.schedule_manage_v2.schedule.controller;

import kyj.schedule_manage_v2.schedule.dto.*;
import kyj.schedule_manage_v2.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/api/schedules")
    public ResponseEntity<CreateScheduleResponse> saveSchedule(@RequestBody CreateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleService.save(request));
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
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(@PathVariable(name = "schedule_id") Long scheduleId, @RequestBody UpdateScheduleRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(scheduleService.updateSchedule(scheduleId, request));

    }

    @DeleteMapping("/api/schedules/{schedule_id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable(name = "schedule_id") Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}




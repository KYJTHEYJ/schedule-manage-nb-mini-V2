package kyj.schedule_manage_v2.domain.schedule.controller;

import kyj.schedule_manage_v2.domain.schedule.dto.CreateScheduleRequest;
import kyj.schedule_manage_v2.domain.schedule.dto.CreateScheduleResponse;
import kyj.schedule_manage_v2.domain.schedule.service.ScheduleService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ScheduleController.class)
@ExtendWith(MockitoExtension.class)
class ScheduleControllerTest {
    @MockitoBean
    private ScheduleService scheduleService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("일정 저장 테스트")
    void saveSchedule() throws Exception {
        // 테스트 안, 테스트 결과 작성
        CreateScheduleRequest request = CreateScheduleRequest.builder()
                .userId(1L)
                .title("TEST")
                .content("testing")
                .build();

        CreateScheduleResponse response = CreateScheduleResponse.builder()
                .id(1L)
                .userId(1L)
                .title("TEST")
                .content("testing")
                .userName("tester")
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        // given (준비)
        // request가 비어있을 텐데, any()으로 채워줌
        // request 위에서 만든 사항을 전달할 수 없는게, service 단 흐름 처리 중에 객체의 주소값이 달라져서
        // content로 넘겨주는게 불가능함, null 발생
        //given(scheduleService.saveSchedule(any())).willReturn(response);

        // when (실행)
        ResultActions resultActions = mockMvc.perform(post("/api/schedules")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then (결과)
        resultActions.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(response.getId()))
                .andExpect(jsonPath("$.user_id").value(response.getUserName()))
                .andExpect(jsonPath("$.title").value(response.getTitle()))
                .andExpect(jsonPath("$.content").value(response.getContent()))
                .andExpect(jsonPath("$.createAt").exists())
                .andExpect(jsonPath("$.updateAt").exists());
    }


    @Test
    void getSchedule() {
    }

    @Test
    void getAllSchedule() {
    }

    @Test
    void updateSchedule() {
    }

    @Test
    void deleteSchedule() {
    }
}
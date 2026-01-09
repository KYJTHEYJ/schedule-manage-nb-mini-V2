package kyj.schedule_manage_v2.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Auditing 분리 이유
// 이게 어플리케이션에 설정하니 컨트롤러 테스트 코드를 작성 해보려하니..
// 컨트롤러 관련 설정부만 가져와서 어플리케이션에 선언된 부분은 가져오질 못함
// 그래서 따로 Configuration 분리

@Configuration
@EnableJpaAuditing
public class JPAAuditingConfig {
}

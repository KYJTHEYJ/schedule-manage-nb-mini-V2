package kyj.schedule_manage_v2.common.aspect;

import jakarta.servlet.http.HttpSession;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static kyj.schedule_manage_v2.common.util.Constants.LOGIN_SESSION_NAME;

//TODO 추후 커스텀 익셉션으로 처리할 것
@Aspect
@Component
public class SessionCheck {
    @Before("@annotation(kyj.schedule_manage_v2.common.annotation.SessionCheck)")
    public void checkSession(JoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpSession currentSession = servletRequestAttributes.getRequest().getSession(false);

        if(currentSession == null || currentSession.getAttribute(LOGIN_SESSION_NAME) == null) {
            throw new IllegalStateException("로그인 되지 않은 상태의 접근입니다");
        }
    }
}

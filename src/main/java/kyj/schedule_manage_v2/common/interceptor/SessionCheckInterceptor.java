package kyj.schedule_manage_v2.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kyj.schedule_manage_v2.common.annotation.LoginSessionCheck;
import kyj.schedule_manage_v2.common.exception.UnAuthroizedAccessErrorException;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import static kyj.schedule_manage_v2.common.util.Constants.LOGIN_SESSION_NAME;

@Component
public class SessionCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        // 기타 URL도 처리되어 메서드를 못찾는다면 통과
        if(!(handler instanceof HandlerMethod)) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginSessionCheck loginSessionCheck = handlerMethod.getMethodAnnotation(LoginSessionCheck.class);

        if (loginSessionCheck == null) {
            return true;
        }

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(LOGIN_SESSION_NAME) == null) {
            throw new UnAuthroizedAccessErrorException("로그인 되지 않은 상태의 접근입니다");
        }

        return true;
    }
}

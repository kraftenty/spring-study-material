package nl.springstudy.cvs1.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import nl.springstudy.cvs1.web.SessionConst;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("LoginCheckInterceptor - call");

        HttpSession session = request.getSession();
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            // 실패 로직
            log.info("LoginCheckInterceptor - Not Logged in User request");
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }

        // 성공 로직
        return true;
    }
}

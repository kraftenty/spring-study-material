package nl.springstudy.cvs1.web.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.springstudy.cvs1.domain.login.LoginService;
import nl.springstudy.cvs1.domain.member.Member;
import nl.springstudy.cvs1.web.login.form.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(
            @Validated @ModelAttribute LoginForm form,
            BindingResult bindingResult,
            @RequestParam(defaultValue="/") String redirectURL, // LoginCheckInterceptor
            HttpServletRequest request
    ) {
        // 검증 실패한 경우
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getEmail(), form.getPassword());
        // 로그인 실패한 경우
        if (loginMember == null) {
            // Validation
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 로그인 성공 처리
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);
        log.info("login success! sessionid={}", session.getId());
        log.info("redirect:/" + redirectURL);
        return "redirect:" + redirectURL;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        log.info("logout called");
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션을 삭제하는 메서드
        }
        return "redirect:/";
    }
}

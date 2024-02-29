package nl.springstudy.cvs1.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import nl.springstudy.cvs1.domain.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class HomeController {

//    @GetMapping("/")
    public String home(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);

        //세션 자체가 없으면 home으로
        if (session == null) {
            return "home";
        }

        // 세션에서 Member 뽑아내보기
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션에 회원 데이터가 없으면 home으로
        if (loginMember == null) {
            return "home";
        }

        // 로그인 성공. loginHome으로
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeWithSpring(
        @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember,
        Model model
    ) {
        // 세션에 회원 데이터가 없으면 home으로
        if (loginMember == null) {
            return "home";
        }

        // 로그인 성공. loginHome으로
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}

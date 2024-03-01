package nl.springstudy.cvs1.web.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.springstudy.cvs1.domain.member.Member;
import nl.springstudy.cvs1.domain.member.MemberService;
import nl.springstudy.cvs1.web.SessionConst;
import nl.springstudy.cvs1.web.member.form.MemberJoinForm;
import nl.springstudy.cvs1.web.member.form.MemberUpdateForm;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("memberJoinForm") MemberJoinForm memberJoinForm) {
        return "member/memberAddForm";
    }

    //@Valid
    //bindingResult
    @PostMapping("/join")
    public String join(
            @Valid @ModelAttribute("memberJoinForm") MemberJoinForm memberJoinForm,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "member/memberAddForm";
        }
        Member joinMember = new Member();
        joinMember.setEmail(memberJoinForm.getEmail());
        joinMember.setPassword(memberJoinForm.getPassword());
        boolean isJoinSuccess = memberService.join(joinMember);
        if (!isJoinSuccess) {
            log.info("Member join failed! email is duplicated");
            // Validation
            bindingResult.rejectValue("email", "error.memberJoinForm", "Email already exists");
            return "member/memberAddForm";
        }
        log.info("Member joined! email={}, password={}", joinMember.getEmail(), joinMember.getPassword());
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateForm(
            @ModelAttribute("memberUpdateForm") MemberUpdateForm memberUpdateForm
    ) {
        return "member/memberUpdateForm";
    }

    @PostMapping("/update")
    public String update(
            @ModelAttribute("memberUpdateForm") MemberUpdateForm memberUpdateForm,
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Member findMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        findMember.setPassword(memberUpdateForm.getPassword());

        memberService.updateMemberPassword(findMember.getId(), findMember);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String delete(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member findMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        memberService.deleteMember(findMember.getId());
        return "redirect:/logout";
    }

}

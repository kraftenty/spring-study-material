package nl.springstudy.cvs1.web.member;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import nl.springstudy.cvs1.domain.member.Member;
import nl.springstudy.cvs1.domain.member.MemberService;
import nl.springstudy.cvs1.web.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String joinForm(@ModelAttribute("member") Member member) {
        return "members/addMemberForm";
    }

    //@Valid
    //bindingResult
    @PostMapping("/join")
    public String join(
            @Valid @ModelAttribute("member") Member member,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "members/addMemberForm";
        }
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String updateForm() {
        return "members/updateMemberForm";
    }

    @PostMapping("/update")
    public String update(@RequestParam String newPassword, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member findMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        findMember.setPassword(newPassword);

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

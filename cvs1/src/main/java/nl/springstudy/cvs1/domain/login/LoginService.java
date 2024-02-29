package nl.springstudy.cvs1.domain.login;

import lombok.RequiredArgsConstructor;
import nl.springstudy.cvs1.domain.member.Member;
import nl.springstudy.cvs1.domain.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String email, String password) {
        return memberRepository.findByEmail(email)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}

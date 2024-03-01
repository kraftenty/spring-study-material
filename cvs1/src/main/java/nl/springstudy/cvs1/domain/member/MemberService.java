package nl.springstudy.cvs1.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입 메서드
    public boolean join(Member member) {
        // 이메일 중복 검사
        if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
            // 중복된 경우
            return false;
        }
        // 성공한 경우
        memberRepository.save(member);
        return true;
    }

    // 회원 비밀번호 수정 메서드
    public void updateMemberPassword(Long currentId, Member updateParam) {
        memberRepository.update(currentId, updateParam);
    }

    // 회원 탈퇴 메서드
    public void deleteMember(Long id) {
        memberRepository.delete(id);
    }

}

package nl.springstudy.cvs1.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member join(Member member) {
        // 이메일 중복 검사
        if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
            return null;
        }
        return memberRepository.save(member);
    }

    public Member findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public List<Member> findAllMember() {
        return memberRepository.findAll();
    }

    public void updateMemberPassword(Long currentId, Member updateParam) {
        memberRepository.update(currentId, updateParam);
    }

    public void deleteMember(Long id) {
        memberRepository.delete(id);
    }

    public void clearMembers() {
        memberRepository.clear();
    }
}

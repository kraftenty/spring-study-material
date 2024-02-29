package nl.springstudy.cvs1.domain.member;

import java.util.Optional;
import java.util.List;

public interface MemberRepository {

    // 등록
    public Member save(Member member);

    // 조회 (1개, id로)
    public Member findById(Long id);

    // 조회 (1개, email로)
    public Optional<Member> findByEmail(String email);

    // 조회 (전체)
    public List<Member> findAll();

    // 수정
    public void update(Long currentId, Member updateParam);

    // 삭제
    public void delete(Long id);

    // 전체 삭제
    public void clear();
}

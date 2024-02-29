package nl.springstudy.cvs1.domain.member;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository // 자동 빈 등록
public class MemoryMemberRepository implements MemberRepository {

    // 메모리 DB
    private static final Map<Long, Member> memoryDB = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        memoryDB.put(member.getId(), member);
        return member;
    }

    @Override
    public Member findById(Long id) {
        return memoryDB.get(id);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return findAll().stream()
                .filter(m -> m.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memoryDB.values());
    }

    @Override
    public void update(Long currentId, Member updateParam) {
        findById(currentId).setPassword(updateParam.getPassword());
    }

    @Override
    public void delete(Long id) {
        memoryDB.remove(id);
    }

    @Override
    public void clear() {
        memoryDB.clear();
    }
}

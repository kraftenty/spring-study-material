package nl.springstudy.cvs1.domain.item;

import java.util.List;

public interface ItemRepository {

    // 등록
    public Item save(Item item);

    // 조회 (1개)
    public Item findById(Long id);

    // 조회 (전체)
    public List<Item> findAll();

    // 수정
    public void update(Long currentId, Item updateParam);

    // 삭제
    public void delete(Long id);

    // 전체 삭제
    public void clear();

}

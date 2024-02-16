package nl.springstudy.cvs1.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository // 자동 빈 등록
public class MemoryItemRepository implements ItemRepository{

    // 메모리 DB
    private static final Map<Long, Item> memoryDB = new HashMap<>();
    private static long sequence = 0L;

    // 등록
    @Override
    public Item save(Item item) {
        item.setId(++sequence);
        memoryDB.put(item.getId(), item);
        return item;
    }

    // 조회 (1개)
    @Override
    public Item findById(Long id) {
        return memoryDB.get(id);
    }

    // 조회 (전체)
    @Override
    public List<Item> findAll() {
        return new ArrayList<>(memoryDB.values());
    }

    // 수정
    @Override
    public void update(Long currentId, Item updateParam) {
        Item findItem = findById(currentId);
        findItem.setName(updateParam.getName());
        findItem.setPrice(updateParam.getPrice());
    }

    // 삭제
    @Override
    public void delete(Long id) {
        memoryDB.remove(id);
    }

    // 전체 삭제
    @Override
    public void clear() {
        memoryDB.clear();
    }

}
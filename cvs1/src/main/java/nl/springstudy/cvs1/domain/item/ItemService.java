package nl.springstudy.cvs1.domain.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 자동 빈 등록
@RequiredArgsConstructor// 생성자 주입을 위해
public class ItemService {

    private final ItemRepository itemRepository;

    public Item addItem(String name, Integer price) {
        return itemRepository.save(new Item(name, price));
    }

    public Item findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public void updateItem(Long id, String newName, Integer newPrice) {
        Item itemToUpdate = itemRepository.findById(id);
        if (itemToUpdate != null) {
            itemToUpdate.setName(newName);
            itemToUpdate.setPrice(newPrice);
            itemRepository.update(id, itemToUpdate);
        }
    }

    public void deleteItem(Long id) {
        itemRepository.delete(id);
    }

    public void clearItems() {
        itemRepository.clear();
    }
}

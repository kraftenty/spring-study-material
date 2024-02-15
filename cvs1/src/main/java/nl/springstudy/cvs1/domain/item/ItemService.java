package nl.springstudy.cvs1.domain.item;

import java.util.List;

public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item addItem(String name, Integer price) {
        return itemRepository.save(new Item(name, price));
    }

    public Item findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Item updateItem(Long id, String newName, Integer newPrice) {
        Item itemToUpdate = itemRepository.findById(id);
        if (itemToUpdate != null) {
            itemToUpdate.setName(newName);
            itemToUpdate.setPrice(newPrice);
            itemRepository.update(id, itemToUpdate);
            Item updatedItem = itemRepository.findById(id);
            return updatedItem;
        }
        return null;
    }

    public void deleteItem(Long id) {
        itemRepository.delete(id);
    }

    public void clearItems() {
        itemRepository.clear();
    }
}

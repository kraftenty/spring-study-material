package nl.springstudy.cvs1.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void itemCRUD() {
        // 아이템 등록
        Item savedItem = itemService.addItem("BananaMilk", 1700);
        Long savedItemId = savedItem.getId();

        // 아이템 조회
        Item findItem = itemService.findItemById(savedItemId);
        assertThat(findItem.getName()).isEqualTo(savedItem.getName());
        assertThat(findItem.getPrice()).isEqualTo(savedItem.getPrice());

        // 아이템 수정
        Item updatedItem = itemService.updateItem(findItem.getId(), "MelonMilk", 1900);
        Item findUpdatedItem = itemService.findItemById(updatedItem.getId());
        assertThat(findUpdatedItem.getName()).isEqualTo(updatedItem.getName());
        assertThat(findUpdatedItem.getPrice()).isEqualTo(updatedItem.getPrice());

        //아이템 삭제
        itemService.deleteItem(findUpdatedItem.getId());
        assertThat(itemService.findAllItems().size()).isEqualTo(0);
    }

}
package nl.springstudy.cvs1;

import nl.springstudy.cvs1.domain.item.ItemRepository;
import nl.springstudy.cvs1.domain.item.ItemService;
import nl.springstudy.cvs1.domain.item.MemoryItemRepository;
import nl.springstudy.cvs1.web.item.ItemController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

//    @Bean
//    public ItemController itemController() {
//        return new ItemController(itemService());
//    }

    @Bean
    public ItemService itemService() {
        return new ItemService(itemRepository());
    }


    @Bean
    public ItemRepository itemRepository() {
        return new MemoryItemRepository();
    }


}

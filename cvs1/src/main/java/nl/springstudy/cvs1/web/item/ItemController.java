package nl.springstudy.cvs1.web.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.springstudy.cvs1.domain.item.Item;
import nl.springstudy.cvs1.domain.item.ItemService;
import nl.springstudy.cvs1.web.item.form.ItemForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor // 생성자 주입을 위해
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public String list(Model model) {
        List<Item> items = itemService.findAllItems();
        model.addAttribute("items", items);
        return "item/itemList";
    }

    @GetMapping("/add")
    public String addForm() {
        return "addform";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ItemForm form) {
        itemService.addItem(form.getName(), form.getPrice());
        return "redirect:/item";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Item item = itemService.findItemById(id);
        model.addAttribute("item", item);
        return "item/itemDetail";
    }

    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Item item = itemService.findItemById(id);
        model.addAttribute("item", item);
        return "item/updateItemForm";
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, @ModelAttribute ItemForm form) {
        itemService.updateItem(id, form.getName(), form.getPrice());
        return "redirect:/item/{id}";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return "redirect:/item";
    }
}

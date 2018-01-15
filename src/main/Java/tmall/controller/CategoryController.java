package tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tmall.dao.Impl.CategoryDaoImpl;
import tmall.pojo.Category;

import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryDaoImpl categoryDaoImpl;

    @RequestMapping("/admin_category_list")
    public String list(Model model){
        List<Category> cs = categoryDaoImpl.list();
        model.addAttribute("cs",cs);
        return "include/admin/listCategory";
    }
}

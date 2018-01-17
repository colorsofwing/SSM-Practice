package tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tmall.dao.Impl.CategoryDaoImpl;
import tmall.pojo.Category;
import tmall.util.Page;

import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryDaoImpl categoryDaoImpl;

    @RequestMapping("/admin_category_list")
    public String list(Model model, Page page){
        List<Category> cs = categoryDaoImpl.list(page);
        int total = categoryDaoImpl.total();
        page.setTotal(total);
        model.addAttribute("cs",cs);
        model.addAttribute("page",page);
        return "admin/listCategory";
    }
}

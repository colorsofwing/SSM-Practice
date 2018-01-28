package tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tmall.pojo.Category;
import tmall.service.CategoryDaoImpl;
import tmall.service.ProductDaoImpl;

import java.util.List;

@Controller
@RequestMapping("")
public class ForeController {

    @Autowired
    private CategoryDaoImpl categoryDaoImpl;
    @Autowired
    private ProductDaoImpl productDaoImpl;

    @RequestMapping("forehome")
    public String home(Model model){
        List<Category> categories = categoryDaoImpl.list();
        productDaoImpl.fill(categories);
        productDaoImpl.fillByRow(categories);

        model.addAttribute("cs",categories);
        return "fore/home";
    }
}

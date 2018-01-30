package tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.HtmlUtils;
import tmall.pojo.Category;
import tmall.pojo.User;
import tmall.service.CategoryDaoImpl;
import tmall.service.ProductDaoImpl;
import tmall.service.UserDaoImpl;

import java.util.List;

@Controller
@RequestMapping("")
@SessionAttributes(value={"cs","contextPath"})
public class ForeController {

    @Autowired
    private CategoryDaoImpl categoryDaoImpl;
    @Autowired
    private ProductDaoImpl productDaoImpl;
    @Autowired
    private UserDaoImpl userDaoImpl;

    @RequestMapping("forehome")
    public String home(Model model){
        List<Category> categories = categoryDaoImpl.list();
        productDaoImpl.fill(categories);
        productDaoImpl.fillByRow(categories);

        model.addAttribute("cs",categories);
        return "fore/home";
    }

    @RequestMapping("foreregister")
    public String register(Model model, User user){
        String name = user.getName();
        //转义name，防止恶意注册。
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);
        boolean exist = userDaoImpl.isExist(name);

        if(exist==true){
            String msg = "用户名已经存在，请重新输入";
            model.addAttribute("msg",msg);
            model.addAttribute("user",null);
            return "fore/register";
        }
        userDaoImpl.add(user);

        return "fore/registerSuccess";
    }
}

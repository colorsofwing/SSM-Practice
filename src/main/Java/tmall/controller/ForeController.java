package tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.HtmlUtils;
import tmall.pojo.Category;
import tmall.pojo.User;
import tmall.service.CategoryDaoImpl;
import tmall.service.ProductDaoImpl;
import tmall.service.UserDaoImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("")

public class ForeController {

    @Autowired
    private CategoryDaoImpl categoryDaoImpl;
    @Autowired
    private ProductDaoImpl productDaoImpl;
    @Autowired
    private UserDaoImpl userDaoImpl;
    @Autowired
    private ServletContext servletContext;

    @RequestMapping("forehome")
    public String home(HttpSession session){
        List<Category> categories = categoryDaoImpl.list();
        productDaoImpl.fill(categories);
        productDaoImpl.fillByRow(categories);

        //设置ServletContext全局变量
        String contextPath = (String)servletContext.getInitParameter("contextPath");
        servletContext.setAttribute("contextPath",contextPath);

        session.setAttribute("cs",categories);
        return "fore/home";
    }

    @RequestMapping("foreregister")
    public String register(Model model, User user,HttpSession session){
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
        //防止跳过register.jsp，导致数据库出现空记录
        if(name==null){
            session.setAttribute("user",null);
            return "fore/register";
        }
        userDaoImpl.add(user);
        session.setAttribute("user",user);

        return "fore/registerSuccess";
    }

    @RequestMapping("forelogin")
    public String login(@RequestParam("name") String name, @RequestParam("password") String password, Model model, HttpSession session){
        name = HtmlUtils.htmlEscape(name);
        User user = userDaoImpl.getAccount(name,password);

        if(user==null){
            model.addAttribute("msg","账号密码错误");
            return "fore/login";
        }

        session.setAttribute("user",user);
        return "redirect:forehome";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:forehome";
    }
}

package tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import tmall.comparator.*;
import tmall.pojo.*;
import tmall.service.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Collections;
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
    @Autowired
    private ProductImageDaoImpl productImageDaoImpl;
    @Autowired
    private PropertyValueDaoImpl propertyValueDaoImpl;
    @Autowired
    private ReviewDaoImpl reviewDaoImpl;

    @RequestMapping("forehome")
    public String home(HttpSession session){
        List<Category> categories = categoryDaoImpl.list();
        productDaoImpl.fill(categories);
        productDaoImpl.fillByRow(categories);

        //设置ServletContext全局变量
        String contextPath = servletContext.getInitParameter("contextPath");
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

    @RequestMapping("foreproduct")
    public String product(Model model,Integer pid){
        Product product = productDaoImpl.get(pid);
        List<ProductImage> productSingleImages = productImageDaoImpl.list(pid,"type_single");
        List<ProductImage> productDetailImages = productImageDaoImpl.list(pid,"type_detail");
        product.setProductSingleImages(productSingleImages);
        product.setProductDetailImages(productDetailImages);

        productDaoImpl.setCategory(product);
        Integer picId = productDaoImpl.getImage(product.getId()).get(0);
        product.setImageId(picId);

        List<PropertyValue> pvs = propertyValueDaoImpl.list(pid);
        propertyValueDaoImpl.setProperty(pvs);
        List<Review> reviews = reviewDaoImpl.list(pid);
        productDaoImpl.setSaleAndReviewNumber(product);

        model.addAttribute("reviews",reviews);
        model.addAttribute("p",product);
        model.addAttribute("pvs",pvs);
        return "fore/product";
    }

    @RequestMapping("forecheckLogin")
    @ResponseBody
    public String checkLogin(HttpSession session){
        //游客状态时，因为没有初始化，所以user为null。
        User user = (User) session.getAttribute("user");
        if(user != null){
            return "success";
        }else {
            return "failure";
        }
    }

    @RequestMapping("foreloginAjax")
    @ResponseBody
    public String loginAjax(@RequestParam("name") String name,@RequestParam("password") String password,HttpSession session){
        name = HtmlUtils.htmlEscape(name);
        User user = userDaoImpl.getAccount(name,password);

        if(user==null){
            return "failure";
        }else {
            session.setAttribute("user",user);
            return "success";
        }
    }

    @RequestMapping("forecategory")
    public String category(@RequestParam("cid") Integer cid,String sort,Model model){
        Category category = categoryDaoImpl.get(cid);
        productDaoImpl.fill(category);
        productDaoImpl.setSaleAndReviewNumber(category.getProducts());
        productDaoImpl.getImage(category.getProducts());

        if(sort!=null){
            switch (sort){
                case "all":
                    Collections.sort(category.getProducts(),new ComprehensiveComparator());
                    break;
                case "review":
                    Collections.sort(category.getProducts(),new ProductReviewComparator());
                    break;
                case "date":
                    Collections.sort(category.getProducts(),new ProductDateComparator());
                    break;
                case "saleCount":
                    Collections.sort(category.getProducts(),new ProductSaleCountComparator());
                    break;
                case "price":
                    Collections.sort(category.getProducts(),new ProductPriceComparator());
                    break;
            }
        }
        model.addAttribute("c",category);
        return "fore/category";
    }

    @RequestMapping("foresearch")
    public String search(@RequestParam("keyword") String keyword,Model model){
        List<Product> products = productDaoImpl.search(keyword);
        productDaoImpl.setSaleAndReviewNumber(products);
        productDaoImpl.getImage(products);
        model.addAttribute("ps",products);
        return "fore/searchResult";
    }
}

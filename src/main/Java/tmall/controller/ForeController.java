package tmall.controller;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;
import tmall.comparator.*;
import tmall.dao.OrderDao;
import tmall.pojo.*;
import tmall.service.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
    /*@Autowired
    private ServletContext servletContext;*/
    @Autowired
    private ProductImageDaoImpl productImageDaoImpl;
    @Autowired
    private PropertyValueDaoImpl propertyValueDaoImpl;
    @Autowired
    private ReviewDaoImpl reviewDaoImpl;
    @Autowired
    private OrderItemDaoImpl orderItemDaoImpl;
    @Autowired
    private OrderDaoImpl orderDaoImpl;

    @RequestMapping("forehome")
    public String home(Model model) {
        List<Category> categories = categoryDaoImpl.list();
        productDaoImpl.fill(categories);
        productDaoImpl.fillByRow(categories);

        /*//设置ServletContext全局变量
        String contextPath = servletContext.getInitParameter("contextPath");
        servletContext.setAttribute("contextPath",contextPath);*/

        model.addAttribute("cs", categories);
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
        productDaoImpl.setImage(category.getProducts());

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
        productDaoImpl.setImage(products);
        model.addAttribute("ps",products);
        return "fore/searchResult";
    }

    @RequestMapping("forebuyone")
    public String buyOne(Integer pid,Integer num,HttpSession session){
        Product p = productDaoImpl.get(pid);
        User user = (User) session.getAttribute("user");
        boolean exist = false;
        Integer oiid = 0;

        //判断该购买产品是否已经在购物车中，如果有，则更新数量。
        List<OrderItem> orderItems = orderItemDaoImpl.listUser(user.getId());
        for (OrderItem orderItem:orderItems){
            if(orderItem.getPid().intValue()==p.getId().intValue()){
                orderItem.setNumber(num+orderItem.getNumber());
                orderItemDaoImpl.update(orderItem);
                exist = true;
                oiid = orderItem.getId();
                break;
            }
        }

        if(exist==false){
            OrderItem oi = new OrderItem();
            oi.setNumber(num);
            oi.setPid(pid);
            oi.setOid(-1);
            oi.setUid(user.getId());
            oi.setProduct(p);
            orderItemDaoImpl.add(oi);
            oiid = oi.getId();
        }
        return "redirect:forebuy?oiid="+oiid;
    }

    @RequestMapping("foreaddCart")
    @ResponseBody
    public String addCart(Integer pid,Integer num,HttpSession session){
        Product p = productDaoImpl.get(pid);
        User user = (User) session.getAttribute("user");
        boolean exist = false;

        //判断该购买产品是否已经在购物车中，如果有，则更新数量。
        List<OrderItem> orderItems = orderItemDaoImpl.listUser(user.getId());
        for (OrderItem orderItem:orderItems){
            if(orderItem.getPid().intValue()==p.getId().intValue()){
                orderItem.setNumber(num+orderItem.getNumber());
                orderItemDaoImpl.update(orderItem);
                exist = true;
                break;
            }
        }

        if(exist==false){
            OrderItem oi = new OrderItem();
            oi.setNumber(num);
            oi.setPid(pid);
            oi.setOid(-1);
            oi.setUid(user.getId());
            oi.setProduct(p);
            orderItemDaoImpl.add(oi);
        }
        return "success";
    }

    @RequestMapping("forecart")
    public String cart(HttpSession session,Model model){
        User user = (User)session.getAttribute("user");
        List<OrderItem> ois = orderItemDaoImpl.listUser(user.getId());
        orderItemDaoImpl.setProductAndImageId(ois);
        model.addAttribute("ois",ois);
        return "fore/cart";
    }

    @RequestMapping("forechangeOrderItem")
    @ResponseBody
    public String changeOrderItem(HttpSession session,@RequestParam("pid") Integer pid,@RequestParam("number") Integer num){
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "failure";
        }

        List<OrderItem> ois = orderItemDaoImpl.listUser(user.getId());
        orderItemDaoImpl.setProductAndImageId(ois);
        for(OrderItem oi:ois){
            //必须使用intValue,否则比较的是Integer引用（内存地址）
            if(oi.getPid().intValue()==pid.intValue()){
                oi.setNumber(num);
                orderItemDaoImpl.update(oi);
                break;
            }
        }
        return "success";
    }

    @RequestMapping("foredeleteOrderItem")
    @ResponseBody
    public String deleteOrderItem(Integer oiid,HttpSession session){
        User user = (User) session.getAttribute("user");
        if(user==null){
            return "failure";
        }

        orderItemDaoImpl.delete(oiid);
        return "success";
    }

    @RequestMapping("forebuy")
    public String buy(Integer[] oiid,Model model,HttpSession session){
        List<OrderItem> ois = new ArrayList<>();
        float total = 0;

        for(Integer id:oiid){
            /*Integer id = Integer.parseInt(strid);*/
            OrderItem oi = orderItemDaoImpl.get(id);
            orderItemDaoImpl.setProductAndImageId(oi);
            total+=oi.getNumber()*oi.getProduct().getPromotePrice();
            ois.add(oi);
        }

        session.setAttribute("ois",ois);
        model.addAttribute("total",total);
        return "fore/buy";
    }

    @RequestMapping("forecreateOrder")
    public String createOrder(HttpSession session,Order order,Model model){
        User user = (User)session.getAttribute("user");
        List<OrderItem> ois = (List<OrderItem>) session.getAttribute("ois");
        String orderCode = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ RandomUtils.nextInt(0,1000);
        Date createDate = new Date();
        order.setOrderCode(orderCode);
        order.setOrderItems(ois);
        order.setCreateDate(createDate);
        order.setUid(user.getId());
        order.setStatus(OrderDao.WAIT_PAY);
        float total = orderDaoImpl.add(order,ois);
        return "redirect:forealipay?oid="+order.getId()+"&total="+total;
    }

    @RequestMapping("forepayed")
    public String payed(Integer oid,float total,Model model){
        Order order = orderDaoImpl.get(oid);
        order.setPayDate(new Date());
        order.setStatus(OrderDao.WAIT_DELIVERY);
        order.setTotal(total);
        orderDaoImpl.update(order);
        model.addAttribute("o",order);
        return "fore/payed";
    }

    @RequestMapping("forebought")
    public String bought(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        List<Order> os = orderDaoImpl.listUserStatus(user.getId(),"delete");
        orderItemDaoImpl.fill(os);
        model.addAttribute("os",os);
        return "fore/bought";
    }

    @RequestMapping("foreconfirmPay")
    public String confirmPay(Integer oid,Model model) {
        Order order = orderDaoImpl.get(oid);
        orderItemDaoImpl.find(order);
        model.addAttribute("o",order);
        return "fore/confirmPay";
    }

    @RequestMapping("foreorderConfirmed")
    public String orderConfirmed(Integer oid,Model model){
        Order order = orderDaoImpl.get(oid);
        order.setConfirmDate(new Date());
        order.setStatus(OrderDao.WAIT_REVIEW);
        /*orderItemDaoImpl.find(order);*/
        orderDaoImpl.update(order);
        model.addAttribute("o",order);
        return "fore/orderConfirmed";
    }

    @RequestMapping("foredeleteOrder")
    @ResponseBody
    public String deleteOrder(Integer oid){
        Order order = orderDaoImpl.get(oid);
        order.setStatus(OrderDao.DELETE);
        orderDaoImpl.update(order);
        return "success";
    }

    @RequestMapping("forereview")
    public String review(Integer oid,Integer pid,Model model){
        Order order = orderDaoImpl.get(oid);
        Product product = productDaoImpl.get(pid);
        productDaoImpl.setImage(product);
        productDaoImpl.setSaleAndReviewNumber(product);
        List<Review> reviews = reviewDaoImpl.list(pid);
        reviewDaoImpl.setUser(reviews);

        model.addAttribute("o",order);
        model.addAttribute("p",product);
        model.addAttribute("reviews",reviews);
        return "fore/review";
    }

    @RequestMapping("foredoReview")
    public String doReview(Integer oid,Integer pid,String content,HttpSession session){
        User user = (User) session.getAttribute("user");
        Order order = orderDaoImpl.get(oid);
        List<OrderItem> orderItems = orderItemDaoImpl.list(order);

        content = HtmlUtils.htmlEscape(content);
        Review review = new Review();
        review.setContent(content);
        review.setPid(pid);
        review.setUid(user.getId());
        review.setCreateDate(new Date());
        review.setUser(user);

        reviewDaoImpl.add(review);
        orderItemDaoImpl.setReview(orderItems,review,pid);
        orderDaoImpl.orderReview(orderItems,order);

        return "redirect:forereview?oid="+oid+"&pid="+pid+"&showonly=true";
    }
}

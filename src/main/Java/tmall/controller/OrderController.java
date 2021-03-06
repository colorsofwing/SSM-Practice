package tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tmall.service.OrderDaoImpl;
import tmall.service.OrderItemDaoImpl;
import tmall.service.UserDaoImpl;
import tmall.pojo.Order;
import tmall.pojo.User;
import tmall.util.Page;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    private OrderDaoImpl orderDaoImpl;
    @Autowired
    private UserDaoImpl userDaoImpl;
    @Autowired
    private OrderItemDaoImpl orderItemDaoImpl;

    @RequestMapping("/admin_order_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Order> list = orderDaoImpl.list();

        for(Order o:list){
            User user = userDaoImpl.get(o.getUid());
            o.setUser(user);
        }
        orderItemDaoImpl.fill(list);

        int total = (int)new PageInfo<>(list).getTotal();
        page.setTotal(total);

        model.addAttribute("list",list);
        model.addAttribute("page",page);
        return "admin/listOrder";
    }

    @RequestMapping("/admin_order_delivery")
    public String deliver(Order order){
        Order o = orderDaoImpl.get(order.getId());
        o.setDeliveryDate(new Date());
        o.setStatus(OrderDaoImpl.WAIT_CONFIRM);
        orderDaoImpl.update(o);
        return "redirect:admin_order_list";
    }
}

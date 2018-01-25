package tmall.dao;

import tmall.pojo.Order;

import java.util.List;

public interface OrderDao {
    //状态常量
    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    //增删改查
    void add(Order order);
    void delete(Integer id);
    void update(Order order);
    Order get(Integer id);
    List<Order> list();
}

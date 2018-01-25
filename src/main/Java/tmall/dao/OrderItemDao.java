package tmall.dao;

import tmall.pojo.Order;
import tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemDao {
    //增删改查
    void add(OrderItem orderItem);
    void delete(Integer id);
    void update(OrderItem orderItem);
    OrderItem get(Integer id);
    List<OrderItem> list();
    //根据订单查找订单项
    void fill(Order order);
    void fill(List<Order> orderList);
}

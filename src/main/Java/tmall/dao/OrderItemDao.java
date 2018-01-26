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

    //根据订单查找订单项
    List<OrderItem> list(Order order);
    //订单计算商品数量和总金额
    void find(Order order);
    //处理所有订单
    void fill(List<Order> orderList);
}

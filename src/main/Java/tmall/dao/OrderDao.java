package tmall.dao;

import org.apache.ibatis.annotations.Param;
import tmall.pojo.Order;

import java.util.List;

public interface OrderDao {
    //状态常量
    String WAIT_PAY = "waitPay";
    String WAIT_DELIVERY = "waitDelivery";
    String WAIT_CONFIRM = "waitConfirm";
    String WAIT_REVIEW = "waitReview";
    String FINISH = "finish";
    String DELETE = "delete";

    //增删改查
    void add(Order order);
    void delete(Integer id);
    void update(Order order);
    Order get(@Param("id") Integer id);
    List<Order> list();
}

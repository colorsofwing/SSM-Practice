package tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tmall.dao.OrderDao;
import tmall.pojo.Order;
import tmall.pojo.OrderItem;

import java.util.List;

@Service("o")
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderItemDaoImpl orderItemDaoImpl;

    @Override
    public void add(Order order) {
        orderDao.add(order);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public float add(Order order, List<OrderItem> ois) {
        float total = 0;
        //新增订单
        add(order);

        /*if (true)
            throw new RuntimeException();*/

        //修改oid，使购物车清空
        for (OrderItem oi : ois) {
            oi.setOid(order.getId());
            orderItemDaoImpl.update(oi);
            total += oi.getNumber() * oi.getProduct().getPromotePrice();
        }

        return total;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }

    @Override
    public Order get(Integer id) {
        return orderDao.get(id);
    }

    @Override
    public List<Order> list() {
        return orderDao.list();
    }

    @Override
    public List<Order> listUserStatus(Integer uid, String excludedStatus) {
        return orderDao.listUserStatus(uid,excludedStatus);
    }

    /*public void fill(Order order){
        List<OrderItem> orderItems = orderItemDaoImpl.list(order);
        orderItemDaoImpl.setProductAndImageId(orderItems);
        order.setOrderItems(orderItems);
    }

    public void fill(List<Order> orders){
        for(Order order:orders){
            fill(order);
        }
    }*/
}

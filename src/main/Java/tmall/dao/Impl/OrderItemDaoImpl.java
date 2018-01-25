package tmall.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.OrderItemDao;
import tmall.pojo.Order;
import tmall.pojo.OrderItem;

import java.util.List;

@Service("oi")
public class OrderItemDaoImpl implements OrderItemDao{

    @Autowired
    private OrderItemDao orderItemDao;

    @Override
    public void add(OrderItem orderItem) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(OrderItem orderItem) {

    }

    @Override
    public OrderItem get(Integer id) {
        return null;
    }

    @Override
    public List<OrderItem> list() {
        return null;
    }

    @Override
    public void fill(Order order) {

    }

    @Override
    public void fill(List<Order> orderList) {

    }
}

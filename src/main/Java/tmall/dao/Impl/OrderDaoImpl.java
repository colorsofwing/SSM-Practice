package tmall.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.OrderDao;
import tmall.pojo.Order;

import java.util.List;

@Service("o")
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private OrderDao orderDao;

    @Override
    public void add(Order order) {

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
}

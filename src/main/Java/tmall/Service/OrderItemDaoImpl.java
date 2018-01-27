package tmall.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.OrderItemDao;
import tmall.dao.ProductDao;
import tmall.pojo.Order;
import tmall.pojo.OrderItem;
import tmall.pojo.Product;

import java.util.List;

@Service("oi")
public class OrderItemDaoImpl implements OrderItemDao{

    @Autowired
    private OrderItemDao orderItemDao;
    @Autowired
    private ProductDao productDao;

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
    public List<OrderItem> list(Order order) {
        return orderItemDao.list(order);
    }

    @Override
    public void find(Order order) {
        //获取所有订单项
        List<OrderItem> orderItemList = list(order);
        order.setOrderItems(orderItemList);
        //计算数量和金额
        float totalPrice = 0;
        int totalAmount = 0;
        for(OrderItem orderItem:orderItemList){
            //找到相应的产品
            Product p = productDao.get(orderItem.getPid());
            //存储产品图片id以及产品对象
            List<Integer> array = productDao.getImage(p.getId());
            if(!array.isEmpty()){
                Integer i = array.get(0);
                p.setImageId(i);
            }
            orderItem.setProduct(p);
            //计算
            Integer amount = orderItem.getNumber();
            Float price = amount * orderItem.getProduct().getPromotePrice();
            totalAmount += amount;
            totalPrice += price;
        }
        //存储计算结果
        order.setTotalNumber(totalAmount);
        order.setTotal(totalPrice);
    }

    @Override
    public void fill(List<Order> orderList) {
        for(Order order:orderList){
            find(order);
        }
    }
}

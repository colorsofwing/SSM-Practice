package tmall.service;

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
    private ProductDaoImpl productDaoImpl;

    @Override
    public void add(OrderItem orderItem) {
        orderItemDao.add(orderItem);
    }

    @Override
    public void delete(Integer id) {
        orderItemDao.delete(id);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemDao.update(orderItem);
    }

    @Override
    public OrderItem get(Integer id) {
        return orderItemDao.get(id);
    }

    @Override
    public List<OrderItem> list(Order order) {
        return orderItemDao.list(order);
    }

    @Override
    public List<OrderItem> listProduct(Integer pid) {
        return orderItemDao.listProduct(pid);
    }

    public void setProductAndImageId(OrderItem orderItem){
        //找到相应的产品
        Product p = productDaoImpl.get(orderItem.getPid());
        //存储产品图片id以及产品对象
        List<Integer> array = productDaoImpl.getImage(p.getId());
        if(!array.isEmpty()){
            Integer i = array.get(0);
            p.setImageId(i);
        }
        orderItem.setProduct(p);
    }

    public void setProductAndImageId(List<OrderItem> orderItems){
        for(OrderItem orderItem:orderItems){
            setProductAndImageId(orderItem);
        }
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
            //设置产品和图片属性
            setProductAndImageId(orderItem);
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

    @Override
    public Integer getSalesCount(Integer pid) {
        List<OrderItem> orderItems = listProduct(pid);
        Integer salesCount = 0;
        for(OrderItem orderItem:orderItems){
            salesCount+=orderItem.getNumber();
        }
        return salesCount;
    }

    @Override
    public List<OrderItem> listUser(Integer uid) {
        return orderItemDao.listUser(uid);
    }
}

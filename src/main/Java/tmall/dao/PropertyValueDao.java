package tmall.dao;

import tmall.pojo.Product;
import tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueDao {
    //增删改查
   /* public void update(PropertyValue.xml propertyValue);
    public PropertyValue.xml get(Integer pid,Integer ptid);*/
    public List<PropertyValue> list(Integer pid);
    //初始化
    /*public void init(Product product);*/
}

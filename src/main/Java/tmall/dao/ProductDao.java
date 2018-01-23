package tmall.dao;

import org.apache.ibatis.annotations.Param;
import tmall.pojo.Product;

import java.util.List;

public interface ProductDao {
    //增删改查
    public void add(Product product);
    public void delete(Integer id);
    public void update(Product product);
    public Product get(Integer id);
    public List<Product> list(Integer cid);
    //查找对应的缩略图
    public List<Integer> getImage(Integer id);
}

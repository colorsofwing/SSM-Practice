package tmall.dao;

import tmall.pojo.Product;

import java.util.List;

public interface ProductDao {
    //增删改查
    public void add(Product product);
    public void delete(Integer id);
    public void update(Product product);
    public Product get(Integer id);
    public List<Product> list(Integer cid);
}

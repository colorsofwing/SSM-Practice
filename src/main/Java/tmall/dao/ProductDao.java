package tmall.dao;

import org.apache.ibatis.annotations.Param;
import tmall.pojo.Category;
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
    //前端首页中分类下面需包含产品，同时分组
    void fill(List<Category> categoryList);
    void fill(Category category);
    void fillByRow(List<Category> categoryList);
    //产品包含相应的产品图片
    void findImage(List<Product> products);
    //设置销量和评价数量
    void setSaleAndReviewNumber(Product product);
    void setSaleAndReviewNumber(List<Product> products);
}

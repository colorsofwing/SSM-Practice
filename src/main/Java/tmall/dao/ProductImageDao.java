package tmall.dao;

import org.apache.ibatis.annotations.Param;
import tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageDao {
    //增删改查
    public void add(ProductImage productImage);
    public void delete(Integer id);
    /*public void update(ProductImage productImage);*/
    public ProductImage get(Integer id);
    public List<ProductImage> list(@Param("pid") Integer pid, @Param("type") String type);
}

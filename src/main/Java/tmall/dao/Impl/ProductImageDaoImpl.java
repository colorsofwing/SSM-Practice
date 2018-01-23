package tmall.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.ProductImageDao;
import tmall.pojo.ProductImage;

import java.util.List;

@Service
public class ProductImageDaoImpl implements ProductImageDao {

    @Autowired
    private ProductImageDao productImageDao;

    public final String single ="type_single";
    public final String detail ="type_detail";

    @Override
    public void add(ProductImage productImage) {
        productImageDao.add(productImage);
    }

    @Override
    public void delete(Integer id) {
        productImageDao.delete(id);
    }

    /*@Override
    public void update(ProductImage productImage) {
        productImageDao.update(productImage);
    }*/

    @Override
    public ProductImage get(Integer id) {
        return productImageDao.get(id);
    }

    @Override
    public List<ProductImage> list(Integer pid,String type) {
        return productImageDao.list(pid,type);
    }
}

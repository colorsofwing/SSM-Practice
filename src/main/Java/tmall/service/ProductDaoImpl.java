package tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.ProductDao;
import tmall.pojo.Category;
import tmall.pojo.Product;

import java.util.List;

@Service("pd")
public class ProductDaoImpl implements ProductDao{
    @Autowired
    private ProductDao productDao;
    @Override
    public void add(Product product) {
        productDao.add(product);
    }

    @Override
    public void delete(Integer id) {
        productDao.delete(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public Product get(Integer id) {
        return productDao.get(id);
    }

    @Override
    public List<Product> list(Integer cid) {
        return productDao.list(cid);
    }

    @Override
    public List<Integer> getImage(Integer id) {
        return productDao.getImage(id);
    }

    @Override
    public void fill(List<Category> categoryList) {

    }

    @Override
    public void fill(Category category) {

    }

    @Override
    public void fillByRow(List<Category> categoryList) {

    }
}

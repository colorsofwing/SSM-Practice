package tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.ProductDao;
import tmall.pojo.Category;
import tmall.pojo.Product;

import java.util.ArrayList;
import java.util.Iterator;
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
        for(Category category:categoryList){
            fill(category);
        }
    }

    @Override
    public void fill(Category category) {
        List<Product> products = list(category.getId());
        findImage(products);
        category.setProducts(products);
    }

    @Override
    public void fillByRow(List<Category> categories) {
        for (Category category:categories){
            List<Product> products = category.getProducts();
            List<List<Product>> pll = new ArrayList<>();

            //开始对分组下面的所有商品按组填充
            int productNumberEachRow = 3;
            List<Product> productList_c = new ArrayList<>();//包装袋（8个一组）
            Iterator iterator = products.iterator();
            while (iterator.hasNext()){
                Product p = (Product)iterator.next();
                productList_c.add(p);
                if(productList_c.size()%productNumberEachRow==0||!iterator.hasNext()){
                    pll.add(productList_c);
                    productList_c = new ArrayList<>();
                }
            }
            category.setProductsByRow(pll);

        }
    }

    @Override
    public void findImage(List<Product> products) {
        for(Product product:products){
            List<Integer> array = getImage(product.getId());
            if(!array.isEmpty()){
                Integer i = array.get(0);
                product.setImageId(i);
            }
        }
    }
}

package tmall.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.CategoryDao;
import tmall.pojo.Category;

import java.util.List;

@Service("c")
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private CategoryDao categoryDao;

    /*public List<Category> list(Page page) {
        return categoryDao.list(page);
    }*/

    /*public int total(){
        return categoryDao.total();
    }*/

    public List<Category> list() {
        return categoryDao.list();
    }

    public void add(Category category){
        categoryDao.add(category);
    }

    public Category get(Integer id) {
        return categoryDao.get(id);
    }

    public void update(Category category){
        categoryDao.update(category);
    }

    public void delete(Integer id){
        categoryDao.delete(id);
    }
}

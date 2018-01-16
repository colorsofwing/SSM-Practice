package tmall.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.CategoryDao;
import tmall.pojo.Category;

import java.util.List;

@Service("c")
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private CategoryDao categoryDao;

    public List<Category> list() {
        return categoryDao.list();
    }
}

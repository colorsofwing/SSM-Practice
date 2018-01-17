package tmall.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.CategoryDao;
import tmall.pojo.Category;
import tmall.util.Page;

import java.util.List;

@Service("c")
public class CategoryDaoImpl implements CategoryDao {

    @Autowired
    private CategoryDao categoryDao;

    public List<Category> list(Page page) {
        return categoryDao.list(page);
    }
    public int total(){
        return categoryDao.total();
    }
}

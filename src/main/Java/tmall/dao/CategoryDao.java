package tmall.dao;

import tmall.pojo.Category;
import tmall.util.Page;

import java.util.List;

public interface CategoryDao {
    List<Category> list(Page page);
    int total();
}

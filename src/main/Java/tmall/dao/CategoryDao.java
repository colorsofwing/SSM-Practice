package tmall.dao;

import tmall.pojo.Category;
import tmall.util.Page;

import java.util.List;

public interface CategoryDao {

    List<Category> list(Page page);//查找记录

    int total();//返回记录总数

    void add(Category category);//增加记录

    Category get(Integer id);//选择特定id的记录
}

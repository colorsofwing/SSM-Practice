
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tmall.pojo.Category;
import tmall.service.CategoryDaoImpl;
import tmall.service.ProductDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String arg[]){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        ProductDaoImpl productDaoImpl=(ProductDaoImpl) ac.getBean("pd");
        CategoryDaoImpl categoryDaoImpl=(CategoryDaoImpl) ac.getBean("c");
        List<Category> categoryList = new ArrayList<>();
        Category category = categoryDaoImpl.get(60);
        productDaoImpl.fill(category);
        categoryList.add(category);
       /* categoryList.add(categoryDaoImpl.get(64));*/
        productDaoImpl.fillByRow(categoryList);
        System.out.println(categoryList.get(0).getProductsByRow());
    }
}

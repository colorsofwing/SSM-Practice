
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tmall.dao.Impl.CategoryDaoImpl;
import tmall.pojo.Category;
import tmall.util.Page;

import java.util.List;

public class MainTest {
    public static void main(String arg[]){
        Page page=new Page(0,5);
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        CategoryDaoImpl service = (CategoryDaoImpl) ac.getBean("c");
        List<Category> list = service.list(page);
        for(Category category:list){
            System.out.println(category.getId()+" "+category.getName());
        }
    }
}

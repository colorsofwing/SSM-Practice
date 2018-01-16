
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tmall.dao.Impl.CategoryDaoImpl;
import tmall.pojo.Category;
import java.util.List;

public class MainTest {
    public static void main(String arg[]){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        CategoryDaoImpl service = (CategoryDaoImpl) ac.getBean("c");
        List<Category> list = service.list();
        for(Category category:list){
            System.out.println(category.getId()+" "+category.getName());
        }
    }
}

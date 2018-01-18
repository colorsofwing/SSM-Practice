
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tmall.dao.Impl.CategoryDaoImpl;
import tmall.pojo.Category;
import tmall.util.Page;

import java.util.List;

public class MainTest {
    public static void main(String arg[]){
        Page page=new Page();//实例已经使用默认构造函数初始化
        System.out.print(page.getParam());
        int b=0;
        Integer a = b;

        /*ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        CategoryDaoImpl service = (CategoryDaoImpl) ac.getBean("c");
        List<Category> list = service.list(page);
        for(Category category:list){
            System.out.println(category.getId()+" "+category.getName());
        }*/
    }
}

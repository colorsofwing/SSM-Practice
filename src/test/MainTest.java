
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tmall.dao.Impl.CategoryDaoImpl;
import tmall.dao.Impl.ProductDaoImpl;
import tmall.dao.Impl.PropertyDaoImpl;
import tmall.dao.Impl.PropertyValueDaoImpl;
import tmall.pojo.Category;
import tmall.pojo.Product;
import tmall.pojo.Property;
import tmall.pojo.PropertyValue;
import tmall.util.Page;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class MainTest {
    public static void main(String arg[]){
        /*Page page=new Page();//实例已经使用默认构造函数初始化
        System.out.print(page.getParam());*/

        /*ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        CategoryDaoImpl service = (CategoryDaoImpl) ac.getBean("c");
        List<Category> list = service.list(page);
        for(Category category:list){
            System.out.println(category.getId()+" "+category.getName());
        }*/

        /*ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        PropertyDaoImpl service = (PropertyDaoImpl) ac.getBean("p");
        List<Property> list = service.list(83);
        for(Property p:list){
            System.out.println(p.getId()+" "+p.getName()+" "+p.getCid()+'\n');
        }*/
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        PropertyValueDaoImpl propertyValueDao=(PropertyValueDaoImpl) ac.getBean("pv");
        List<PropertyValue> pvl = propertyValueDao.list(91);
        System.out.println(pvl);
        /*for(Product product:pl){
            Integer j = product.getId();
            System.out.println(j);
            List<Integer> i = productDaoImpl.getImage(j);
            System.out.println(i);
        }*/
    }
}

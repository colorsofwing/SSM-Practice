
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tmall.dao.Impl.CategoryDaoImpl;
import tmall.dao.Impl.ProductDaoImpl;
import tmall.dao.Impl.PropertyDaoImpl;
import tmall.pojo.Category;
import tmall.pojo.Product;
import tmall.pojo.Property;
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
       /* ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        ProductDaoImpl productDaoImpl=(ProductDaoImpl) ac.getBean("pd");
        Product p = new Product();
        p.setName("测试");
        p.setCid(60);
        p.setOriginalPrice(new Float(100));
        p.setPromotePrice(new Float(50));
        p.setStock(100);
        *//*DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());*//*
        p.setCreateDate(new Date());
        System.out.println(new Date());
        productDaoImpl.add(p);*/
    }
}

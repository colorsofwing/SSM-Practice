
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import tmall.Service.PropertyValueDaoImpl;

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
        List<Integer> list_ptid = propertyValueDao.select_ptid(963);
        System.out.println(list_ptid);
        /*for(Product product:pl){
            Integer j = product.getId();
            System.out.println(j);
            List<Integer> i = productDaoImpl.getImage(j);
            System.out.println(i);
        }*/
    }
}

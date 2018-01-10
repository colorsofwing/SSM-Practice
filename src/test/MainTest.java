import lyons.entity.Goods;
import lyons.service.Impl.GoodsServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainTest {
    public static void main(String arg[]){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        GoodsServiceImpl service = (GoodsServiceImpl) ac.getBean("s");
        //GoodsServiceImpl service = new GoodsServiceImpl();
        List<Goods> list = service.findAllGoods();
        for(Goods good:list){
            System.out.println(good.getCommodity_number()+" "+good.getCommodity_name()+" "+good.getCommodity_price());
        }
    }
}

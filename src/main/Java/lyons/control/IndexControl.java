package lyons.control;

import lyons.entity.Goods;
import lyons.service.Impl.GoodsServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Controller
public class IndexControl {
    @RequestMapping({"/","/index"})
    public ModelAndView handle(){
        ModelAndView mav = new ModelAndView("index");

        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");
        GoodsServiceImpl service = (GoodsServiceImpl) ac.getBean("s");
        List<Goods> list = service.findAllGoods();
        /*Iterator<Goods> it = list.iterator();
        HashMap map = new HashMap();
        int i = 0;
        while (it.hasNext()){
            Goods good = it.next();
            map.put(i++,good);
        }*/
        mav.addObject("list",list);
        return mav;
    }
}

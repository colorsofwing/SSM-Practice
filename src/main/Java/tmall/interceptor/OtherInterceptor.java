package tmall.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import tmall.pojo.Category;
import tmall.pojo.OrderItem;
import tmall.pojo.User;
import tmall.service.CategoryDaoImpl;
import tmall.service.OrderItemDaoImpl;
import tmall.service.ProductDaoImpl;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class OtherInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ServletContext servletContext;
    @Autowired
    private OrderItemDaoImpl orderItemDaoImpl;
    @Autowired
    private CategoryDaoImpl categoryDaoImpl;
    @Autowired
    private ProductDaoImpl productDaoImpl;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contextPath = servletContext.getInitParameter("contextPath");
        servletContext.setAttribute("contextPath", contextPath);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //页面渲染前得到amount
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer amount = 0;
        if (user != null) {
            List<OrderItem> orderItems = orderItemDaoImpl.listUser(user.getId());
            for (OrderItem oi : orderItems) {
                amount += oi.getNumber();
            }
        } else {
            amount = 0;
        }
        session.setAttribute("cartTotalItemNumber", amount);

        //页面渲染前生成分类列表对象
        List<Category> cs = categoryDaoImpl.list();
        productDaoImpl.fill(cs);

        session.setAttribute("cs", cs);

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}

package tmall.controller;
/**
 * WEB-INF目录下的，是无法通过浏览器直接访问的。
 * PageController类，专门处理服务端页面的跳转。
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class PageController {
    @RequestMapping("register")
    public String registerPage(){
        return "fore/register";
    }

    @RequestMapping("registerSuccess")
    public String registerSuccessPage(){
        return "fore/registerSuccess";
    }

    @RequestMapping("loginPage")
    public String loginPage() {
        return "fore/login";
    }
    @RequestMapping("forealipay")
    public String alipay(){
        return "fore/alipay";
    }
}

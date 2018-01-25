package tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tmall.dao.Impl.UserDaoImpl;
import tmall.pojo.User;
import tmall.util.Page;

import java.util.List;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private UserDaoImpl userDaoImpl;

    @RequestMapping("/admin_user_list")
    public String list(Model model, Page page){
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<User> list = userDaoImpl.list();
        int total = (int)new PageInfo<>(list).getTotal();
        page.setTotal(total);

        model.addAttribute("list",list);
        model.addAttribute("page",page);
        return "admin/listUser";
    }
}

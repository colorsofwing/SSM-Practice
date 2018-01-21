package tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tmall.dao.Impl.CategoryDaoImpl;
import tmall.dao.Impl.PropertyDaoImpl;
import tmall.pojo.Category;
import tmall.pojo.Property;
import tmall.util.Page;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyController {

    @Autowired
    private PropertyDaoImpl propertyDaoImpl;
    @Autowired
    private CategoryDaoImpl categoryDaoImpl;

    @RequestMapping("/admin_property_list")
    public String list(Model model, Integer cid, Page page){
        Category c = categoryDaoImpl.get(cid);
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Property> ps = propertyDaoImpl.list(cid);
        int total = (int)new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+c.getId());

        model.addAttribute("ps",ps);
        model.addAttribute("c",c);
        model.addAttribute("page",page);
        return "admin/listProperty";
    }

    @RequestMapping("/admin_property_add")
    public String add(Property p){
        propertyDaoImpl.add(p);
        return "redirect:admin_property_list?cid="+p.getCid();
    }

    @RequestMapping("/admin_property_delete")
    public String delete(Integer id){
        Property p = propertyDaoImpl.get(id);
        propertyDaoImpl.delete(p.getId());
        return "redirect:admin_property_list?cid="+p.getCid();
    }

    @RequestMapping("/admin_property_edit")
    public String edit(Model model,Integer id){
        Property p = propertyDaoImpl.get(id);
        model.addAttribute("p",p);
        return "admin/editProperty";
    }

    @RequestMapping("/admin_property_update")
    public String update(Property p){
        propertyDaoImpl.update(p);
        return "redirect:admin_property_list?cid="+p.getCid();
    }
}

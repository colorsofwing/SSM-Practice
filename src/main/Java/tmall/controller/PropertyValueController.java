package tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tmall.dao.Impl.CategoryDaoImpl;
import tmall.dao.Impl.ProductDaoImpl;
import tmall.dao.Impl.PropertyDaoImpl;
import tmall.dao.Impl.PropertyValueDaoImpl;
import tmall.pojo.Category;
import tmall.pojo.Product;
import tmall.pojo.Property;
import tmall.pojo.PropertyValue;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {
    @Autowired
    private PropertyValueDaoImpl propertyValueDaoImpl;
    @Autowired
    private PropertyDaoImpl propertyDaoImpl;
    @Autowired
    private CategoryDaoImpl categoryDaoImpl;
    @Autowired
    private ProductDaoImpl productDaoImpl;

    @RequestMapping("admin_propertyValue_edit")
    public String list(Model model,Integer pid){

        //产品储存分类对象
        Product p = productDaoImpl.get(pid);
        Category c = categoryDaoImpl.get(p.getCid());
        p.setCategory(c);

        //通过查找是否有新的ptid来决定是否需要初始化
        List<Property> properties = propertyDaoImpl.list(c.getId());
        c.setProperties(properties);
        for(Property property:properties){
            if(propertyValueDaoImpl.find(property.getId())==0){
                //新创建的产品在属性值表是没有记录的，要初始化;或者是新增了属性，也要初始化
                propertyValueDaoImpl.init(pid,property.getId());
            }
        }

        List<PropertyValue> pvl = propertyValueDaoImpl.list(pid);
        for(PropertyValue pv:pvl){
            Property pt = propertyDaoImpl.get(pv.getPtid());
            pv.setProperty(pt);
        }
        //Category c = categoryDaoImpl.get(pvl.get(0).getProperty().getCid());

        model.addAttribute("p",p);
        model.addAttribute("pvl",pvl);
        return "admin/editPropertyValue";
    }

    @RequestMapping("/admin_propertyValue_update")
    @ResponseBody
    public String update(Integer id,String value){
        propertyValueDaoImpl.update(id,value);
        return "success";
    }
}

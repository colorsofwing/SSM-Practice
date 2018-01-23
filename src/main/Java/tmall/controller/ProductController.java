package tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tmall.dao.Impl.CategoryDaoImpl;
import tmall.dao.Impl.ProductDaoImpl;
import tmall.pojo.Category;
import tmall.pojo.Product;
import tmall.util.Page;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    private ProductDaoImpl productDaoImpl;
    @Autowired
    private CategoryDaoImpl categoryDaoImpl;

    @RequestMapping("/admin_product_list")
    public String list(Model model, Integer cid, Page page){
        Category c = categoryDaoImpl.get(cid);
        PageHelper.offsetPage(page.getStart(),page.getCount());
        List<Product> pl = productDaoImpl.list(cid);

        for(Product product:pl){
            Integer i = productDaoImpl.getImage(product.getId());
            product.setImageId(i);
        }

        int total = (int)new PageInfo<>(pl).getTotal();
        page.setTotal(total);
        page.setParam("&cid="+c.getId());

        model.addAttribute("c",c);
        model.addAttribute("pl",pl);
        model.addAttribute("page",page);
        return "admin/listProduct";
    }

    @RequestMapping("/admin_product_add")
    public String add(Product p){
        p.setCreateDate(new Date());
        productDaoImpl.add(p);
        return "redirect:admin_product_list?cid="+p.getCid();
    }

    @RequestMapping("/admin_product_delete")
    public String delete(Integer id){
        Product p = productDaoImpl.get(id);
        productDaoImpl.delete(p.getId());
        return "redirect:admin_product_list?cid="+p.getCid();
    }

    @RequestMapping("/admin_product_edit")
    public String edit(Model model,Integer id){
        Product p = productDaoImpl.get(id);
        Category c = categoryDaoImpl.get(p.getCid());
        p.setCategory(c);
        model.addAttribute("p",p);
        return "admin/editProduct";
    }

    @RequestMapping("/admin_product_update")
    public String update(Product p){
        productDaoImpl.update(p);
        return "redirect:admin_product_list?cid="+p.getCid();
    }
}

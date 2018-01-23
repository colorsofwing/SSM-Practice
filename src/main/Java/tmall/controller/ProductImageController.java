package tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tmall.dao.Impl.CategoryDaoImpl;
import tmall.dao.Impl.ProductDaoImpl;
import tmall.dao.Impl.ProductImageDaoImpl;
import tmall.pojo.Category;
import tmall.pojo.Product;
import tmall.pojo.ProductImage;
import tmall.util.ImageUtil;
import tmall.util.UploadedImageFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class ProductImageController {
    @Autowired
    private ProductImageDaoImpl productImageDaoImpl;
    @Autowired
    private ProductDaoImpl productDaoImpl;
    @Autowired
    private CategoryDaoImpl categoryDaoImpl;

    @RequestMapping("/admin_productImage_list")
    public String list(Model model,Integer pid){
        Product p = productDaoImpl.get(pid);
        Category c = categoryDaoImpl.get(p.getCid());

        p.setCategory(c);
        List<ProductImage> pi_s = productImageDaoImpl.list(pid,productImageDaoImpl.single);
        List<ProductImage> pi_d = productImageDaoImpl.list(pid,productImageDaoImpl.detail);

        model.addAttribute("p",p);
        model.addAttribute("pi_s",pi_s);
        model.addAttribute("pi_d",pi_d);
        return "admin/listProductImage";
    }

    @RequestMapping("/admin_productImage_add")
    public String add(ProductImage pi, UploadedImageFile uploadedImageFile, HttpSession session) throws IOException{
        productImageDaoImpl.add(pi);

        String filename = pi.getId()+".jpg";
        String imageFolder;
        String imageFolder_small=null;
        String imageFolder_middle=null;
        if(productImageDaoImpl.single.equals(pi.getType())) {
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
        }else {
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
        }
        File imageFile = new File(imageFolder,filename);

        if(!imageFile.getParentFile().exists())
            imageFile.getParentFile().mkdirs();

        uploadedImageFile.getImage().transferTo(imageFile);
        ImageIO.write(ImageUtil.change2jpg(imageFile),"jpg",imageFile);

        //如果添加的是产品单个图片，还需要生成缩略图和中等图
        if(productImageDaoImpl.single.equals(pi.getType())){
            File f_small = new File(imageFolder_small,filename);
            File f_middle = new File(imageFolder_middle,filename);

            ImageUtil.resizeImage(imageFile,56,56,f_small);
            ImageUtil.resizeImage(imageFile,217,190,f_middle);
        }
        return "redirect:admin_productImage_list?pid="+pi.getPid();
    }

    @RequestMapping("/admin_productImage_delete")
    public String delete(Integer id,HttpSession session){
        ProductImage pi = productImageDaoImpl.get(id);
        productImageDaoImpl.delete(id);

        String imageFolder;
        String imageFolder_small=null;
        String imageFolder_middle=null;
        if(productImageDaoImpl.single.equals(pi.getType())){
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
            File imageFile = new File(imageFolder,id+".jpg");
            File f_small = new File(imageFolder_small,id+".jpg");
            File f_middle = new File(imageFolder_middle,id+".jpg");
            imageFile.delete();
            f_small.delete();
            f_middle.delete();
        }else {
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
            File imageFile = new File(imageFolder,id+".jpg");
            imageFile.delete();
        }
        return "redirect:admin_productImage_list?pid="+pi.getPid();
    }
}

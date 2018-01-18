package tmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tmall.dao.Impl.CategoryDaoImpl;
import tmall.pojo.Category;
import tmall.util.ImageUtil;
import tmall.util.Page;
import tmall.util.UploadedImageFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {
    @Autowired
    CategoryDaoImpl categoryDaoImpl;

    @RequestMapping("/admin_category_list")
    public String list(Model model, Page page){
        List<Category> cs = categoryDaoImpl.list(page);
        int total = categoryDaoImpl.total();
        page.setTotal(total);
        model.addAttribute("cs",cs);
        model.addAttribute("page",page);
        return "admin/listCategory";
    }

    /**
     * 将text保存到Category后在进行数据库执行insert操作，将图片文件保存到运行环境的静态资源路径中。
     * @param c
     * @param session
     * @param uploadedImageFile
     * @return
     * @throws IOException
     */
    @RequestMapping("/admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException{

        categoryDaoImpl.add(c);

        //category文件夹对象（父对象）
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        //根据父对象和子路径（图片ID）创建图片文件
        File image = new File(imageFolder,c.getId()+".jpg");
        //判断父对象是否存在，不存在则依据子对象路径创建
        if(!image.getParentFile().exists())
            image.getParentFile().mkdirs();

        //把浏览器上传的图片文件保存到服务器
        uploadedImageFile.getImage().transferTo(image);
        //将图片数据（原来为二进制格式）以“jpg”的格式写入文件
        ImageIO.write(ImageUtil.change2jpg(image),"jpg",image);

        return "redirect:/admin_category_list";
    }

    @RequestMapping("/admin_category_edit")
    public String get(Integer i,Model model){
        //除了直接用i接收参数，也可以用Category的实例接收参数。
        Category c = categoryDaoImpl.get(i);
        model.addAttribute("c",c);
        return "admin/editCategory";
    }
}

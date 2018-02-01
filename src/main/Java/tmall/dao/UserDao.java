package tmall.dao;

import org.apache.ibatis.annotations.Param;
import tmall.pojo.User;

import java.util.List;

public interface UserDao {
    //用户表的增加删除和修改只能在前台进行
    public List<User> list();
    public User get(Integer id);
    public Integer findName(String name);
    public void add(User user);
    public User getAccount(@Param("name") String name, @Param("password") String password);
    //查找是否有已经存在的user
    public boolean isExist(String name);
}

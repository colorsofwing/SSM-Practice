package tmall.dao;

import tmall.pojo.User;

import java.util.List;

public interface UserDao {
    //用户表的增加删除和修改只能在前台进行
    public List<User> list();
    public User get(Integer id);
    public Integer findname(String name);
    public void add(User user);
    //查找是否有已经存在的user
    public boolean isExist(String name);
}

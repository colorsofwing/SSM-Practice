package tmall.dao;

import tmall.pojo.User;

import java.util.List;

public interface UserDao {
    //用户表的增加删除和修改只能在前台进行
    public List<User> list();
    public User get(Integer id);
}

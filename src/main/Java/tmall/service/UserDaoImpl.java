package tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.UserDao;
import tmall.pojo.User;

import java.util.List;

@Service("u")
public class UserDaoImpl implements UserDao{

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> list() {
        return userDao.list();
    }

    @Override
    public User get(Integer id) {
        return userDao.get(id);
    }
}

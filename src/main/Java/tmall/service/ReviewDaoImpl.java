package tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.ReviewDao;
import tmall.dao.UserDao;
import tmall.pojo.Review;
import tmall.pojo.User;

import java.util.List;

@Service("r")
public class ReviewDaoImpl implements ReviewDao {

    @Autowired
    private ReviewDao reviewDao;
    @Autowired
    private UserDao userDao;

    @Override
    public void add(Review review) {
        reviewDao.add(review);
    }

    @Override
    public void delete(Integer id) {
        reviewDao.delete(id);
    }

    @Override
    public void update(Review review) {
        reviewDao.update(review);
    }

    @Override
    public Review get(Integer id) {
        return reviewDao.get(id);
    }

    @Override
    public List<Review> list(Integer pid) {
        return reviewDao.list(pid);
    }

    @Override
    public Integer getCount(Integer pid) {
        return list(pid).size();
    }

    public void setUser(Review review){
        Integer uid = review.getUid();
        User user = userDao.get(uid);
        review.setUser(user);
    }

    public void setUser(List<Review> reviews){
        for(Review review:reviews){
            setUser(review);
        }
    }
}

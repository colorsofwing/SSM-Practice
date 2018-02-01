package tmall.dao;

import tmall.pojo.Review;

import java.util.List;

public interface ReviewDao {
    //增删改查
    void add(Review review);
    void delete(Integer id);
    void update(Review review);
    Review get(Integer id);
    List<Review> list(Integer pid);
    //统计综述
    Integer getCount(Integer pid);
}

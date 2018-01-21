package tmall.dao;

import tmall.pojo.Property;

import java.util.List;

public interface PropertyDao {
    //增删改查
    public void add(Property property);
    public void delete(Integer id);
    public void update(Property property);
    public Property get(Integer id);
    public List<Property> list(Integer cid);
}

package tmall.dao;

import org.apache.ibatis.annotations.Param;
import tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueDao {
    //增删改查
    public void update(@Param("id") Integer id, @Param("value") String value);
    public List<PropertyValue> list(@Param("pid") Integer pid);
    //初始化
    public void init(Integer pid);
    public List<Integer> select_ptid(@Param("pid") Integer pid);
    public void add(PropertyValue propertyValue);
}

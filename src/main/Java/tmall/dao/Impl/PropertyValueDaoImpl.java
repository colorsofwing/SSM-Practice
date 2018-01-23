package tmall.dao.Impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.PropertyDao;
import tmall.dao.PropertyValueDao;
import tmall.pojo.Product;
import tmall.pojo.PropertyValue;

import java.util.List;

@Service("pv")
public class PropertyValueDaoImpl implements PropertyValueDao{

    @Autowired
    private PropertyValueDao propertyValueDao;
    @Autowired
    private PropertyDao propertyDao;

    /*@Override
    public void update(PropertyValue.xml propertyValue) {
        propertyValueDao.update(propertyValue);
    }

    @Override
    public PropertyValue.xml get(Integer pid, Integer ptid) {
        return propertyValueDao.get(pid,ptid);
    }*/

    @Override
    public List<PropertyValue> list(@Param("pid") Integer pid) {
        return propertyValueDao.list(pid);
    }

    /*@Override
    public void init(Product product) {

    }*/
}

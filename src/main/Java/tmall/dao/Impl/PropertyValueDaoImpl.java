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

    @Override
    public void update(Integer id,String value) {
        propertyValueDao.update(id,value);
    }

    @Override
    public List<PropertyValue> list(Integer pid) {
        return propertyValueDao.list(pid);
    }

    @Override
    public void init(Integer pid) {
        List<Integer> list_ptid = select_ptid(pid);
        for(Integer ptid:list_ptid){
            PropertyValue propertyValue = new PropertyValue();
            propertyValue.setPid(pid);
            propertyValue.setPtid(ptid);
            add(propertyValue);
        }
    }

    @Override
    public List<Integer> select_ptid(Integer pid) {
        return propertyValueDao.select_ptid(pid);
    }

    @Override
    public void add(PropertyValue propertyValue) {
        propertyValueDao.add(propertyValue);
    }
}

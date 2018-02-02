package tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.PropertyDao;
import tmall.dao.PropertyValueDao;
import tmall.pojo.Property;
import tmall.pojo.PropertyValue;

import java.util.List;

@Service("pv")
public class PropertyValueDaoImpl implements PropertyValueDao{

    @Autowired
    private PropertyValueDao propertyValueDao;
    @Autowired
    private PropertyDao propertyDao;

    @Override
    public void update(Integer id,String value) {
        propertyValueDao.update(id,value);
    }

    @Override
    public List<PropertyValue> list(Integer pid) {
        return propertyValueDao.list(pid);
    }

    /*@Override
    public void init(Integer pid) {
        List<Integer> list_ptid = select_ptid(pid);
        if(list_ptid != null){
            for(Integer ptid:list_ptid){
                PropertyValue propertyValue = new PropertyValue();
                propertyValue.setPid(pid);
                propertyValue.setPtid(ptid);
                add(propertyValue);
            }
        }
    }*/

    @Override
    public void init(Integer pid, Integer ptid) {
        PropertyValue propertyValue = new PropertyValue();
        propertyValue.setPid(pid);
        propertyValue.setPtid(ptid);
        add(propertyValue);
    }

    @Override
    public List<Integer> select_ptid(Integer pid) {
        return propertyValueDao.select_ptid(pid);
    }

    @Override
    public void add(PropertyValue propertyValue) {
        propertyValueDao.add(propertyValue);
    }

    @Override
    public int find(Integer ptid) {
        return propertyValueDao.find(ptid);
    }

    private void setProperty(PropertyValue propertyValue){
        Property property = propertyDao.get(propertyValue.getPtid());
        propertyValue.setProperty(property);
    }

    public void setProperty(List<PropertyValue> propertyValues){
        for(PropertyValue propertyValue:propertyValues){
            setProperty(propertyValue);
        }
    }
}

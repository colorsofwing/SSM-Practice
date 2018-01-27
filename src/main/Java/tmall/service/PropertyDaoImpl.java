package tmall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tmall.dao.PropertyDao;
import tmall.pojo.Property;

import java.util.List;

@Service("pp")
public class PropertyDaoImpl implements PropertyDao {

    @Autowired
    private PropertyDao propertyDao;

    public void add(Property property){
        propertyDao.add(property);
    }

    public void delete(Integer id){
        propertyDao.delete(id);
    }

    public void update(Property property){
        propertyDao.update(property);
    }

    public Property get(Integer id){
        return propertyDao.get(id);
    }

    public List<Property> list(Integer cid){
        return propertyDao.list(cid);
    }
}

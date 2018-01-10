package lyons.service.Impl;

import lyons.dao.GoodsDao;
import lyons.entity.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("s")
public class GoodsServiceImpl{
    @Autowired
    private GoodsDao goodsDao;

    public List<Goods> findAllGoods() {
        //System.out.println(goodsDao);
        return goodsDao.goodsAllList();
    }
}

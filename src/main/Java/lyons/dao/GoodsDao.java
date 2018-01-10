package lyons.dao;

import lyons.entity.Goods;
import java.util.ArrayList;
import java.util.List;

public interface GoodsDao {
    /** 查询所有商品列表**/
    List<Goods> goodsAllList();
}

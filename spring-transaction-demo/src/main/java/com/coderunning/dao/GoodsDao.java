package com.coderunning.dao;

import com.coderunning.domain.Goods;

public interface GoodsDao {
    Goods selectGoods(Integer id);

    int updateGoods(Goods goods);
}

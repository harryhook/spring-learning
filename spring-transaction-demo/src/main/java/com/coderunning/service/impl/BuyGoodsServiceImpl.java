package com.coderunning.service.impl;

import com.coderunning.dao.GoodsDao;
import com.coderunning.dao.SalesDao;
import com.coderunning.domain.Goods;
import com.coderunning.domain.Sales;
import com.coderunning.service.BuyGoodsService;

public class BuyGoodsServiceImpl implements BuyGoodsService {

    private SalesDao salesDao;

    private GoodsDao goodsDao;

    public SalesDao getSalesDao() {
        return salesDao;
    }

    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public void setSalesDao(SalesDao salesDao) {
        this.salesDao = salesDao;
    }

    @Override
    public void buy(Integer goodsId, Integer nums) {

        Goods goods = goodsDao.selectGoods(goodsId);

        if(goods == null) {
            throw new RuntimeException("商品不存在");
        } else if(goods.getAmount() < nums) {
            throw new RuntimeException("库存不足");
        }

        Sales sales = new Sales();
        sales.setGid(goodsId);
        sales.setNums(nums);

        salesDao.insertSalesGoods(sales);
    }
}

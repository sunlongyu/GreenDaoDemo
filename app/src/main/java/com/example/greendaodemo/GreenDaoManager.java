package com.example.greendaodemo;

import android.content.Context;

import com.example.greendaodemo.Model.GoodsModel;
import com.example.greendaodemo.Model.GoodsModelDao;
import com.example.greendaodemo.utils.DataUtils;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class GreenDaoManager {

    private Context context;
    private GoodsModelDao goodsModelDao;

    public GreenDaoManager(Context context) {
        this.context = context;
        this.goodsModelDao = MyApplication.session.getGoodsModelDao();
    }

    /**
     * 插入所有数据到数据库中
     */
    public void insertGoods() {
        String json = DataUtils.getJson("goods.json", context);
        //将json转换成List<GoodsModel>并插入到数据库当中
        goodsModelDao.insertOrReplaceInTx(DataUtils.getGoodsModels(json));
    }

    /**
     * 查询所有方法
     *
     * @return
     */
    public List<GoodsModel> findAll() {
        //获取QueryBuilder
        QueryBuilder<GoodsModel> queryBuilder = goodsModelDao.queryBuilder();
        queryBuilder.orderAsc(GoodsModelDao.Properties.GoodsId);
        return queryBuilder.list();
    }

    /**
     * 查询水果的数据
     * @return
     */
    public List<GoodsModel> queryFruits () {
        QueryBuilder<GoodsModel> queryBuilder = goodsModelDao.queryBuilder();
        /**
         * 借助Property属性类提供的筛选方法
         */
        queryBuilder = queryBuilder.where(GoodsModelDao.Properties.Type.eq("0")).orderAsc(GoodsModelDao.Properties.GoodsId);
        return queryBuilder.list();
    }

    /**
     * 查询零食的数据
     * @return
     */
    public List<GoodsModel> querySnacks () {
        QueryBuilder<GoodsModel> queryBuilder = goodsModelDao.queryBuilder();
        /**
         * 借助Property属性类提供的筛选方法
         */
        queryBuilder = queryBuilder.where(GoodsModelDao.Properties.Type.eq("1")).orderAsc(GoodsModelDao.Properties.GoodsId);
        return queryBuilder.list();
    }

    /**
     * 修改指定商品的商品信息
     * @param model
     */
    public void updateGoodsInfo (GoodsModel model) {
        goodsModelDao.update(model);
        goodsModelDao.updateInTx();
    }

    /**
     * 删除指定商品的商品信息
     * @param model
     */
    public void deleteGoodsInfo (GoodsModel model) {
        goodsModelDao.deleteByKey(model.getId());
    }
}

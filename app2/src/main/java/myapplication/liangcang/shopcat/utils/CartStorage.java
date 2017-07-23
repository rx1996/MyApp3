package myapplication.liangcang.shopcat.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import myapplication.liangcang.shopcat.bean.GoodsBean;

/**
 * Created by Administrator on 2017/7/23.
 */

public class CartStorage {

    public static final String JSON_CART = "json_cart";
    public static CartStorage instance;
    private static Context mContext;
    private SparseArray<GoodsBean> sparseArray;

    private CartStorage(){
        //初始化集合
        sparseArray = new SparseArray<>();
        listTosparseArray();
    }

    private void listTosparseArray() {
        //得到所有数据
        ArrayList<GoodsBean> datas = getAllData();
        for(int i = 0; i < datas.size(); i++) {
          GoodsBean goodsBean = datas.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getGoods_id()),goodsBean);
        }
    }

    //给外部用
    private ArrayList<GoodsBean> getAllData() {
        return getLocalData();
    }

    //本地加载
    private ArrayList<GoodsBean> getLocalData() {
        ArrayList<GoodsBean> datas = new ArrayList<>();
        String saveJson = CacheUtils.getString(mContext, JSON_CART);
        if(TextUtils.isEmpty(saveJson)) {
            //解析成ArrayList数组
            datas = new Gson().fromJson(saveJson,new TypeToken<ArrayList<GoodsBean>>(){}.getType());
        }
        return datas;
    }

    public static CartStorage getInstance(Context context){
        //同步  单例
        if(instance == null) {
            mContext = context;
            //加锁
            synchronized (CartStorage.class){
                if(instance == null) {
                    instance = new CartStorage();
                }
            }
        }
        return instance;
    }
    //添加数据
    public void addData(GoodsBean bean){
        //查看内容中是否存在
        GoodsBean temp = sparseArray.get(Integer.parseInt(bean.getGoods_id()));
        if(temp != null) {
            temp.setNumber(bean.getNumber());
        }else {
            temp = bean;
        }
        //内存中更新
        sparseArray.put(Integer.parseInt(temp.getGoods_id()),temp);
        //同步到本地
        commit();

    }

    public void deleteData(GoodsBean bean){
        //内存中更新
        sparseArray.delete(Integer.parseInt(bean.getGoods_id()));
        //同步到本地
        commit();

    }
    public void updateData(GoodsBean bean){
        //内存中更新
        sparseArray.put(Integer.parseInt(bean.getGoods_id()),bean);
        //同步到本地
        commit();

    }
    //在本地中保存一份
    private void commit() {
        //把SparseArray转换成List集合
        ArrayList<GoodsBean> goodsBeans = sparseArrayToList();
        //使用Gson把List集合装换成json的String数据
        String json = new Gson().toJson(goodsBeans);
        //把文本保存在sp中
        CacheUtils.putString(mContext,JSON_CART,json);
    }

    private ArrayList<GoodsBean> sparseArrayToList() {
        ArrayList<GoodsBean> goodsBeans = new ArrayList<>();
        for(int i = 0; i < sparseArray.size(); i++) {
          GoodsBean goodsBean = sparseArray.get(i);
            goodsBeans.add(goodsBean);
        }
        return goodsBeans;
    }
}

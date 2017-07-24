package myapplication.liangcang.shopcat.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import myapplication.liangcang.shop.bean.ShopInformationBean;
import myapplication.liangcang.shopcat.bean.GoodsBean;

/**
 * Created by Administrator on 2017/7/23.
 */

public class CartStorage {

    public static final String JSON_CART = "json_cart";
    public static CartStorage instance;
    private static Context mContext;
    private SparseArray<ShopInformationBean> sparseArray;

    private CartStorage(){
        //初始化集合
        sparseArray = new SparseArray<>();
        listTosparseArray();
    }

    private void listTosparseArray() {
        //得到所有数据
        ArrayList<ShopInformationBean> datas = getAllData();
        for(int i = 0; i < datas.size(); i++) {
          ShopInformationBean goodsBean = datas.get(i);
            sparseArray.put(Integer.parseInt(goodsBean.getData().getItems().getGoods_id()),goodsBean);
        }
    }

    //给外部用
    private ArrayList<ShopInformationBean> getAllData() {
        return getLocalData();
    }

    //本地加载
    private ArrayList<ShopInformationBean> getLocalData() {
        ArrayList<ShopInformationBean> datas = new ArrayList<>();
        String saveJson = CacheUtils.getString(mContext, JSON_CART);
        if(TextUtils.isEmpty(saveJson)) {
            //解析成ArrayList数组
            datas = new Gson().fromJson(saveJson,new TypeToken<ArrayList<ShopInformationBean>>(){}.getType());
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
    public void addData(ShopInformationBean bean){
        //查看内容中是否存在
        ShopInformationBean temp = sparseArray.get(Integer.parseInt(bean.getData().getItems().getGoods_id()));
        if(temp != null) {
            temp.getData().getItems().setNumber(bean.getData().getItems().getNumber());
        }else {
            temp = bean;
        }
        //内存中更新
        sparseArray.put(Integer.parseInt(temp.getData().getItems().getGoods_id()),temp);
        //同步到本地
        commit();

    }

    public void deleteData(ShopInformationBean bean){
        //内存中更新
        sparseArray.delete(Integer.parseInt(bean.getData().getItems().getGoods_id()));
        //同步到本地
        commit();

    }
    public void updateData(ShopInformationBean bean){
        //内存中更新
        sparseArray.put(Integer.parseInt(bean.getData().getItems().getGoods_id()),bean);
        //同步到本地
        commit();

    }
    //在本地中保存一份
    private void commit() {
        //把SparseArray转换成List集合
        ArrayList<ShopInformationBean> goodsBeans = sparseArrayToList();
        //使用Gson把List集合装换成json的String数据
        String json = new Gson().toJson(goodsBeans);
        //把文本保存在sp中
        CacheUtils.putString(mContext,JSON_CART,json);
    }

    private ArrayList<ShopInformationBean> sparseArrayToList() {
        ArrayList<ShopInformationBean> goodsBeans = new ArrayList<>();
        for(int i = 0; i < sparseArray.size(); i++) {
          ShopInformationBean goodsBean = sparseArray.get(i);
            goodsBeans.add(goodsBean);
        }
        return goodsBeans;
    }
}

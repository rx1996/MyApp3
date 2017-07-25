package myapplication.liangcang.shopcat.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import myapplication.liangcang.shop.bean.ShopInformationBean;

/**
 * Created by Administrator on 2017/7/23.
 */

public class CartStorage {

    public static final String JSON_CART = "json_cart";
    public static CartStorage instance;
    private static Context mContext;
    /**
     * 数据存储在内存中
     */
    private SparseArray<ShopInformationBean> sparseArray;

    private CartStorage() {
        //初始化集合
        sparseArray = new SparseArray<>();
        listTosparseArray();
    }

    private void listTosparseArray() {
        //得到所有数据
        ArrayList<ShopInformationBean> datas = getAllData();
        for (int i = 0; i < datas.size(); i++) {
            ShopInformationBean ShopInformationBean = datas.get(i);
            sparseArray.put(Integer.parseInt(ShopInformationBean.getData().getItems().getGoods_id()), ShopInformationBean);

        }
    }

    /**
     * 得到所有数据
     *
     * @return
     */
    public ArrayList<ShopInformationBean> getAllData() {
        return getLocalData();
    }

    /**
     * 得到本地缓存的数据
     *
     * @return
     */
    private ArrayList<ShopInformationBean> getLocalData() {
        ArrayList<ShopInformationBean> datas = new ArrayList<>();
        //json数据
        String saveJson = CacheUtils.getString(mContext, JSON_CART);
        if (!TextUtils.isEmpty(saveJson)) {
            //把保存的json数据解析成ArrayList数组
            datas = new Gson().fromJson(saveJson, new TypeToken<ArrayList<ShopInformationBean>>() {
            }.getType());
        }
        return datas;
    }


    /**
     * 得到CartStorage
     *
     * @return
     */
    public static CartStorage getInstance(Context context) {
        if (instance == null) {
            mContext = context;
            //加上锁
            synchronized (CartStorage.class) {
                if (instance == null) {
                    instance = new CartStorage();
                }
            }
        }

        return instance;
    }

    /**
     * 添加数据
     *
     * @param bean
     */
    public void addData(ShopInformationBean bean) {
        //查看内容中是否存在
        ShopInformationBean temp = sparseArray.get(Integer.parseInt(bean.getData().getItems().getGoods_id()));
        if (temp != null) {
            //存在，就修改
            temp.getData().getItems().setNumber(bean.getData().getItems().getNumber()+temp.getData().getItems().getNumber());
        } else {
            //如果不存在，保存到内存中
            temp = bean;
        }

        //内存中更新
        sparseArray.put(Integer.parseInt(temp.getData().getItems().getGoods_id()), temp);


        //同步到本地
        commit();


    }


    /**
     * 添加数据
     *
     * @param bean
     */
    public void deleteData(ShopInformationBean bean) {
        //内存中更新
        sparseArray.delete(Integer.parseInt(bean.getData().getItems().getGoods_id()));
        //同步到本地
        commit();

    }


    /**
     * 添加数据
     *
     * @param bean
     */
    public void updateData(ShopInformationBean bean) {
        //内存中更新
        sparseArray.put(Integer.parseInt(bean.getData().getItems().getGoods_id()),bean);
        //同步到本地
        commit();

    }

    /**
     * 在本地保存一份
     */
    private void commit() {
        //把SparseArray 转换成List集合
        ArrayList<ShopInformationBean> goodsBeens = sparseArrayToList();
        //使用Gson把List集合转换成json的String数据
        String json = new Gson().toJson(goodsBeens);
        //把文本保存到sp中
        CacheUtils.putString(mContext,JSON_CART,json);


    }

    private ArrayList<ShopInformationBean> sparseArrayToList() {
        ArrayList<ShopInformationBean> goodsBeens = new ArrayList<>();
        for (int i = 0; i < sparseArray.size(); i++) {
            ShopInformationBean ShopInformationBean = sparseArray.valueAt(i);
            goodsBeens.add(ShopInformationBean);
        }
        return goodsBeens;
    }
}

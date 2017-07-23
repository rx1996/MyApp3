package myapplication.liangcang.shopcat.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/23.
 */

public class GoodsBean implements Serializable {
    private String goods_name;
    private String goods_id;
    private String goods_image;
    private String price;
    private String sku_info;
    private int number = 1;

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_image() {
        return goods_image;
    }

    public void setGoods_image(String goods_image) {
        this.goods_image = goods_image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSku_info() {
        return sku_info;
    }

    public void setSku_info(String sku_info) {
        this.sku_info = sku_info;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "goods_name='" + goods_name + '\'' +
                ", goods_id='" + goods_id + '\'' +
                ", goods_image='" + goods_image + '\'' +
                ", price='" + price + '\'' +
                ", sku_info='" + sku_info + '\'' +
                ", number=" + number +
                '}';
    }
}

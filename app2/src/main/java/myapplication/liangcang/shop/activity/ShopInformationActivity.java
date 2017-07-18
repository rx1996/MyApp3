package myapplication.liangcang.shop.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myapplication.liangcang.R;
import myapplication.liangcang.base.BaseActivity;
import myapplication.liangcang.shop.bean.GiftParentInfo;
import myapplication.liangcang.shop.bean.ShopInformationBean;
import okhttp3.Call;

public class ShopInformationActivity extends BaseActivity {

    @Bind(R.id.banner)
    ImageView banner;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    @Bind(R.id.iv_like_image)
    ImageView ivLikeImage;
    @Bind(R.id.tv_like_num)
    TextView tvLikeNum;
    @Bind(R.id.iv_fenxiang)
    ImageView ivFenxiang;
    @Bind(R.id.ll)
    LinearLayout ll;
    @Bind(R.id.tv_price)
    TextView tvPrice;
    @Bind(R.id.ll_1)
    LinearLayout ll1;
    @Bind(R.id.ll_2)
    LinearLayout ll2;
    @Bind(R.id.iv_image)
    ImageView ivImage;
    @Bind(R.id.tv_name2)
    TextView tvName2;
    @Bind(R.id.ll_3)
    LinearLayout ll3;
    @Bind(R.id.rb_gushi)
    RadioButton rbGushi;
    @Bind(R.id.rb_chanpin)
    RadioButton rbChanpin;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.activity_shop_information)
    RelativeLayout activityShopInformation;
    private String url;
    private int id;
    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        url = "http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id="+id+"&sig=BF287AF953103F390674E73DDA18CFD8%7C639843030233268&v=1.0";
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }
    class MyStringCallback extends StringCallback {
        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("TAG", "请求失败==" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e("TAG", "请求成功==");
            processData(response);
        }
    }

    private void processData(String json) {
        ShopInformationBean bean = JSON.parseObject(json, ShopInformationBean.class);
        tvPrice.setText("￥"+bean.getData().getItems().getPrice());
        tvName.setText(bean.getData().getItems().getOwner_name());
        tvLikeNum.setText(bean.getData().getItems().getLike_count());
        Glide.with(this)
                .load(bean.getData().getItems().getGoods_image())
                .placeholder(R.drawable.ic_login_logo)
                .error(R.drawable.ic_login_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImage);
//        Glide.with(this)
//                .load(bean.getData().getItems().getImages_item())
//                .placeholder(R.drawable.ic_login_logo)
//                .error(R.drawable.ic_login_logo)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(banner);
        tvName2.setText(bean.getData().getItems().getOwner_name());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_information;
    }

    @OnClick({R.id.rb_gushi, R.id.rb_chanpin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_gushi:
                break;
            case R.id.rb_chanpin:
                break;
        }
    }
}

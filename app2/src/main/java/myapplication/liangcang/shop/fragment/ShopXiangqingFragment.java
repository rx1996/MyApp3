package myapplication.liangcang.shop.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;
import myapplication.liangcang.base.BaseFragment;
import myapplication.liangcang.shop.bean.ShopInformationBean;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/7/18.
 */

public class ShopXiangqingFragment extends BaseFragment {

    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_desc)
    TextView tvDesc;
    private Bundle bundle;
    private String url;

    private LinearLayout ll;
    private LinearLayout.LayoutParams layoutParams;
    private ImageView imageView;
    LinearLayout.LayoutParams layoutParams1;
    TextView tvZhuyiShixiang;

    @Override
    public View initView() {
        ll = new LinearLayout(mContext);
        ll.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setBackgroundColor(Color.GRAY);
        bundle = this.getArguments();
        url = bundle.getString("url");
        return ll;
    }

    @Override
    public void initData() {
        super.initData();
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
            Log.e("TAG", "商品详情请求成功==");
            processData(response);
        }
    }

    private void processData(String json) {
        ShopInformationBean bean = JSON.parseObject(json, ShopInformationBean.class);
        for (int i = 0; i < bean.getData().getItems().getGoods_info().size(); i++) {
            imageView = new ImageView(mContext);
            layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            ll.addView(imageView, layoutParams);
            Glide.with(this)
                    .load(bean.getData().getItems().getGoods_info().get(i).getContent().getImg())
                    .placeholder(R.drawable.ic_login_logo)
                    .error(R.drawable.ic_login_logo)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
        }
        tvZhuyiShixiang = new TextView(mContext);
        layoutParams1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll.addView(tvZhuyiShixiang, layoutParams1);
        tvZhuyiShixiang.setText(bean.getData().getItems().getGoods_desc());
        View view = View.inflate(mContext, R.layout.fragment_xiangqing, null);
        ButterKnife.bind(this, view);
        tvName.setText(bean.getData().getItems().getBrand_info().getBrand_name());
        tvDesc.setText(bean.getData().getItems().getBrand_info().getBrand_desc());
        ll.addView(view);
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

package myapplication.liangcang.shop.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
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

public class GouwuXuzhiFragment extends BaseFragment {
    @Bind(R.id.tv_gouwu_xuzhi)
    TextView tvGouwuXuzhi;
    @Bind(R.id.bt_shouhou_xuzhi)
    Button btShouhouXuzhi;

    private Bundle bundle;
    private String url;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_gouwu_xuzhi, null);
        ButterKnife.bind(this, view);
        bundle = this.getArguments();
        url = bundle.getString("url");
        return view;
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
        tvGouwuXuzhi.setText(bean.getData().getItems().getGood_guide().getContent());
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

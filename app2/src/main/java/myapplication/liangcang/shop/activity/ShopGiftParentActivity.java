package myapplication.liangcang.shop.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import myapplication.liangcang.R;
import myapplication.liangcang.activity.LoginActivity;
import myapplication.liangcang.base.BaseActivity;
import myapplication.liangcang.shop.adapter.ShopGiftParentAdapter;
import myapplication.liangcang.shop.bean.GiftParentInfo;
import myapplication.liangcang.utils.Constants;
import okhttp3.Call;

public class ShopGiftParentActivity extends BaseActivity {

    @Bind(R.id.tv_title_biaoti)
    TextView tvTitleBiaoti;
    @Bind(R.id.iv_special)
    ImageView ivSpecial;
    @Bind(R.id.base_shop)
    ImageView baseShop;
    @Bind(R.id.rl_shuaixuan)
    RelativeLayout rlShuaixuan;
    @Bind(R.id.holiday_recyclerView)
    RecyclerView holidayRecyclerView;
    @Bind(R.id.activity_shop_gift_holiday)
    LinearLayout activityShopGiftHoliday;
    private ShopGiftParentAdapter adapter;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        getDataFromNet(Constants.BASE_URL_SHOP_GIF_PARENT);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_gift_parent;
    }


    @OnClick({R.id.iv_special, R.id.base_shop, R.id.rl_shuaixuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_special:
                finish();
                break;
            case R.id.base_shop:
                startActivity(new Intent(ShopGiftParentActivity.this, LoginActivity.class));
                break;
            case R.id.rl_shuaixuan:
                Toast.makeText(ShopGiftParentActivity.this, "筛选", Toast.LENGTH_SHORT).show();
                break;
        }
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
        GiftParentInfo giftParentInfo = JSON.parseObject(json, GiftParentInfo.class);
        List<GiftParentInfo.DataBean.ItemsBean> datas =giftParentInfo.getData().getItems();
        adapter = new ShopGiftParentAdapter(this, datas);
        holidayRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new GridLayoutManager(this, 2);
        holidayRecyclerView.setLayoutManager(layout);
        baseShop.setVisibility(View.VISIBLE);

    }
}

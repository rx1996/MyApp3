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
import myapplication.liangcang.shop.adapter.ShopGiftchildAdapter;
import myapplication.liangcang.shop.bean.ShopGiftChildInfo;
import myapplication.liangcang.utils.Constants;
import okhttp3.Call;

public class ShopGiftChildActivity extends BaseActivity {

    @Bind(R.id.tv_title_biaoti)
    TextView tvTitleBiaoti;
    @Bind(R.id.iv_special)
    ImageView ivSpecial;
    @Bind(R.id.base_shop)
    ImageView baseShop;
    @Bind(R.id.rl_shuaixuan)
    RelativeLayout rlShuaixuan;
    @Bind(R.id.gift_recyclerView)
    RecyclerView giftRecyclerView;
    @Bind(R.id.activity_shop_gift)
    LinearLayout activityShopGift;
    private ShopGiftchildAdapter adapter;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        getDataFromNet(Constants.BASE_URL_SHOP_GIF_CHILD);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_gift_child;
    }

    @OnClick({R.id.iv_special, R.id.base_shop, R.id.rl_shuaixuan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_special:
                finish();
                break;
            case R.id.base_shop:
                startActivity(new Intent(ShopGiftChildActivity.this, LoginActivity.class));
                break;
            case R.id.rl_shuaixuan:
                Toast.makeText(ShopGiftChildActivity.this, "筛选", Toast.LENGTH_SHORT).show();
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
        ShopGiftChildInfo shopGiftChildInfo = JSON.parseObject(json, ShopGiftChildInfo.class);
        List<ShopGiftChildInfo.DataBean.ItemsBean> datas = shopGiftChildInfo.getData().getItems();
        adapter = new ShopGiftchildAdapter(this, datas);
        giftRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new GridLayoutManager(this, 2);
        giftRecyclerView.setLayoutManager(layout);
        baseShop.setVisibility(View.VISIBLE);

    }
}

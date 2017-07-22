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

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import myapplication.liangcang.R;
import myapplication.liangcang.activity.LoginActivity;
import myapplication.liangcang.base.BaseActivity;
import myapplication.liangcang.common.ShopCatActivity;
import myapplication.liangcang.shop.adapter.ShopGiftAdapter;
import myapplication.liangcang.shop.bean.ShopGiftInfo;
import myapplication.liangcang.utils.Constants;
import okhttp3.Call;

public class ShopGiftActivity extends BaseActivity {


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
    private ShopGiftAdapter adapter;

    @Override
    public void initListener() {
        ivSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        baseShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(mContext,LoginActivity.class));
                startActivity(new Intent(ShopGiftActivity.this,ShopCatActivity.class));
            }
        });
    }

    @Override
    public void initData() {
        getDataFromNet(Constants.BASE_URL_SHOP_GIF);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_gift;
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
        ShopGiftInfo shopGiftInfo = JSON.parseObject(json, ShopGiftInfo.class);
        List<ShopGiftInfo.DataBean.ItemsBean> datas = shopGiftInfo.getData().getItems();
        adapter = new ShopGiftAdapter(this, datas);
        giftRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new GridLayoutManager(this, 2);
        giftRecyclerView.setLayoutManager(layout);
        baseShop.setVisibility(View.VISIBLE);

    }
}

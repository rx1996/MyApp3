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
import butterknife.OnClick;
import myapplication.liangcang.R;
import myapplication.liangcang.activity.LoginActivity;
import myapplication.liangcang.base.BaseActivity;
import myapplication.liangcang.shop.adapter.TypeErjiadapter;
import myapplication.liangcang.shop.bean.TypeErjiInfo;
import okhttp3.Call;

public class TypeErjiActivity extends BaseActivity {


    @Bind(R.id.tv_title_biaoti)
    TextView tvTitleBiaoti;
    @Bind(R.id.iv_special)
    ImageView ivSpecial;
    @Bind(R.id.base_shop)
    ImageView baseShop;
    @Bind(R.id.rl_shuaixuan)
    RelativeLayout rlShuaixuan;
    @Bind(R.id.erji_recyclerView)
    RecyclerView erjiRecyclerView;
    @Bind(R.id.activity_type_erji)
    LinearLayout activityTypeErji;
    private int id;
    private String name;

    private String url;
    private TypeErjiadapter adapter;

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        super.initView();
        tvTitleBiaoti.setText("商店");
    }

    @Override
    public void initData() {
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        //name = getIntent().getStringExtra("name");
        if(id<100) {
            url = "http://mobile.iliangcang.com/goods/goodsShare?app_key=" +
                    "Android&cat_code=00"+id+"&count=10&coverId=1&page=1&sig="
                    + "6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
        }else {
            url = "http://mobile.iliangcang.com/goods/goodsShare?app_key=" +
                    "Android&cat_code=0"+id+"&count=10&coverId=1&page=1&sig="
                    + "6E1DEF1DAFF84909ECD98F32FE6CDAD5%7C536890620070968&v=1.0";
        }

        getDataFromNet(url);
    }

    private void getDataFromNet(String url) {

        OkHttpUtils
                .get()
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
            Log.e("TAGW", "请求成功==");
            processData(response);

        }
    }

    private void processData(String json) {
        TypeErjiInfo typeErjiInfo = JSON.parseObject(json, TypeErjiInfo.class);
        List<TypeErjiInfo.DataBean.ItemsBean> datas = typeErjiInfo.getData().getItems();
        adapter = new TypeErjiadapter(this,datas);
        erjiRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new GridLayoutManager(this, 2);
        erjiRecyclerView.setLayoutManager(layout);
        baseShop.setVisibility(View.VISIBLE);
    }


    @Override
    public int getLayoutId() {
        return R.layout.activity_type_erji;
    }



    @OnClick({R.id.iv_special, R.id.base_shop, R.id.rl_shuaixuan, R.id.erji_recyclerView})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_special:
                finish();
                break;
            case R.id.base_shop:
                startActivity(new Intent(TypeErjiActivity.this, LoginActivity.class));
                break;
            case R.id.rl_shuaixuan:
                showToast("筛选");
                break;
           case R.id.erji_recyclerView:
                break;
        }
    }
}

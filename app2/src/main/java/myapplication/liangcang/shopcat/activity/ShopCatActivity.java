package myapplication.liangcang.shopcat.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import myapplication.liangcang.R;
import myapplication.liangcang.base.BaseActivity;
import myapplication.liangcang.common.MyApplication;
import myapplication.liangcang.shop.bean.ShopInformationBean;
import myapplication.liangcang.shopcat.adapter.ShoppingCartAdapter;
import myapplication.liangcang.shopcat.utils.CartStorage;

public class ShopCatActivity extends BaseActivity {

    @Bind(R.id.iv_shopcat_back)
    ImageView ivShopcatBack;
    @Bind(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.tv_manjian_price)
    TextView tvManjianPrice;
    @Bind(R.id.tv_zhekou_price)
    TextView tvZhekouPrice;
    @Bind(R.id.tv_baozhuang_price)
    TextView tvBaozhuangPrice;
    @Bind(R.id.tv_baoyou_price)
    TextView tvBaoyouPrice;
    @Bind(R.id.checkbox_all)
    CheckBox checkboxAll;
    @Bind(R.id.tv_shopcart_price)
    TextView tvShopcartPrice;
    @Bind(R.id.btn_check_out)
    Button btnCheckOut;
    @Bind(R.id.ll_check_all)
    LinearLayout llCheckAll;
    @Bind(R.id.activity_shop_cat)
    LinearLayout activityShopCat;
    private ArrayList<ShopInformationBean> datas;
    private ShoppingCartAdapter adapter;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        datas = CartStorage.getInstance(MyApplication.getContext()).getAllData();
        if(datas != null && datas.size() >0){
            adapter = new ShoppingCartAdapter(ShopCatActivity.this,datas,checkboxAll,tvShopcartPrice,btnCheckOut);
            recyclerview.setAdapter(adapter);
            //设置布局管理器
            recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_cat;
    }


    @OnClick({R.id.iv_shopcat_back, R.id.tv_shopcart_edit, R.id.checkbox_all, R.id.btn_check_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_shopcat_back:
                finish();
                break;
            case R.id.tv_shopcart_edit:
                Toast.makeText(ShopCatActivity.this, "编辑", Toast.LENGTH_SHORT).show();
                break;
            case R.id.checkbox_all:
//                Toast.makeText(ShopCatActivity.this, "全选", Toast.LENGTH_SHORT).show();
                boolean checked = checkboxAll.isChecked();
                adapter.checkAll_none(checked);
                //重新计算价格
                adapter.showTotalPrice();
                break;
            case R.id.btn_check_out:
                Toast.makeText(ShopCatActivity.this, "结算", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

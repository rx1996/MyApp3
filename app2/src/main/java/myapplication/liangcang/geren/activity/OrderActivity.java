package myapplication.liangcang.geren.activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;
import myapplication.liangcang.base.BaseActivity;

public class OrderActivity extends BaseActivity {

    @Bind(R.id.iv_shopcat_back)
    ImageView ivShopcatBack;
    @Bind(R.id.tv_shopcart_edit)
    TextView tvShopcartEdit;
    @Bind(R.id.rb_quanbu)
    RadioButton rbQuanbu;
    @Bind(R.id.rb_daifukuan)
    RadioButton rbDaifukuan;
    @Bind(R.id.rb_daifahuo)
    RadioButton rbDaifahuo;
    @Bind(R.id.rb_yifahuo)
    RadioButton rbYifahuo;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.activity_order)
    LinearLayout activityOrder;

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

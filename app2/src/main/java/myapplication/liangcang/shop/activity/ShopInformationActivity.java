package myapplication.liangcang.shop.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myapplication.liangcang.R;
import myapplication.liangcang.activity.MainActivity;
import myapplication.liangcang.base.BaseActivity;
import myapplication.liangcang.base.BaseFragment;
import myapplication.liangcang.daren.fragment.DarenFragment;
import myapplication.liangcang.fenxiang.fragment.FenxiangFragment;
import myapplication.liangcang.geren.fragment.GerenFragment;
import myapplication.liangcang.shop.bean.GiftParentInfo;
import myapplication.liangcang.shop.bean.ShopInformationBean;
import myapplication.liangcang.shop.fragment.ShopChanpinFragment;
import myapplication.liangcang.shop.fragment.ShopFragment;
import myapplication.liangcang.shop.fragment.ShopGushiFragment;
import myapplication.liangcang.utils.GlideImageLoader;
import myapplication.liangcang.zazhi.fragment.ZaZhiFragment;
import okhttp3.Call;

public class ShopInformationActivity extends BaseActivity {

    @Bind(R.id.banner)
    Banner banner;
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
    ScrollView activityShopInformation;
    private String url;
    private int id;
    private Fragment tempFragment;
    private int position = 0;
    private ArrayList<BaseFragment> fragments;
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
        initFragment();

        //设置RadioGroup的选中监听
        rgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());

        //设置默认选择首页
        rgMain.check(R.id.rb_gushi);
    }
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_gushi:
                    position = 0;
                    break;
                case R.id.rb_chanpin:
                    position = 1;
                    break;

            }
            Fragment currentFragment = fragments.get(position);
            switchFragment(currentFragment);
        }
    }
    private void switchFragment(Fragment currentFragment) {
        if(currentFragment != tempFragment){//不是同一个
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if(!currentFragment.isAdded()){

                //把之前的隐藏
                if(tempFragment!= null){
                    ft.hide(tempFragment);
                }
                //把现在的添加
                ft.add(R.id.frameLayout,currentFragment);

            }else{
                //把之前的隐藏
                if(tempFragment!= null){
                    ft.hide(tempFragment);
                }
                //把当前的显示
                ft.show(currentFragment);
            }


            //提交
            ft.commit();

            tempFragment = currentFragment;

        }

    }
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ShopGushiFragment());
        fragments.add(new ShopChanpinFragment());
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
        List<String> images = new ArrayList<>();
        for(int i = 0; i < bean.getData().getItems().getImages_item().size(); i++) {
            images.add(bean.getData().getItems().getImages_item().get(i));
        }
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        tvName2.setText(bean.getData().getItems().getOwner_name());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_information;
    }
}

package myapplication.liangcang.shop.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myapplication.liangcang.R;
import myapplication.liangcang.base.BaseActivity;
import myapplication.liangcang.base.BaseFragment;
import myapplication.liangcang.common.MyApplication;
import myapplication.liangcang.shop.bean.ShopInformationBean;
import myapplication.liangcang.shop.fragment.GouwuXuzhiFragment;
import myapplication.liangcang.shop.fragment.ShopXiangqingFragment;
import myapplication.liangcang.shopcat.utils.CartStorage;
import myapplication.liangcang.utils.GlideImageLoader;
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
    RelativeLayout activityShopInformation;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.iv_service)
    ImageView ivService;
    @Bind(R.id.tv_join_shop_cat)
    TextView tvJoinShopCat;
    @Bind(R.id.tv_buy)
    TextView tvBuy;
    private String url;
    private int id;
    private Fragment tempFragment;
    private int position = 0;
    private ArrayList<BaseFragment> fragments;

    private ShopXiangqingFragment xiangqingFragment;
    private GouwuXuzhiFragment xuzhiFragment;
    private ShopInformationBean bean;

    private PopupWindow mPopupWindow;

    ImageView iv_goumai_image;
    ImageView iv_finish_image;
    TextView tv_name;
    TextView tv_info;
    TextView tv_price;
    TextView tv_type_name1;
    TextView tv_type_name2;

    @Override
    public void initListener() {
        //设置RadioGroup的选中监听
        rgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
    }

    @Override
    public void initView() {
        super.initView();
        initFragment();

    }

    @Override
    public void initData() {
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        url = "http://mobile.iliangcang.com/goods/goodsDetail?app_key=Android&goods_id=" + id + "&sig=1EC88F51A8AC94B7B3E2979EAF4CE171%7C720047010493468&v=1.0";
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

    FragmentTransaction ft;

    private void processData(String json) {
        bean = JSON.parseObject(json, ShopInformationBean.class);
        tvPrice.setText("￥" + bean.getData().getItems().getPrice());
        tvName.setText(bean.getData().getItems().getOwner_name());
        tvLikeNum.setText(bean.getData().getItems().getLike_count());
        Glide.with(this)
                .load(bean.getData().getItems().getHeadimg())
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
        for (int i = 0; i < bean.getData().getItems().getImages_item().size(); i++) {
            images.add(bean.getData().getItems().getImages_item().get(i));
        }
        banner.setImages(images).setImageLoader(new GlideImageLoader()).start();
        tvName2.setText(bean.getData().getItems().getOwner_name());


        bundle.putString("url", url);
        Log.e("TAG", "url==" + bean.getData().getItems().getGoods_url());
        bundle.putString("goods_id", bean.getData().getItems().getGoods_id());


        //设置默认选择首页
        rgMain.check(R.id.rb_gushi);

    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
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
        if (currentFragment != tempFragment) {//不是同一个
            ft = getSupportFragmentManager().beginTransaction();

            if (!currentFragment.isAdded()) {

                //把之前的隐藏
                if (tempFragment != null) {
                    ft.hide(tempFragment);
                }
                //把现在的添加
                ft.add(R.id.frameLayout, currentFragment);

            } else {
                //把之前的隐藏
                if (tempFragment != null) {
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
        xiangqingFragment = new ShopXiangqingFragment();
        xuzhiFragment = new GouwuXuzhiFragment();
        bundle = new Bundle();


        xiangqingFragment.setArguments(bundle);
        xuzhiFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().commit();
        fragments.add(xiangqingFragment);
        fragments.add(xuzhiFragment);

    }

    Bundle bundle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shop_information;
    }


    @OnClick({R.id.iv_service, R.id.tv_join_shop_cat, R.id.tv_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_service:
                Toast.makeText(ShopInformationActivity.this, "客服", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_join_shop_cat:
//                Toast.makeText(ShopInformationActivity.this, "加入购物车", Toast.LENGTH_SHORT).show();
//                CartStorage.getInstance(MyApplication.getContext()).addData();
                setPopupWindow();

                break;
            case R.id.tv_buy:
                Toast.makeText(ShopInformationActivity.this, "直接购卖", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    TagFlowLayout flowlayout1;
    TagFlowLayout flowlayout2;

    private void setPopupWindow() {
        List<ShopInformationBean.DataBean.ItemsBean.SkuInfoBean> sku_info = bean.getData().getItems().getSku_info();
        final View popupView = LayoutInflater.from(this).inflate(R.layout.pop_goumai, null);
        iv_goumai_image = (ImageView) popupView.findViewById(R.id.iv_goumai_image);
        iv_finish_image = (ImageView) popupView.findViewById(R.id.iv_finish_image);
        tv_name = (TextView) popupView.findViewById(R.id.tv_name);
        tv_info = (TextView) popupView.findViewById(R.id.tv_info);
        tv_price = (TextView) popupView.findViewById(R.id.tv_price);
        flowlayout1 = (TagFlowLayout) popupView.findViewById(R.id.id_flowlayout1);
        flowlayout2 = (TagFlowLayout) popupView.findViewById(R.id.id_flowlayout2);
        tv_type_name1 = (TextView) popupView.findViewById(R.id.tv_type_name1);
        tv_type_name2 = (TextView) popupView.findViewById(R.id.tv_type_name2);
        if (sku_info.size() == 1) {
            setInfo1(sku_info);
        } else {
            setInfo1(sku_info);
            setInfo2(sku_info);
        }

        tv_name.setText(bean.getData().getItems().getOwner_name());
        tv_info.setText(bean.getData().getItems().getGoods_name());
        tv_price.setText("￥" + bean.getData().getItems().getPrice());
        iv_finish_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        mPopupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        mPopupWindow.setAnimationStyle(R.style.take_photo_anim);
        mPopupWindow.showAtLocation(popupView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    private void setInfo1(List<ShopInformationBean.DataBean.ItemsBean.SkuInfoBean> sku_info) {
        tv_type_name1.setText(sku_info.get(0).getType_name());
        final String[] mVals = new String[sku_info.get(0).getAttrList().size()];
        final String[] urls = new String[sku_info.get(0).getAttrList().size()];
        for (int i = 0; i < sku_info.get(0).getAttrList().size(); i++) {
            mVals[i] = sku_info.get(0).getAttrList().get(i).getAttr_name();
            urls[i] = sku_info.get(0).getAttrList().get(i).getImg_path();
        }
        final LayoutInflater mInflater = LayoutInflater.from(this);
        flowlayout1.setAdapter(new TagAdapter<String>(mVals) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv, flowlayout1, false);
                tv.setText(s);
                return tv;
            }

            @Override
            public boolean setSelected(int position, String s) {
                return s.equals(mVals[0]);
            }
        });
        flowlayout1.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                UIUtils.showToast(mVals[position]);
                if (!urls[position].equals("")) {
                    Glide.with(ShopInformationActivity.this)
                            .load(urls[position])
                            .placeholder(R.drawable.ic_login_logo)
                            .error(R.drawable.ic_login_logo)
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(iv_goumai_image);
                }

                return true;
            }
        });

    }

    private void setInfo2(List<ShopInformationBean.DataBean.ItemsBean.SkuInfoBean> sku_info) {
        tv_type_name2.setVisibility(View.VISIBLE);
        tv_type_name2.setText(sku_info.get(1).getType_name());
        flowlayout2.setVisibility(View.VISIBLE);


        final String[] mVals = new String[sku_info.get(1).getAttrList().size()];
        final String[] urls = new String[sku_info.get(1).getAttrList().size()];

        for (int i = 0; i < sku_info.get(1).getAttrList().size(); i++) {
            mVals[i] = sku_info.get(1).getAttrList().get(i).getAttr_name();
            urls[i] = sku_info.get(1).getAttrList().get(i).getImg_path();

        }


        final LayoutInflater mInflater = LayoutInflater.from(this);
        flowlayout2.setAdapter(new TagAdapter<String>(mVals) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.tv, flowlayout2, false);
                tv.setText(s);
                return tv;
            }

            //默认选中哪一条
            @Override
            public boolean setSelected(int position, String s) {
                return s.equals(mVals[0]);
            }
        });


        flowlayout2.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
//                UIUtils.showToast(mVals[position]);
//                if(!urls[position].equals("")) {
//                    Picasso.with(PurchaseActivity.this)
//                            .load(urls[position])
//                            .placeholder(R.drawable.atguigu_logo)
//                            .error(R.drawable.atguigu_logo)
//                            .into(iv_tupian);
//                }
                return true;
            }
        });
    }
}

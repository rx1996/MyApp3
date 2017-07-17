package myapplication.liangcang.daren.activity;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import myapplication.liangcang.R;
import myapplication.liangcang.base.BaseActivity;
import myapplication.liangcang.base.BaseFragment;
import myapplication.liangcang.daren.bean.DarenxiangqingInfo;
import myapplication.liangcang.daren.fragment.FensiFragment;
import myapplication.liangcang.daren.fragment.GuanzhuFragment;
import myapplication.liangcang.daren.fragment.TuijianFragment;
import myapplication.liangcang.daren.fragment.XihuanFragment;
import myapplication.liangcang.utils.Constants;
import okhttp3.Call;

public class DarenActicity extends BaseActivity {


    @Bind(R.id.tv_title_biaoti)
    TextView tvTitleBiaoti;
    @Bind(R.id.iv_special)
    ImageView ivSpecial;
    @Bind(R.id.base_shop)
    ImageView baseShop;
    @Bind(R.id.daren_icon)
    ImageView darenIcon;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.tv_duty)
    TextView tvDuty;
    @Bind(R.id.btn_guanzhu)
    Button btnGuanzhu;
    @Bind(R.id.btn_sixin)
    Button btnSixin;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.xihuan)
    RadioButton xihuan;
    @Bind(R.id.tuijian)
    RadioButton tuijian;
    @Bind(R.id.guanzhu)
    RadioButton guanzhu;
    @Bind(R.id.fensi)
    RadioButton fensi;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.frameLayout)
    FrameLayout frameLayout;
    @Bind(R.id.activity_daren_acticity)
    LinearLayout activityDarenActicity;
    private ArrayList<BaseFragment> fragments;
    /**
     * 选择某个Fragment的位置
     */
    private int position = 0;
    /**
     * 之前显示过的Fragment
     */
    private Fragment tempFragment;


    @Override
    public void initData() {
        tvTitleBiaoti.setText(getIntent().getStringExtra("name"));
        tvName.setText(getIntent().getStringExtra("name"));
        tvDuty.setText(getIntent().getStringExtra("duty"));
        Uri uri = getIntent().getData();
        Glide.with(DarenActicity.this).load(uri)
                .placeholder(R.drawable.ic_login_logo).
                error(R.drawable.ic_login_logo).
                diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(darenIcon);
        //初始多个页面对应的Fragment并且设置默认的Fragment页面
        initFragment();
        //设置RadioGroup的选中监听
        rgMain.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置默认选择首页
        rgMain.check(R.id.xihuan);
        getDataFromNet(Constants.BASE_URL_DAREN_XIANGQING);


    }
    private void getDataFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }

    private class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("HOMETAG", "请求失败==" + e.getMessage());
        }
        @Override
        public void onResponse(String response, int id) {
            Log.e("HOMETAG", "请求成功==");
            processData(response);
        }
    }

    private void processData(String json) {
        DarenxiangqingInfo datas = JSON.parseObject(json, DarenxiangqingInfo.class);
        String like = getResources().getString(R.string.daren_xihuan);
        String likecount = datas.getData().getItems().getLike_count();
        xihuan.setText(String.format(like,likecount));

        String recommendation = getResources().getString(R.string.daren_tuijian);
        String recommendationcount = datas.getData().getItems().getRecommendation_count();
        xihuan.setText(String.format(recommendation,recommendationcount));

        String following = getResources().getString(R.string.daren_guanzhu);
        String followingcount = datas.getData().getItems().getFollowing_count();
        xihuan.setText(String.format(following,followingcount));

        String followed = getResources().getString(R.string.daren_fensi);
        String followedcount = datas.getData().getItems().getFollowed_count();
        xihuan.setText(String.format(followed,followedcount));



    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_daren_acticity;
    }


    @OnClick({R.id.iv_special, R.id.btn_guanzhu, R.id.btn_sixin, R.id.xihuan, R.id.tuijian, R.id.guanzhu, R.id.fensi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_special:
                finish();
                break;
            case R.id.btn_guanzhu:
                break;
            case R.id.btn_sixin:
                break;
            case R.id.xihuan:
                break;
            case R.id.tuijian:
                break;
            case R.id.guanzhu:
                break;
            case R.id.fensi:
                break;
        }
    }

    @Override
    public void initListener() {

    }




    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.xihuan:
                    position = 0;
                    break;
                case R.id.tuijian:
                    position = 1;
                    break;
                case R.id.guanzhu:
                    position = 2;
                    break;
                case R.id.fensi:
                    position = 3;
                    break;

            }
            Fragment currentFragment = fragments.get(position);
            switchFragment(currentFragment);
        }
    }
    /**
     * 要显示的Fragment
     * @param currentFragment
     */
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
        fragments.add(new XihuanFragment());
        fragments.add(new TuijianFragment());
        fragments.add(new GuanzhuFragment());
        fragments.add(new FensiFragment());
//        //一进入要显示的Fragment
//        switchFragment(fragments.get(position));
    }


}

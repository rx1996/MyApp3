package myapplication.liangcang.geren.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myapplication.liangcang.R;
import myapplication.liangcang.activity.LoginActivity;
import myapplication.liangcang.base.BaseFragment;
import myapplication.liangcang.geren.activity.OrderActivity;

/**
 * Created by zhouzhou on 2017/7/6.
 */

public class GerenFragment extends BaseFragment {


    @Bind(R.id.iv_setting)
    ImageView ivSetting;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.iv_photo_image)
    ImageView ivPhotoImage;
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.ll_dingdan)
    LinearLayout llDingdan;
    @Bind(R.id.ll_hongbao)
    LinearLayout llHongbao;
    @Bind(R.id.rl1)
    RelativeLayout rl1;
    @Bind(R.id.ll_wish)
    LinearLayout llWish;
    @Bind(R.id.ll_news)
    LinearLayout llNews;
    @Bind(R.id.ll_address)
    LinearLayout llAddress;
    @Bind(R.id.ll_service)
    LinearLayout llService;

    @Override
    public void initTitle() {
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_geren, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @OnClick({R.id.iv_setting, R.id.tv_name, R.id.ll_dingdan, R.id.ll_hongbao, R.id.ll_wish, R.id.ll_news, R.id.ll_address, R.id.ll_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_setting:
                Toast.makeText(mContext, "设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_name:
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.ll_dingdan:
                startActivity(new Intent(mContext,OrderActivity.class));
                break;
            case R.id.ll_hongbao:
                break;
            case R.id.ll_wish:
                break;
            case R.id.ll_news:
                break;
            case R.id.ll_address:
                break;
            case R.id.ll_service:
                break;
        }
    }
}

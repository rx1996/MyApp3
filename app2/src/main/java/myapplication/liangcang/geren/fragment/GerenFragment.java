package myapplication.liangcang.geren.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;
import myapplication.liangcang.base.BaseFragment;

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
    @Bind(R.id.tv_my_dingdan)
    TextView tvMyDingdan;
    @Bind(R.id.tv_my_hongbao)
    TextView tvMyHongbao;
    @Bind(R.id.rl1)
    RelativeLayout rl1;
    @Bind(R.id.tv_wish)
    TextView tvWish;
    @Bind(R.id.tv_news)
    TextView tvNews;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.tv_service)
    TextView tvService;

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
}

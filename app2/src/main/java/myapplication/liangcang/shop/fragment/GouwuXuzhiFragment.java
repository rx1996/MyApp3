package myapplication.liangcang.shop.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import myapplication.liangcang.base.BaseFragment;

/**
 * Created by Administrator on 2017/7/18.
 */

public class GouwuXuzhiFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("产品内容");
    }

    @Override
    public void initTitle() {

    }
}

package myapplication.liangcang.zazhi.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;
import myapplication.liangcang.base.BaseFragment;

/**
 * Created by zhouzhou on 2017/7/6.
 */

public class ZaZhiFragment extends BaseFragment {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.title)
    RelativeLayout title;
    @Bind(R.id.base_search)
    ImageView basesearch;
    @Bind(R.id.base_shop)
    ImageView baseshop;
    @Bind(R.id.listView)
    ListView listView;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_zazhi, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initTitle() {
        baseTitle.setText("杂志");
    }



}

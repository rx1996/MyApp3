package myapplication.liangcang.daren.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import myapplication.liangcang.R;
import myapplication.liangcang.base.BaseFragment;
import myapplication.liangcang.daren.adapter.DarenAdapter;
import myapplication.liangcang.daren.bean.DarenInfo;
import myapplication.liangcang.utils.Constants;
import okhttp3.Call;

/**
 * Created by zhouzhou on 2017/7/6.
 */

public class DarenFragment extends BaseFragment {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_search)
    ImageView basesearch;
    @Bind(R.id.gv_daren)
    GridView gvDaren;
    @Bind(R.id.ib_daren_fenlei)
    ImageView ibDarenFenlei;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    private DarenAdapter adapter;
    private List<DarenInfo.DataBean.ItemsBean> datas;
    private List<DarenInfo.DataBean.ItemsBean.UserImagesBean> images;

    private static final int DAREN_FENLEI = 1;
    private static final int ACTION_COMPLETE = 2;
    private PopupWindow mPopupWindow;
    private LinearLayout llFenlei;
    private TextView tvMoRen;
    private TextView tvZuiDuo;
    private TextView tvHuanYing;
    private TextView tvTuiJian;
    private TextView tvJiaRu;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.feagment_daren, null);
        ButterKnife.bind(this, view);
        ibDarenFenlei.setTag(DAREN_FENLEI);
        return view;
    }

    @Override
    public void initTitle() {
        baseTitle.setText("达人");
    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet(Constants.BASE_URL_DAREN);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new MyStringCallback());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    public class MyStringCallback extends StringCallback {

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
        DarenInfo darenInfo = JSON.parseObject(json, DarenInfo.class);
        darenInfo.getData().getItems().get(6).getUser_images();
        datas = darenInfo.getData().getItems();

        adapter = new DarenAdapter(mContext, datas, images);
        gvDaren.setAdapter(adapter);
    }

    @OnClick({R.id.base_search, R.id.ib_daren_fenlei})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.base_search:
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.ib_daren_fenlei:
                int tag = (int) ibDarenFenlei.getTag();
                if (tag == DAREN_FENLEI) {
                    showPopupWindow(view);
                } else {
                    hideDelete();
                }
                break;
        }
    }

    private void hideDelete() {
        ibDarenFenlei.setImageResource(R.drawable.actionbar_navigation_menu);
        ibDarenFenlei.setTag(DAREN_FENLEI);
        llFenlei.setVisibility(View.GONE);
    }

    private void showPopupWindow(View view) {
        ibDarenFenlei.setTag(ACTION_COMPLETE);
        ibDarenFenlei.setImageResource(R.drawable.abc_ic_clear_mtrl_alpha);
        final View popupView = LayoutInflater.from(mContext).inflate(R.layout.pop_daren, null);
        llFenlei = (LinearLayout) popupView.findViewById(R.id.ll_fenlei);
        tvMoRen = (TextView) popupView.findViewById(R.id.tv_moren);
        tvZuiDuo = (TextView) popupView.findViewById(R.id.tv_zuiduo);
        tvHuanYing = (TextView) popupView.findViewById(R.id.tv_huanying);
        tvTuiJian = (TextView) popupView.findViewById(R.id.tv_zuixin_tuijian);
        tvJiaRu = (TextView) popupView.findViewById(R.id.tv_zuixin_jiaru);
        llFenlei.setVisibility(View.VISIBLE);
        tvZuiDuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=goods_sum&page=1&sig=05D2057FE3D726A43A94505807516FC3%7C136072130089168&v=1.0";
                OkHttpUtils.get().url(url).build().execute(new MyStringCallback());

                ibDarenFenlei.setImageResource(R.drawable.actionbar_navigation_menu);
                mPopupWindow.dismiss();
            }
        });
        tvHuanYing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=followers&page=9&sig=05D2057FE3D726A43A94505807516FC3|136072130089168&v=1.0";
                OkHttpUtils.get().url(url).build().execute(new MyStringCallback());
                ibDarenFenlei.setImageResource(R.drawable.actionbar_navigation_menu);
                mPopupWindow.dismiss();
            }
        });
        tvTuiJian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=reg_time&page=9&sig=05D2057FE3D726A43A94505807516FC3|136072130089168&v=1.0";
                OkHttpUtils.get().url(url).build().execute(new MyStringCallback());
                ibDarenFenlei.setImageResource(R.drawable.actionbar_navigation_menu);
                mPopupWindow.dismiss();
            }
        });
        tvJiaRu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://mobile.iliangcang.com/user/masterList?app_key=Android&count=18&orderby=action_time&page=9&sig=05D2057FE3D726A43A94505807516FC3|136072130089168&v=1.0";
                OkHttpUtils.get().url(url).build().execute(new MyStringCallback());
                ibDarenFenlei.setImageResource(R.drawable.actionbar_navigation_menu);
                mPopupWindow.dismiss();
            }
        });
       mPopupWindow = new PopupWindow(popupView, GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupWindow.showAsDropDown(view);
    }

}

package myapplication.liangcang.daren.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;
import myapplication.liangcang.base.BaseFragment;
import myapplication.liangcang.daren.adapter.TuijianAdapter;
import myapplication.liangcang.daren.bean.TuijianInfo;
import myapplication.liangcang.utils.Constants;
import okhttp3.Call;

/**
 * Created by zhouzhou on 2017/7/14.
 */

public class TuijianFragment extends BaseFragment {

    @Bind(R.id.tuijian_recyclerView)
    RecyclerView tuijianRecyclerView;
    private TuijianAdapter adapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_daren_tuijian, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initTitle() {

    }

    @Override
    public void initData() {
        super.initData();
        getDataFromNet(Constants.BASE_URL_DAREN_TUIJIAN);
    }

    private void getDataFromNet(String url) {
        OkHttpUtils
                .get()
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
            Log.e("TAG", "请求成功==");
            processData(response);

        }
    }

    private void processData(String json) {
        TuijianInfo tuijianInfo = JSON.parseObject(json, TuijianInfo.class);
        final List<TuijianInfo.DataBean.ItemsBean.GoodsBean> datas = tuijianInfo.getData().getItems().getGoods();
        adapter = new TuijianAdapter(mContext, datas);
        tuijianRecyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layout = new GridLayoutManager(mContext, 2);
        tuijianRecyclerView.setLayoutManager(layout);
    }

}

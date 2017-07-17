package myapplication.liangcang.daren.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import myapplication.liangcang.daren.bean.TuijianInfo;

/**
 * Created by zhouzhou on 2017/7/14.
 */

public class TuijianAdapter extends RecyclerView.Adapter {
    private final Context context;
    private final List<TuijianInfo.DataBean.ItemsBean.GoodsBean> datas;

    public TuijianAdapter(Context mContext, List<TuijianInfo.DataBean.ItemsBean.GoodsBean> datas) {
        this.context = mContext;
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

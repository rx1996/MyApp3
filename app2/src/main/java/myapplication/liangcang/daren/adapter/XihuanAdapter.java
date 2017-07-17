package myapplication.liangcang.daren.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;
import myapplication.liangcang.daren.bean.XihuanInfo;

/**
 * Created by zhouzhou on 2017/7/14.
 */

public class XihuanAdapter extends RecyclerView.Adapter<XihuanAdapter.MyViewHolder> {
    private final Context context;
    private final List<XihuanInfo.DataBean.ItemsBean.GoodsBean> datas;


    public XihuanAdapter(Context mContext, List<XihuanInfo.DataBean.ItemsBean.GoodsBean> datas) {
        this.context = mContext;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = View.inflate(context, R.layout.item_xihuan, null);
        MyViewHolder view = new MyViewHolder(item);
        return view;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String imageUrl = datas.get(position).getGoods_image();
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_login_logo)
                .error(R.drawable.ic_login_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivXihuanIcon);
    }


    @Override
    public int getItemCount() {
        return datas ==null?0:datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_xihuan_icon)
        ImageView ivXihuanIcon;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

package myapplication.liangcang.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;
import myapplication.liangcang.shop.bean.ShopGiftChildInfo;

/**
 * Created by zhouzhou on 2017/7/12.
 */

public class ShopGiftchildAdapter extends RecyclerView.Adapter<ShopGiftchildAdapter.MyViewHolder> {
    private final Context context;
    private final List<ShopGiftChildInfo.DataBean.ItemsBean> datas;

    public ShopGiftchildAdapter(Context context, List<ShopGiftChildInfo.DataBean.ItemsBean> datas) {
        this.context = context;
        this.datas = datas;
    }
    @Override
    public ShopGiftchildAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = View.inflate(context, R.layout.item_shop_gift_child, null);
        ShopGiftchildAdapter.MyViewHolder view = new ShopGiftchildAdapter.MyViewHolder(item);
        return view;
    }

    @Override
    public void onBindViewHolder(ShopGiftchildAdapter.MyViewHolder holder, int position) {
        String imageUrl = datas.get(position).getGoods_image();
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_login_logo)
                .error(R.drawable.ic_login_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivGiftIcon);
        holder.tvGiftGoodsName.setText(datas.get(position).getGoods_name());
        holder.tvGiftBrandsName.setText(datas.get(position).getBrand_info().getBrand_name());
        holder.giftPrice.setText(datas.get(position).getPrice());
        holder.giftLike.setText(datas.get(position).getLike_count());
//        holder.itemView.setTag(datas.get(position));
//        holder.itemView.setOnClickListener(this);
    }


    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_gift_icon)
        ImageView ivGiftIcon;
        @Bind(R.id.tv_gift_goodsName)
        TextView tvGiftGoodsName;
        @Bind(R.id.tv_gift_brandsName)
        TextView tvGiftBrandsName;
        @Bind(R.id.gift_moneyicon)
        TextView giftMoneyicon;
        @Bind(R.id.gift_price)
        TextView giftPrice;
        @Bind(R.id.iv_like)
        ImageView ivLike;
        @Bind(R.id.gift_like)
        TextView giftLike;
        @Bind(R.id.rl_price)
        RelativeLayout rlPrice;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

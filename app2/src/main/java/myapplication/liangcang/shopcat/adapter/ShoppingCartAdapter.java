package myapplication.liangcang.shopcat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;
import myapplication.liangcang.shop.bean.ShopInformationBean;
import myapplication.liangcang.shopcat.activity.ShopCatActivity;

/**
 * Created by Administrator on 2017/7/25.
 */

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartAdapter.MyViewHolder> {
    private final ShopCatActivity context;
    private final ArrayList<ShopInformationBean> datas;


    public ShoppingCartAdapter(ShopCatActivity context, ArrayList<ShopInformationBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item_shop_cat, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ShopInformationBean infoBean = datas.get(position);
        holder.checkboxAll.setChecked(infoBean.getData().getItems().isCheck());
        Glide.with(context).load(infoBean.getData().getItems().getGoods_image())
                .placeholder(R.drawable.ic_login_logo).
                error(R.drawable.ic_login_logo).
                diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.ivGov);
        holder.tvDescGov.setText(infoBean.getData().getItems().getGoods_name());
        holder.tvPriceGov.setText("￥" + infoBean.getData().getItems().getPrice());

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.checkbox_all)
        CheckBox checkboxAll;
        @Bind(R.id.iv_gov)
        ImageView ivGov;
        @Bind(R.id.tv_desc_gov)
        TextView tvDescGov;
        @Bind(R.id.tv_price_gov)
        TextView tvPriceGov;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}

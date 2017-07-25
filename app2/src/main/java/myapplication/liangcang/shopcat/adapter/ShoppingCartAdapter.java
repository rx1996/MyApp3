package myapplication.liangcang.shopcat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;
import myapplication.liangcang.shop.bean.ShopInformationBean;
import myapplication.liangcang.shopcat.activity.ShopCatActivity;
import myapplication.liangcang.shopcat.view.AddSubView;

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
        holder.nasGoodinfoNum.setValue(infoBean.getData().getItems().getNumber());
        holder.nasGoodinfoNum.setMinValue(1);
        //库存
        holder.nasGoodinfoNum.setMaxValue(200);

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
        @Bind(R.id.ll_1)
        LinearLayout ll1;
        @Bind(R.id.checkbox_all2)
        CheckBox checkboxAll2;
        @Bind(R.id.iv_gov2)
        ImageView ivGov2;
        @Bind(R.id.nas_goodinfo_num)
        AddSubView nasGoodinfoNum;
        @Bind(R.id.tv_desc_gov2)
        TextView tvDescGov2;
        @Bind(R.id.tv_price_gov2)
        TextView tvPriceGov2;
        @Bind(R.id.ll_2)
        LinearLayout ll2;
        @Bind(R.id.tv_shanchu)
        TextView tvShanchu;
        @Bind(R.id.rl_2)
        RelativeLayout rl2;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShopInformationBean infoBean = datas.get(getLayoutPosition());
                    infoBean.getData().getItems().setCheck(!infoBean.getData().getItems().isCheck());
                    notifyItemChanged(getLayoutPosition());
                }
            });
        }
    }
}

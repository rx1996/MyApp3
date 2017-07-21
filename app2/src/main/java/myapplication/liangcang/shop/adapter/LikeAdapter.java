package myapplication.liangcang.shop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;
import myapplication.liangcang.shop.bean.CainiXihuanBean;

/**
 * Created by Administrator on 2017/7/21.
 */

public class LikeAdapter extends BaseAdapter {
    private final Context context;
    private final List<CainiXihuanBean.DataBean.ItemsBean> items;

    public LikeAdapter(Context mContext, List<CainiXihuanBean.DataBean.ItemsBean> items) {
        this.context = mContext;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_like, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        CainiXihuanBean.DataBean.ItemsBean bean = items.get(position);
        Glide.with(context)
                .load(bean.getGoods_image())
                .placeholder(R.drawable.ic_login_logo)
                .error(R.drawable.ic_login_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivGiftIcon);

        viewHolder.tvGiftGoodsName.setText(items.get(position).getGoods_name());
        viewHolder.giftPrice.setText(items.get(position).getPrice());
        viewHolder.giftLike.setText(items.get(position).getLike_count());
        return convertView;
    }


    class ViewHolder {
        @Bind(R.id.iv_gift_icon)
        ImageView ivGiftIcon;
        @Bind(R.id.tv_gift_goodsName)
        TextView tvGiftGoodsName;
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

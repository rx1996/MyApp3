package myapplication.liangcang.zazhi.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;
import myapplication.liangcang.zazhi.bean.ZaZhiInfo;

/**
 * Created by Administrator on 2017/7/19.
 */

public class ZaZhiAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<ZaZhiInfo.DataBean.ItemsBean.InfosBean._$2017JUL18Bean> datas;

    public ZaZhiAdapter(Context mContext, List<ZaZhiInfo.DataBean.ItemsBean.InfosBean._$2017JUL18Bean> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }


    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_zazhi, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ZaZhiInfo.DataBean.ItemsBean.InfosBean._$2017JUL18Bean info = datas.get(position);
        viewHolder.tvZazhiName.setText(info.getTopic_name());
        viewHolder.tvZazhiFeilei.setText(info.getCat_name());
        Glide.with(mContext)
                .load(info.getCover_img_new())
                .placeholder(R.drawable.ic_login_logo)
                .error(R.drawable.ic_login_logo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivZazhiImage);
        viewHolder.tvDate.setText(info.getAddtime());
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_zazhi_image)
        ImageView ivZazhiImage;
        @Bind(R.id.tv_zazhi_name)
        TextView tvZazhiName;
        @Bind(R.id.tv_zazhi_feilei)
        TextView tvZazhiFeilei;
        @Bind(R.id.tv_date)
        TextView tvDate;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

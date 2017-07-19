package myapplication.liangcang.zazhi.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;
import myapplication.liangcang.base.BaseFragment;
import myapplication.liangcang.utils.Constants;
import myapplication.liangcang.zazhi.adapter.ZaZhiAdapter;
import myapplication.liangcang.zazhi.bean.ZaZhiInfo;
import okhttp3.Call;

/**
 * Created by zhouzhou on 2017/7/6.
 */

public class ZaZhiFragment extends BaseFragment {


    @Bind(R.id.base_title)
    TextView baseTitle;
    @Bind(R.id.base_arrow_down)
    ImageView baseArrowDown;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.listView)
    ListView listView;
    private List<String> keys;
    private HashMap<Object, Object> map;
    private ZaZhiAdapter adapter;
    ZaZhiInfo magazineBean;
    List<ZaZhiInfo.DataBean.ItemsBean.InfosBean._$2017JUL18Bean> list;
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

    @Override
    public void initData() {
        super.initData();
        getDataFromNet(Constants.BASE_URL_ZAZHI);
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

    private class MyStringCallback extends StringCallback {

        @Override
        public void onError(Call call, Exception e, int id) {
            Log.e("HOMETAG", "请求失败==" + e.getMessage());
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e("HOMETAG", "请求成功==");
            processData(response);

        }
    }

    private void processData(String json) {
         magazineBean = JSON.parseObject(json, ZaZhiInfo.class);
        try {

            keys = magazineBean.getData().getItems().getKeys();

            list = new ArrayList<>();

            map = new HashMap<>();

            if (keys != null && keys.size() > 0) {

                for (int i = 0; i < keys.size(); i++) {

                    JSONObject jsonObject = new JSONObject(json);
                    JSONObject jsonObject2 = jsonObject.optJSONObject("data");
                    JSONObject jsonObject3 = jsonObject2.optJSONObject("items");
                    JSONObject jsonobject4 = jsonObject3.optJSONObject("infos");

                    map.put(keys.get(i), list);


                    JSONArray jsonArray = jsonobject4.optJSONArray(keys.get(i));

                    if (jsonArray != null && jsonArray.length() > 0) {

                        for (int j = 0; j < jsonArray.length(); j++) {


                            JSONObject object = (JSONObject) jsonArray.get(j);

                            if (object != null) {
                                ZaZhiInfo.DataBean.ItemsBean.InfosBean._$2017JUL18Bean info = new ZaZhiInfo.DataBean.ItemsBean.InfosBean._$2017JUL18Bean();
                                info.setTaid(object.optString("taid"));
                                info.setTopic_name(object.optString("topic_name"));
                                info.setCat_id(object.optString("cat_id"));
                                info.setAuthor_id(object.optString("author_id"));
                                info.setTopic_url(object.optString("topic_url"));
                                info.setAccess_url(object.optString("access_url"));
                                info.setCover_img(object.optString("cover_img"));
                                info.setCover_img_new(object.optString("cover_img_new"));
                                info.setHit_number(object.optInt("hit_number"));
                                info.setAddtime(object.optString("addtime"));
                                info.setContent(object.optString("content"));
                                info.setNav_title(object.optString("nav_title"));
                                info.setAuthor_name(object.optString("author_name"));
                                info.setCat_name(object.optString("cat_name"));

                                list.add(info);
                            }
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new ZaZhiAdapter(mContext, list);
        listView.setAdapter(adapter);
    }

}

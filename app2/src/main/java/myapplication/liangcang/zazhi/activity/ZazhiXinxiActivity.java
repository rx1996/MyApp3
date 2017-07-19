package myapplication.liangcang.zazhi.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import myapplication.liangcang.R;

public class ZazhiXinxiActivity extends AppCompatActivity {

    @Bind(R.id.tv_title_biaoti)
    TextView tvTitleBiaoti;
    @Bind(R.id.iv_special)
    ImageView ivSpecial;
    @Bind(R.id.base_shop)
    ImageView baseShop;
    @Bind(R.id.special_webView)
    WebView specialWebView;
    @Bind(R.id.activity_zazhi_xinxi)
    LinearLayout activityZazhiXinxi;

    private Uri url;
    private WebSettings settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zazhi_xinxi);
        ButterKnife.bind(this);

        ivSpecial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvTitleBiaoti.setText(getIntent().getStringExtra("biaoti"));
        url = getIntent().getData();
        settings = specialWebView.getSettings();
        //设置支持javaScript
        settings.setJavaScriptEnabled(true);
        //设置双击页面变大变小
        settings.setUseWideViewPort(true);

        //添加变大变小按钮
        settings.setBuiltInZoomControls(true);

        //设置加载网页完成的监听
        specialWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

            }
        });
        //加载网页地址
        specialWebView.loadUrl(url.toString());
    }
}

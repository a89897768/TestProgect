package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.appbar.AppBarLayout;

public class MainActivity extends AppCompatActivity {
    private CustomCoordinatorLayout mCoordinatorLayout;
    private CustomWebView mWebView;
    private CustomAppBarLayout mAppBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mCoordinatorLayout = findViewById(R.id.coordinatorLayout);
//
//        mAppBarLayout = findViewById(R.id.id_news_content_appbar);
//        mAppBarLayout.addOnOffsetChangedListener(createOffsetChangedListener());
        mWebView = findViewById(R.id.webView);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        mWebView.loadUrl("https://www.google.com/search?q=%E7%BF%BB%E8%AD%AF&rlz=1C1CHBF_zh-TWTW906TW906&oq=%E7%BF%BB%E8%AD%AF&aqs=chrome.0.69i59j0i433l2j0i131i433l2j0i433j0j69i65.4226j0j7&sourceid=chrome&ie=UTF-8");
        mWebView.loadUrl("https://testweb9.xq.com.tw/focus/Article/車用晶片慌  如何看這場斷炊危機-637481464870537152");

//        mCoordinatorLayout.setWebView(mWebView);
//        mCoordinatorLayout.setAppBarLayout(mAppBarLayout);
//        mAppBarLayout.setWebView(mWebView);
    }

    private AppBarLayout.OnOffsetChangedListener createOffsetChangedListener() {
        return new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                mCoordinatorLayout.setExpanded(verticalOffset == 0);
                mCoordinatorLayout.setCollapsed(Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange());
//                mWebView.setExpanded(verticalOffset == 0);
//                mWebView.setCollapsed(Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange());
            }
        };
    }
}

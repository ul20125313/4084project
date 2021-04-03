package com.example.dazuoye.userpage.shouye;

import androidx.appcompat.app.AppCompatActivity;
import com.example.dazuoye.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Web1 extends AppCompatActivity {
    private WebView webv;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web1);
        getSupportActionBar().hide();
        init();

    }
    void init(){
        webv = (WebView) findViewById(R.id.webv);
        Intent a=getIntent();
        url=a.getStringExtra("url");
        webv.loadUrl(url);
        webv.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webv.getSettings();

//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            if(webv.canGoBack()){
                webv.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

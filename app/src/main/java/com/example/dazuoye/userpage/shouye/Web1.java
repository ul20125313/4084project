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


        webSettings.setJavaScriptEnabled(true);


        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);


        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDefaultTextEncodingName("utf-8");
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

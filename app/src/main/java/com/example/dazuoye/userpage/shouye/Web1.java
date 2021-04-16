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

//If you want to interact with JavaScript in the visited page, the WebView must be set to support JavaScript
        webSettings.setJavaScriptEnabled(true);

//Set up adaptive screen, both of which are shared
        webSettings.setUseWideViewPort(true); //Adjust the image to fit the WebView
        webSettings.setLoadWithOverviewMode(true); //Zoom to the size of the screen

//Zoom operation
        webSettings.setSupportZoom(true); //Support zoom, the default is true. It's the premise of the following.
        webSettings.setBuiltInZoomControls(true); //Set the built-in zoom control. If false, the WebView is not scalable
        webSettings.setDisplayZoomControls(false); //Hide native zoom controls

        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //Support to open a new window through JS
        webSettings.setLoadsImagesAutomatically(true); //Support automatic loading of pictures
        webSettings.setDefaultTextEncodingName("utf-8");//Format encoding
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


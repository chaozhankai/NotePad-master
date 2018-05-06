package com.example.administrator.intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
/**
 * Created by Administrator on 2018/5/6.
 */

public class myWebViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        Intent intent = getIntent();
        Uri data = intent.getData();
        WebView webView = (WebView)findViewById(R.id.myWebView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(data.toString());

        //URL url = new URL(data.getScheme());


    }
}

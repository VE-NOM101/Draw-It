package com.example.drawit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebImage extends AppCompatActivity {
    WebView webView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_image);

        Intent intent=getIntent();
        String url=intent.getStringExtra("url");
        webView =findViewById(R.id.webImage);
        progressBar= findViewById(R.id.web_loading);

        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

    }

    @Override
    public void onBackPressed() {

        if(webView.canGoBack()){
            webView.goBack();
        }
        else super.onBackPressed();
    }
}
package com.example.drawit.WebView;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.drawit.Bookmark.Sqlite_StudentDatabaseSource;
import com.example.drawit.Bookmark.Sqlite_StudentModel;
import com.example.drawit.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class All_Web_Pages_NoAds extends AppCompatActivity {


    ProgressBar progressBar;
    TextView textView;
    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton floatingActionButton_bookmark;
    Sqlite_StudentDatabaseSource studentDatabaseSource;
    String webPage, link, current_link, current_title;

    WebView superWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_page_drawer_for_webpage);   // this is the set content view

        Log.d(TAG, "Web onCreat: is called new now");
        studentDatabaseSource = new Sqlite_StudentDatabaseSource(this);




        if (getIntent().getStringExtra("Web") != null) {
            webPage = getIntent().getStringExtra("Web");    //here url will be loaded
            Log.d(TAG, "onCreate: callingggg getIntent");
        }else {
            webPage = "https://www.google.com";
        }

        progressBar = findViewById(R.id.progressBar_ID);
        swipeRefreshLayout = findViewById(R.id.refreshing_ID);
        superWebView = findViewById(R.id.webView);
        textView = findViewById(R.id.App_bar_text_progress);

        try {
            superWebView.loadUrl(webPage); // Sometimes it generate null pointer exception
        } catch (Exception e) {
            e.printStackTrace();
        }


        bookmark_saved_caller();

        superWebView.setWebViewClient(new WebViewClient());


        WebSettings webSettings = superWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setSupportZoom(false);
        webSettings.setCacheMode(webSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);

        superWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        superWebView.setScrollbarFadingEnabled(true);
        //to enhance webpage
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setGeolocationEnabled(false);
        webSettings.setNeedInitialFocus(false);
        webSettings.setSaveFormData(false);


        superWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                current_title = view.getTitle();
                current_link = view.getUrl();

                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(newProgress);
                String string = String.valueOf(newProgress);

                Log.d(TAG, "onProgressChanged: " + newProgress);
                if (newProgress < 100) {
                    textView.setText("  Loading " + string + "%  ");

                }
                if (newProgress == 100) {
                    textView.setText("");
                }


                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                }

                super.onProgressChanged(view, newProgress);
            }


        });


        //This part of code is for Refreshing
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                    try {

                        if (superWebView.getOriginalUrl().isEmpty()) {
                            Toast.makeText(All_Web_Pages_NoAds.this, "Invalid....", Toast.LENGTH_LONG).show();
                            swipeRefreshLayout.setRefreshing(false);
                        } else if (!superWebView.getOriginalUrl().isEmpty()) {
                            superWebView.reload();
                            Toast.makeText(All_Web_Pages_NoAds.this, "Refreshing....", Toast.LENGTH_LONG).show();
                            swipeRefreshLayout.setRefreshing(false);
                        }

                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }catch (Exception e){

                }

            }

        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (this.superWebView.canGoBack()) {
            this.superWebView.goBack();
        } else {
            finish();

        }
    }


    //this is for sharing navigation button
   public void bookmark_saved_caller() {
        floatingActionButton_bookmark = findViewById(R.id.bottom_nav_bookmark_button);
        floatingActionButton_bookmark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Sqlite_StudentModel studentModel = new Sqlite_StudentModel(current_title, 20, current_link);
                studentDatabaseSource.addStudent(studentModel);

                Toast.makeText(All_Web_Pages_NoAds.this, "Bookmark done", Toast.LENGTH_SHORT).show();

            }
        });

    }

}

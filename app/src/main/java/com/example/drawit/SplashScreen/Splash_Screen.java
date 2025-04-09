package com.example.drawit.SplashScreen;

import static android.content.ContentValues.TAG;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.drawit.Account.Key_SharedPreference;
import com.example.drawit.R;

public class Splash_Screen extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences(Key_SharedPreference.PREFS_KEY, MODE_PRIVATE);

        if (sp.getBoolean(Key_SharedPreference.FIRST_TIME_APP_OPEN_SPLASH_SCREEN, true)) {
            setContentView(R.layout.splash_screen);   //This is the FIRST time
            Log.d(TAG, "onCreate: Called now ins 1 : " + sp.getBoolean(Key_SharedPreference.FIRST_TIME_APP_OPEN_SPLASH_SCREEN, true));
        } else {
            setContentView(R.layout.splash_screen);   //This is the every time               /// this is the setting the content view
            Log.d(TAG, "onCreate: Called now ins 2 : " + sp.getBoolean(Key_SharedPreference.FIRST_TIME_APP_OPEN_SPLASH_SCREEN, true));
        }


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progress_bar);
        textView = findViewById(R.id.text_view);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        one_time_show_AppOverview();

    }


    //this block is for one time APP_Toor_AppOverView
    private void one_time_show_AppOverview() {
        SharedPreferences sharedPreferences = getSharedPreferences(Key_SharedPreference.PREFS_KEY, MODE_PRIVATE);

        boolean bln = sharedPreferences.getBoolean(Key_SharedPreference.FIRST_TIME_APP_OPEN_SPLASH_SCREEN, true);   //ata store korba *on creat* koi bar run koracha

        if (bln == true) {
            progressAnimation_1st_time();
            //this portion will update the boolean value status , true to false
            Log.d(TAG, "onCreate: Called now ins 1st : " + sharedPreferences.getBoolean(Key_SharedPreference.FIRST_TIME_APP_OPEN_SPLASH_SCREEN, true));
        } else if (bln == false) {
            progressAnimation_other_time();
            Log.d(TAG, "onCreate: Called now ins other : " + sharedPreferences.getBoolean(Key_SharedPreference.FIRST_TIME_APP_OPEN_SPLASH_SCREEN, true));
        }
    }

    public void progressAnimation_1st_time() {

        Splash_Screen_ProgressAnimation_FirstTime anim = new Splash_Screen_ProgressAnimation_FirstTime(this, progressBar, textView, 0f, 100f);
        anim.setDuration(6000);
        progressBar.setAnimation(anim);

    }

    public void progressAnimation_other_time() {

        Splash_Screen_ProgressAnimation_OthersTime anim = new Splash_Screen_ProgressAnimation_OthersTime(this, progressBar, textView, 0f, 100f);
        anim.setDuration(6000);
        progressBar.setAnimation(anim);

    }

}
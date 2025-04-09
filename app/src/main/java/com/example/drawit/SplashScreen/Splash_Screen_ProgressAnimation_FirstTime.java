package com.example.drawit.SplashScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.drawit.Account.Login;
import com.example.drawit.R;

public class Splash_Screen_ProgressAnimation_FirstTime extends Animation {

    private Context context ;
    private ProgressBar progressBar ;
    private TextView textView ;
    private  float from ;
    private float to ;

    public Splash_Screen_ProgressAnimation_FirstTime(Context context, ProgressBar progressBar, TextView textView, float from, float to) {
        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
        this.from = from;
        this.to = to;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        float value = from + (to - from) * interpolatedTime ;
        progressBar.setProgress((int) value);
        progressBar.getProgressDrawable().setColorFilter(ContextCompat.getColor(context, R.color.progressbar_color_first), android.graphics.PorterDuff.Mode.SRC_IN);
        textView.setText((int)value+" %");

        if (value == to){
            context.startActivity(new Intent(context, Login.class));
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        }
    }
}



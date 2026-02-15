package com.example.phishguardapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

public class activity_splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        android.widget.TextView title = findViewById(R.id.tvSplashTitle);

        android.animation.ObjectAnimator scaleX =
                android.animation.ObjectAnimator.ofFloat(title, "scaleX", 1f, 1.05f, 1f);

        android.animation.ObjectAnimator scaleY =
                android.animation.ObjectAnimator.ofFloat(title, "scaleY", 1f, 1.05f, 1f);

        scaleX.setDuration(1500);
        scaleY.setDuration(1500);

        scaleX.setRepeatCount(android.animation.ValueAnimator.INFINITE);
        scaleY.setRepeatCount(android.animation.ValueAnimator.INFINITE);

        scaleX.start();
        scaleY.start();


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activity_splash.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        }, 3000);
    }
}

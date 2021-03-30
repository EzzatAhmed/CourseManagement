package com.example.new_desgin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        TextView tv = findViewById(R.id.textView2);
        ImageView im = findViewById(R.id.imageView);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splash);
        tv.startAnimation(myanim);
        im.startAnimation(myanim);

        final Intent i = new Intent(this, login.class);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}

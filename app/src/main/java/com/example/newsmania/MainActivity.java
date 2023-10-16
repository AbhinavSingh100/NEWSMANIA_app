package com.example.newsmania;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private Animation mainAnim;
    private ImageView logo;
    private ProgressBar progressBar;
    private int progressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.logo);
        mainAnim = AnimationUtils.loadAnimation(this, R.anim.main_screen);
        logo.setAnimation(mainAnim);

        progressBar = findViewById(R.id.progressBar);
        final Handler handler = new Handler();

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 10; // Increase progress by 10 (adjust as needed)
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Add a delay to simulate loading
                        Thread.sleep(500); // Adjust the delay time as needed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // After loading is complete, start the main activity
                startActivity(new Intent(MainActivity.this, HomeScreen.class));
                finish();
            }
        }).start();
    }
}
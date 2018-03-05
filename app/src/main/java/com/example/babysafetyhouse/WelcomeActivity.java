package com.example.babysafetyhouse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babysafetyhouse.statusbar.BaseActivity;
import com.example.babysafetyhouse.statusbar.SystemBarTintManager;

/**
 * Created by 94284 on 2017/5/1.
 */

public class WelcomeActivity extends BaseActivity {

    private Handler handler = new Handler();
    private Handler time_handle = new Handler();
    private int time = 3;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            text_time.setText(time + "s");

            if (time <= 1) {
                time_handle.removeCallbacks(runnable);
            } else {
                time--;
            }
            time_handle.postDelayed(this, 1000);

        }
    };
    private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            WelcomeActivity.this.startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            finish();
        }
    };

    private SystemBarTintManager mTintManager;
    private TextView text_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mTintManager = new SystemBarTintManager(WelcomeActivity.this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.welcomestatuscolor);
        text_time = (TextView) this.findViewById(R.id.text_time);

        handler.postDelayed(runnable2, 3000);
        time_handle.postDelayed(runnable, 0);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        WelcomeActivity.this.startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacks(runnable2);
        time_handle.removeCallbacks(runnable);
    }

    public void go(View v) {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        WelcomeActivity.this.startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        finish();
    }
}

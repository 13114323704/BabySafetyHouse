package com.example.babysafetyhouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.babysafetyhouse.statusbar.BaseActivity;
import com.example.babysafetyhouse.statusbar.SystemBarTintManager;

/**
 * Created by 94284 on 2017/5/8.
 */

public class WalletActivity extends BaseActivity {
    private SystemBarTintManager mTintManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        initStatusbar();
    }

    private void initStatusbar() {
        mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.statusColor);
    }

    public void recharge(View v) {
        Intent intent = new Intent(WalletActivity.this, RechargeActivity.class);
        WalletActivity.this.startActivity(intent);
    }

    public void image_back(View v){
        finish();
    }
}

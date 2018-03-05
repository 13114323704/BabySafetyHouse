package com.example.babysafetyhouse;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.babysafetyhouse.statusbar.BaseActivity;
import com.example.babysafetyhouse.statusbar.SystemBarTintManager;

/**
 * Created by 94284 on 2017/5/6.
 */

public class QRCodeActivity extends BaseActivity{

    private ImageView image_back;
    private SystemBarTintManager mTintManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        initStatusbar();

        image_back=(ImageView)this.findViewById(R.id.image_back);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initStatusbar(){
        mTintManager=new SystemBarTintManager(QRCodeActivity.this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.statusColor);
    }
}

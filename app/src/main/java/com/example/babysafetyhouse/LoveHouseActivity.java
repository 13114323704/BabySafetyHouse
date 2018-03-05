package com.example.babysafetyhouse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.example.babysafetyhouse.statusbar.BaseActivity;
import com.example.babysafetyhouse.statusbar.SystemBarTintManager;

/**
 * Created by 94284 on 2017/5/9.
 */

public class LoveHouseActivity extends BaseActivity implements View.OnClickListener{

    private SystemBarTintManager mTintManager;
    private View view1,view2,view3;

    private PullRefreshLayout pullRefreshLayout_love_hosue;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_house);
        initStatusbar();
        initView();
        initListener();
    }

    private void initStatusbar() {
        mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.statusColor);
    }

    private void initView(){
        pullRefreshLayout_love_hosue=(PullRefreshLayout)this.findViewById(R.id.pullRefreshLayout_love_house);
        view1=(View)this.findViewById(R.id.layout_lovehouse1);
        view2=(View)this.findViewById(R.id.layout_lovehouse2);
        view3=(View)this.findViewById(R.id.layout_lovehouse3);

        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
    }

    private void initListener(){
        pullRefreshLayout_love_hosue.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout_love_hosue.setRefreshing(false);
                        Toast.makeText(LoveHouseActivity.this,"刷新完成啦",Toast.LENGTH_SHORT).show();
                    }
                },2000);
            }
        });
    }

    public void image_back(View v){
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_lovehouse1:
                Intent intent1=new Intent(LoveHouseActivity.this,DetailInfoActivity.class);
                intent1.putExtra("position",2);
                LoveHouseActivity.this.startActivity(intent1);
                break;
            case R.id.layout_lovehouse2:
                Intent intent2=new Intent(LoveHouseActivity.this,DetailInfoActivity.class);
                intent2.putExtra("position",3);
                LoveHouseActivity.this.startActivity(intent2);
                break;
            case R.id.layout_lovehouse3:
                Intent intent3=new Intent(LoveHouseActivity.this,DetailInfoActivity.class);
                intent3.putExtra("position",4);
                LoveHouseActivity.this.startActivity(intent3);
                break;
        }
    }
}

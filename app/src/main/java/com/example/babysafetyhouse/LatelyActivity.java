package com.example.babysafetyhouse;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.example.babysafetyhouse.statusbar.BaseActivity;
import com.example.babysafetyhouse.statusbar.SystemBarTintManager;


/**
 * Created by 94284 on 2017/5/7.
 */

public class LatelyActivity extends BaseActivity implements View.OnClickListener {
    private SystemBarTintManager mTintManager;

    private ImageView image_back;
    private PullRefreshLayout pullRefreshLayout;
    private Handler handler = new Handler();

    private View view1,view2,view3,view4,view5,view6,view7,view8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lately);
        initStatusbar();
        initData();
        initView();
        initListener();

    }

    private void initStatusbar() {
        mTintManager = new SystemBarTintManager(LatelyActivity.this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.statusColor);
    }

    private void initData() {

    }

    private void initView() {
        pullRefreshLayout = (PullRefreshLayout) this.findViewById(R.id.pullRefreshLayout_lately);
        image_back=(ImageView)this.findViewById(R.id.image_back);
        view1=(View)this.findViewById(R.id.lately_babysafetyhouse1);
        view2=(View)this.findViewById(R.id.lately_babysafetyhouse2);
        view3=(View)this.findViewById(R.id.lately_babysafetyhouse3);
        view4=(View)this.findViewById(R.id.lately_babysafetyhouse4);
        view5=(View)this.findViewById(R.id.lately_babysafetyhouse5);
        view6=(View)this.findViewById(R.id.lately_babysafetyhouse6);
        view7=(View)this.findViewById(R.id.lately_babysafetyhouse7);
        view8=(View)this.findViewById(R.id.lately_babysafetyhouse8);
    }

    private void initListener() {
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout.setRefreshing(false);
                        Toast.makeText(LatelyActivity.this, "刷新完成啦", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });

        image_back.setOnClickListener(this);
        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
        view4.setOnClickListener(this);
        view5.setOnClickListener(this);
        view6.setOnClickListener(this);
        view7.setOnClickListener(this);
        view8.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
            case R.id.lately_babysafetyhouse1:
                Intent intent1=new Intent(LatelyActivity.this,DetailInfoActivity.class);
                intent1.putExtra("position",2);
                LatelyActivity.this.startActivity(intent1);
                break;
            case R.id.lately_babysafetyhouse2:
                Intent intent2=new Intent(LatelyActivity.this,DetailInfoActivity.class);
                intent2.putExtra("position",3);
                LatelyActivity.this.startActivity(intent2);
                break;
            case R.id.lately_babysafetyhouse3:
                Intent intent3=new Intent(LatelyActivity.this,DetailInfoActivity.class);
                intent3.putExtra("position",1);
                LatelyActivity.this.startActivity(intent3);
                break;
            case R.id.lately_babysafetyhouse4:
                Intent intent4=new Intent(LatelyActivity.this,DetailInfoActivity.class);
                intent4.putExtra("position",4);
                LatelyActivity.this.startActivity(intent4);
                break;
            case R.id.lately_babysafetyhouse5:
                Intent intent5=new Intent(LatelyActivity.this,DetailInfoActivity.class);
                intent5.putExtra("position",4);
                LatelyActivity.this.startActivity(intent5);
                break;
            case R.id.lately_babysafetyhouse6:
                Intent intent6=new Intent(LatelyActivity.this,DetailInfoActivity.class);
                intent6.putExtra("position",4);
                LatelyActivity.this.startActivity(intent6);
                break;
            case R.id.lately_babysafetyhouse7:
                Intent intent7=new Intent(LatelyActivity.this,DetailInfoActivity.class);
                intent7.putExtra("position",4);
                LatelyActivity.this.startActivity(intent7);
                break;
            case R.id.lately_babysafetyhouse8:
                Intent intent8=new Intent(LatelyActivity.this,DetailInfoActivity.class);
                intent8.putExtra("position",4);
                LatelyActivity.this.startActivity(intent8);
                break;
            default:
                break;
        }
    }
}

package com.example.babysafetyhouse;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.example.babysafetyhouse.statusbar.BaseActivity;
import com.example.babysafetyhouse.statusbar.SystemBarTintManager;

import java.util.ArrayList;

/**
 * Created by 94284 on 2017/5/9.
 */

public class TrackActivity extends BaseActivity implements View.OnClickListener{
    private SystemBarTintManager mTintManager;

    private PullRefreshLayout pullRefreshLayout_track_gone, pullRefreshLayout_order_bought, pullRefreshLayout_order_read;
    private Handler pullRefreshLayout_handler = new Handler();

    private ImageView image_back;

    private View view1, view2, view3;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<View> viewContainter = new ArrayList<View>();
    private ArrayList<String> titleContainer = new ArrayList<String>();
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        initStatusbar();
        initData();
        initView();
        initListener();
    }

    private void initStatusbar() {
        mTintManager = new SystemBarTintManager(TrackActivity.this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.statusColor);
    }

    private void initData() {
        view1 = LayoutInflater.from(TrackActivity.this).inflate(R.layout.fragment_track_gone, null);
        view2 = LayoutInflater.from(TrackActivity.this).inflate(R.layout.fragment_track_bought, null);
        view3 = LayoutInflater.from(TrackActivity.this).inflate(R.layout.fragment_track_read, null);

        viewContainter.clear();
        titleContainer.clear();

        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);

        titleContainer.add("去过的");
        titleContainer.add("买过的");
        titleContainer.add("看过的");
    }

    private void initView() {
        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) this.findViewById(R.id.tablayout);
        adapter = new PagerAdapter() {
            // viewpager中的组件数量
            @Override
            public int getCount() {
                return viewContainter.size();
            }

            // 滑动切换的时候销毁当前的组件
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                container.removeView(viewContainter.get(position));
            }

            // 每次滑动的时候生成的组件
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                container.addView(viewContainter.get(position));
                return viewContainter.get(position);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {

                return super.getItemPosition(object);
            }

            @Override
            public CharSequence getPageTitle(int position) {

                return titleContainer.get(position);
            }
        };
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        int position=getIntent().getIntExtra("position",1);
        viewPager.setCurrentItem(position-1);


        image_back=(ImageView)this.findViewById(R.id.image_back);
        image_back.setOnClickListener(this);

        pullRefreshLayout_track_gone = (PullRefreshLayout) view1.findViewById(R.id.pullRefreshLayout_track_gone);
        pullRefreshLayout_order_bought = (PullRefreshLayout) view2.findViewById(R.id.pullRefreshLayout_track_bought);
        pullRefreshLayout_order_read = (PullRefreshLayout) view3.findViewById(R.id.pullRefreshLayout_track_read);
    }

    private void initListener() {
        pullRefreshLayout_track_gone.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout_handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout_track_gone.setRefreshing(false);
                        Toast.makeText(TrackActivity.this, "刷新完成啦", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

            }
        });

        pullRefreshLayout_order_bought.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout_handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout_order_bought.setRefreshing(false);
                        Toast.makeText(TrackActivity.this, "刷新完成啦", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

            }
        });

        pullRefreshLayout_order_read.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout_handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout_order_read.setRefreshing(false);
                        Toast.makeText(TrackActivity.this, "刷新完成啦", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image_back:
                finish();
                break;
        }
    }
}

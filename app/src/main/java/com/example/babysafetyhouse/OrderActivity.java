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
 * Created by 94284 on 2017/5/6.
 */

public class OrderActivity extends BaseActivity implements View.OnClickListener {

    private SystemBarTintManager mTintManager;

    private PullRefreshLayout pullRefreshLayout_order_all, pullRefreshLayout_order_pay, pullRefreshLayout_order_comment;
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
        setContentView(R.layout.activity_order);
        initStatusbar();
        initData();
        initView();
        initListener();
    }

    private void initStatusbar() {
        mTintManager = new SystemBarTintManager(OrderActivity.this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.statusColor);
    }

    private void initData() {
        view1 = LayoutInflater.from(OrderActivity.this).inflate(R.layout.fragment_order_all, null);
        view2 = LayoutInflater.from(OrderActivity.this).inflate(R.layout.fragment_order_pay, null);
        view3 = LayoutInflater.from(OrderActivity.this).inflate(R.layout.fragment_order_comment, null);

        viewContainter.clear();
        titleContainer.clear();

        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);

        titleContainer.add("已完成");
        titleContainer.add("待付款");
        titleContainer.add("待评价");
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

        pullRefreshLayout_order_all = (PullRefreshLayout) view1.findViewById(R.id.pullRefreshLayout_order_all);
        pullRefreshLayout_order_pay = (PullRefreshLayout) view2.findViewById(R.id.pullRefreshLayout_order_pay);
        pullRefreshLayout_order_comment = (PullRefreshLayout) view3.findViewById(R.id.pullRefreshLayout_order_comment);
    }

    private void initListener() {
        pullRefreshLayout_order_all.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout_handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout_order_all.setRefreshing(false);
                        Toast.makeText(OrderActivity.this, "刷新完成啦", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

            }
        });

        pullRefreshLayout_order_pay.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout_handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout_order_pay.setRefreshing(false);
                        Toast.makeText(OrderActivity.this, "刷新完成啦", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

            }
        });

        pullRefreshLayout_order_comment.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout_handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout_order_comment.setRefreshing(false);
                        Toast.makeText(OrderActivity.this, "刷新完成啦", Toast.LENGTH_SHORT).show();
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

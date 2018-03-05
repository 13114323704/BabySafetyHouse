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

public class CollectActivity extends BaseActivity implements View.OnClickListener{
    private SystemBarTintManager mTintManager;

    private PullRefreshLayout pullRefreshLayout_collect_house, pullRefreshLayout_collect_commodity;
    private Handler pullRefreshLayout_handler = new Handler();

    private ImageView image_back;

    private View view1, view2;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<View> viewContainter = new ArrayList<View>();
    private ArrayList<String> titleContainer = new ArrayList<String>();
    private PagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initStatusbar();
        initData();
        initView();
        initListener();
    }

    private void initStatusbar() {
        mTintManager = new SystemBarTintManager(CollectActivity.this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.statusColor);
    }

    private void initData() {
        view1 = LayoutInflater.from(CollectActivity.this).inflate(R.layout.fragment_collect_house, null);
        view2 = LayoutInflater.from(CollectActivity.this).inflate(R.layout.fragment_collect_commodity, null);


        viewContainter.clear();
        titleContainer.clear();

        viewContainter.add(view1);
        viewContainter.add(view2);

        titleContainer.add("安全屋");
        titleContainer.add("商品");

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

        pullRefreshLayout_collect_house = (PullRefreshLayout) view1.findViewById(R.id.pullRefreshLayout_collect_house);
        pullRefreshLayout_collect_commodity = (PullRefreshLayout) view2.findViewById(R.id.pullRefreshLayout_collect_commodity);

    }

    private void initListener() {
        pullRefreshLayout_collect_house.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout_handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout_collect_house.setRefreshing(false);
                        Toast.makeText(CollectActivity.this, "刷新完成啦", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);

            }
        });

        pullRefreshLayout_collect_commodity.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullRefreshLayout_handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout_collect_commodity.setRefreshing(false);
                        Toast.makeText(CollectActivity.this, "刷新完成啦", Toast.LENGTH_SHORT).show();
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

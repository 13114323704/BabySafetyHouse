package com.example.babysafetyhouse.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.example.babysafetyhouse.CommodityInfoActivity;
import com.example.babysafetyhouse.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by 94284 on 2017/5/1.
 */

public class BuyFragment extends Fragment implements View.OnClickListener{

    private View main_view,view1,view2,view3,view4;
    private PullRefreshLayout pullRefreshLayout;
    //用于处理下拉刷新事件
    private Handler handler = new Handler();

    //图片轮播相关控件
    private ViewPager mViewPaper;
    private List<ImageView> images;
    // 存放图片的id
    private int[] imageIds = new int[]{R.mipmap.a2, R.mipmap.b2,
            R.mipmap.c2, R.mipmap.d2, R.mipmap.e2};
    private List<View> dots;
    private int currentItem;
    // 记录上一次点的位置
    private int oldPosition = 0;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;
    //接收子线程传递过来的数据,用于处理图片轮播事件
    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        main_view = inflater.inflate(R.layout.fragment_buy, container, false);

        initData();
        initView();
        initListener();
        return main_view;

    }

    private void initData() {
        // 显示的图片
        images = new ArrayList<ImageView>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }

        // 显示的小点
        dots = new ArrayList<View>();
        dots.add(main_view.findViewById(R.id.dot_0));
        dots.add(main_view.findViewById(R.id.dot_1));
        dots.add(main_view.findViewById(R.id.dot_2));
        dots.add(main_view.findViewById(R.id.dot_3));
        dots.add(main_view.findViewById(R.id.dot_4));
    }

    private void initView() {
        pullRefreshLayout = (PullRefreshLayout) main_view.findViewById(R.id.pullRefreshLayout_buy);
        mViewPaper = (ViewPager) main_view.findViewById(R.id.viewpager);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        view1=(View)main_view.findViewById(R.id.commodity_layout_first);
        view2=(View)main_view.findViewById(R.id.commodity_layout_second);
        view3=(View)main_view.findViewById(R.id.commodity_layout_third);
        view4=(View)main_view.findViewById(R.id.commodity_layout_fourth);
    }

    private void initListener() {
        pullRefreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "刷新完成啦", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });

        mViewPaper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                dots.get(position).setBackgroundResource(
                        R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(
                        R.drawable.dot_normal);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        view1.setOnClickListener(this);
        view2.setOnClickListener(this);
        view3.setOnClickListener(this);
        view4.setOnClickListener(this);
    }

    //利用线程池定时执行动画轮播
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(new ViewPageTask(), 3,
                3, TimeUnit.SECONDS);
    }

    @Override
    public void onStop() {
        super.onStop();
        scheduledExecutorService.shutdown();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.commodity_layout_first:
                Intent intent1=new Intent(getActivity(), CommodityInfoActivity.class);
                intent1.putExtra("position",1);
                getActivity().startActivity(intent1);
                break;
            case R.id.commodity_layout_second:
                Intent intent2=new Intent(getActivity(), CommodityInfoActivity.class);
                intent2.putExtra("position",2);
                getActivity().startActivity(intent2);
                break;
            case R.id.commodity_layout_third:
                Intent intent3=new Intent(getActivity(), CommodityInfoActivity.class);
                intent3.putExtra("position",3);
                getActivity().startActivity(intent3);
                break;
            case R.id.commodity_layout_fourth:
                Intent intent4=new Intent(getActivity(), CommodityInfoActivity.class);
                intent4.putExtra("position",4);
                getActivity().startActivity(intent4);
                break;
            default:
                break;
        }
    }

    private class ViewPageTask implements Runnable {

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
            // super.destroyItem(container, position, object);
            // view.removeView(view.getChildAt(position));
            // view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }
}

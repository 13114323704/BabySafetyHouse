package com.example.babysafetyhouse.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.example.babysafetyhouse.R;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;

/**
 * Created by 94284 on 2017/5/1.
 */

public class MotherCircleFragment extends Fragment {

    private View main_view,view_freshthings, view_question;
    private PullRefreshLayout pullRefreshLayout_freshthings,pullRefreshLayout_question;
    private Handler handler = new Handler();

    private FloatingActionsMenu floatingActionsMenu;
    private FloatingActionButton floatingActionButton_freshthings,floatingActionButton_question;

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ArrayList<View> viewContainter = new ArrayList<View>();
    private ArrayList<String> titleContainer = new ArrayList<String>();
    private PagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        main_view = inflater.inflate(R.layout.fragment_mothercircle, container, false);

        initData();
        initView();
        initListener();

        return main_view;
    }

    private void initData(){
        view_freshthings = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_freshthings, null);
        view_question = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_question, null);

        viewContainter.clear();
        titleContainer.clear();

        viewContainter.add(view_freshthings);
        viewContainter.add(view_question);

        titleContainer.add("育儿新鲜事");
        titleContainer.add("问答");
    }

    private void initView() {

        viewPager = (ViewPager)main_view.findViewById(R.id.viewPager);
        tabLayout = (TabLayout) main_view.findViewById(R.id.tablayout);
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

        floatingActionsMenu=(FloatingActionsMenu)main_view.findViewById(R.id.multiple_actions_down);
        floatingActionButton_freshthings=(FloatingActionButton)main_view.findViewById(R.id.float_button_freshthings);
        floatingActionButton_question=(FloatingActionButton)main_view.findViewById(R.id.float_button_question);

        initViewFreshThings();
        initViewQuestion();
    }

    private void initViewFreshThings(){
        pullRefreshLayout_freshthings=(PullRefreshLayout)view_freshthings.findViewById(R.id.pullRefreshLayout_freshthings);
    }

    private void initViewQuestion(){
        pullRefreshLayout_question=(PullRefreshLayout)view_question.findViewById(R.id.pullRefreshLayout_question);
    }

    private void initListener() {
        pullRefreshLayout_freshthings.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout_freshthings.setRefreshing(false);
                        Toast.makeText(getActivity(), "刷新完成啦", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });
        pullRefreshLayout_question.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullRefreshLayout_question.setRefreshing(false);
                        Toast.makeText(getActivity(), "刷新完成啦", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });
        floatingActionButton_freshthings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.toggle();
                Toast.makeText(getActivity(),"由于没有服务器，该功能暂未开放哦！",Toast.LENGTH_LONG).show();
            }
        });
        floatingActionButton_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.toggle();
                Toast.makeText(getActivity(),"由于没有服务器，该功能暂未开放哦！",Toast.LENGTH_LONG).show();
            }
        });
    }
}

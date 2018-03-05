package com.example.babysafetyhouse.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baoyz.widget.PullRefreshLayout;
import com.example.babysafetyhouse.CollectActivity;
import com.example.babysafetyhouse.LoginActivity;
import com.example.babysafetyhouse.LoveHouseActivity;
import com.example.babysafetyhouse.OrderActivity;
import com.example.babysafetyhouse.R;
import com.example.babysafetyhouse.TrackActivity;
import com.example.babysafetyhouse.WalletActivity;


/**
 * Created by 94284 on 2017/5/1.
 */

public class MineFragment extends Fragment implements View.OnClickListener {

    private View main_view;
    private PullRefreshLayout pullRefreshLayout;
    //用于处理下拉刷新事件
    private Handler handler = new Handler();
    private Button btn_loc, btn_login;
    private ImageView user_image;
    private RelativeLayout relativeLayout_order_all,relativeLayout_order_pay,relativeLayout_order_comment,relativeLayout_mywallet;
    private RelativeLayout relativeLayout_love_house,relativeLayout_collect,relativeLayout_track,relativeLayout_advice,relativeLayout_about;
    private SharedPreferences sharedPreferences;

    //是否登录
    private boolean isLogin;
    //用户名
    private String username;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        main_view = inflater.inflate(R.layout.fragment_mine, container, false);
        initData();
        initView();
        initListener();
        return main_view;
    }

    private void initData() {
        sharedPreferences = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        username=sharedPreferences.getString("username", "");
        String isreallogin = sharedPreferences.getString("isreallogin", "");

        if (isreallogin.equals("true")) {
            isLogin = true;
        } else {
            isLogin = false;
        }
    }

    private void initView() {
        pullRefreshLayout = (PullRefreshLayout) main_view.findViewById(R.id.pullRefreshLayout_mine);

        btn_loc = (Button) main_view.findViewById(R.id.user_loc);
        btn_login = (Button) main_view.findViewById(R.id.btn_login);
        user_image=(ImageView)main_view.findViewById(R.id.user_image);

        if (isLogin) {
            btn_login.setText(username.toString());
            btn_loc.setText("武汉");
            user_image.setImageResource(R.mipmap.user_login);
        }

        relativeLayout_mywallet=(RelativeLayout)main_view.findViewById(R.id.relative_mywallet);
        relativeLayout_collect=(RelativeLayout)main_view.findViewById(R.id.relative_collect);
        relativeLayout_love_house=(RelativeLayout)main_view.findViewById(R.id.relative_love_house);
        relativeLayout_track=(RelativeLayout)main_view.findViewById(R.id.relative_track);
        relativeLayout_order_all=(RelativeLayout)main_view.findViewById(R.id.relative_order_all);
        relativeLayout_order_pay=(RelativeLayout)main_view.findViewById(R.id.relative_order_pay);
        relativeLayout_order_comment=(RelativeLayout)main_view.findViewById(R.id.relative_order_comment);
        relativeLayout_advice=(RelativeLayout)main_view.findViewById(R.id.relative_advice);
        relativeLayout_about=(RelativeLayout)main_view.findViewById(R.id.relative_about);

        relativeLayout_mywallet.setOnClickListener(this);
        relativeLayout_collect.setOnClickListener(this);
        relativeLayout_love_house.setOnClickListener(this);
        relativeLayout_track.setOnClickListener(this);
        relativeLayout_order_all.setOnClickListener(this);
        relativeLayout_order_pay.setOnClickListener(this);
        relativeLayout_order_comment.setOnClickListener(this);
        relativeLayout_advice.setOnClickListener(this);
        relativeLayout_about.setOnClickListener(this);
    }

    private void initListener() {
        btn_login.setOnClickListener(this);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (!isLogin) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    getActivity().startActivity(intent);
                } else {
                    new AlertDialog.Builder(getActivity()).setTitle("提示")
                            .setMessage("退出登录？")
                            .setNegativeButton("取消",null)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putString("isreallogin","false");
                                    editor.commit();
                                    btn_loc.setText("归属地");
                                    btn_login.setText("注册/登录");
                                    user_image.setImageResource(R.mipmap.user);
                                    isLogin=false;
                                    Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .show();
                }

                break;
            case R.id.relative_mywallet:
                Intent walletintent=new Intent(getActivity(), WalletActivity.class);
                getActivity().startActivity(walletintent);
                break;
            case R.id.relative_collect:
                Intent collectintent=new Intent(getActivity(), CollectActivity.class);
                getActivity().startActivity(collectintent);
                break;
            case R.id.relative_love_house:
                Intent intent_love_house=new Intent(getActivity(), LoveHouseActivity.class);
                getActivity().startActivity(intent_love_house);
                break;
            case R.id.relative_track:
                Intent intent_track=new Intent(getActivity(), TrackActivity.class);
                getActivity().startActivity(intent_track);
                break;
            case R.id.relative_order_all:
                Intent intent=new Intent(getActivity(), OrderActivity.class);
                intent.putExtra("position",1);
                getActivity().startActivity(intent);
                break;
            case R.id.relative_order_pay:
                Intent intent1=new Intent(getActivity(), OrderActivity.class);
                intent1.putExtra("position",2);
                getActivity().startActivity(intent1);
                break;
            case R.id.relative_order_comment:
                Intent intent2=new Intent(getActivity(), OrderActivity.class);
                intent2.putExtra("position",3);
                getActivity().startActivity(intent2);
                break;
            case R.id.relative_advice:
                Toast.makeText(getActivity(),"暂未开放，敬请期待！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.relative_about:
                Toast.makeText(getActivity(),"暂未开放，敬请期待！",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}

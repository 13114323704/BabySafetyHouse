package com.example.babysafetyhouse;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.babysafetyhouse.fragment.BuyFragment;
import com.example.babysafetyhouse.fragment.MainPagerFragment;
import com.example.babysafetyhouse.fragment.MineFragment;
import com.example.babysafetyhouse.fragment.MotherCircleFragment;
import com.example.babysafetyhouse.statusbar.BaseActivity;
import com.example.babysafetyhouse.statusbar.SystemBarTintManager;
import com.example.babysafetyhouse.utils.CircleImageView;

import kr.co.namee.permissiongen.PermissionFail;
import kr.co.namee.permissiongen.PermissionGen;
import kr.co.namee.permissiongen.PermissionSuccess;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout linearLayout_mainpager,linearLayout_circle,linearLayout_buy,linearLayout_mine;
    private ImageView image_mainpager_label,image_circle_label,image_buy_label,image_mine_label;

    private FragmentManager fm;
    private FragmentTransaction ft;
    private SystemBarTintManager systemBarTintManager;

    private MainPagerFragment mainPagerFragment;
    private MotherCircleFragment motherCircleFragment;
    private BuyFragment buyFragment;
    private MineFragment mineFragment;

    //再按一次退出相关变量
    private static long currenttime=0;//获取当前时间
    private long touchtime=2000;//反映时间
    private long lasttime=0;//上次按键时间

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initStatusbar();
        initView();
        initListener();
        initMainPager();

    }

    private void initStatusbar() {
        systemBarTintManager = new SystemBarTintManager(MainActivity.this);
        systemBarTintManager.setStatusBarTintEnabled(true);
        systemBarTintManager.setStatusBarTintResource(R.color.statusColor);
    }

    private void initView() {
        linearLayout_mainpager = (LinearLayout) this.findViewById(R.id.mainpager_linelayout);
        linearLayout_circle = (LinearLayout) this.findViewById(R.id.circle_linelayout);
        linearLayout_buy = (LinearLayout) this.findViewById(R.id.buy_linelayout);
        linearLayout_mine = (LinearLayout) this.findViewById(R.id.mine_linelayout);

        image_mainpager_label=(ImageView)this.findViewById(R.id.mainpager_label);
        image_circle_label=(ImageView)this.findViewById(R.id.circle_label);
        image_buy_label=(ImageView)this.findViewById(R.id.buy_label);
        image_mine_label=(ImageView)this.findViewById(R.id.mine_label);
    }

    private void initListener() {
        linearLayout_mainpager.setOnClickListener(this);
        linearLayout_circle.setOnClickListener(this);
        linearLayout_buy.setOnClickListener(this);
        linearLayout_mine.setOnClickListener(this);
    }

    private void initMainPager() {
        mainPagerFragment=new MainPagerFragment();
        motherCircleFragment=new MotherCircleFragment();
        buyFragment=new BuyFragment();
        mineFragment=new MineFragment();

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.content, mainPagerFragment,"MainPagerFragment");
        ft.commit();

        freshlabel(1);
    }


    @Override
    public void onClick(View v) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (v.getId()) {
            case R.id.mainpager_linelayout:
                ft.replace(R.id.content, mainPagerFragment,"MainPagerFragment");
                freshlabel(1);
                break;

            case R.id.circle_linelayout:
                ft.replace(R.id.content, motherCircleFragment,"MotherCircleFragment");
                freshlabel(2);
                break;

            case R.id.buy_linelayout:
                ft.replace(R.id.content, buyFragment,"BuyFragment");
                freshlabel(3);
                break;

            case R.id.mine_linelayout:
                ft.replace(R.id.content, mineFragment,"MineFragment");
                freshlabel(4);
                break;

            default:
                break;
        }
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        /*if(requestCode==0){
            String result = data.getExtras().getString("result");
            Toast.makeText(MainActivity.this,"扫描结果为"+result,Toast.LENGTH_LONG).show();
        }*/
        //交到子fragment去处理
        this.getSupportFragmentManager().findFragmentByTag("MainPagerFragment").onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            currenttime=System.currentTimeMillis();//获取当前时间
            if(currenttime-lasttime>=touchtime) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                lasttime=currenttime;//将当前时间记录为上次按键时间
            }
            else {
                //如果小于时间间隔说明连续按键，希望退出
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent);
            }
        }
        //	return super.onKeyDown(keyCode, event);
        return true;//需要返回当前的按键信息，如返回super，将直接退出不会有按两次退出效果
    }

    private void freshlabel(int i){
        switch (i){
            case 1:
                image_mainpager_label.setImageResource(R.mipmap.mainpager_pressed);
                image_circle_label.setImageResource(R.mipmap.circle_normal);
                image_buy_label.setImageResource(R.mipmap.buy_normal);
                image_mine_label.setImageResource(R.mipmap.mine_normal);
                break;
            case 2:
                image_mainpager_label.setImageResource(R.mipmap.mainpager_normal);
                image_circle_label.setImageResource(R.mipmap.circle_pressed);
                image_buy_label.setImageResource(R.mipmap.buy_normal);
                image_mine_label.setImageResource(R.mipmap.mine_normal);
                break;
            case 3:
                image_mainpager_label.setImageResource(R.mipmap.mainpager_normal);
                image_circle_label.setImageResource(R.mipmap.circle_normal);
                image_buy_label.setImageResource(R.mipmap.buy_pressed);
                image_mine_label.setImageResource(R.mipmap.mine_normal);
                break;
            case 4:
                image_mainpager_label.setImageResource(R.mipmap.mainpager_normal);
                image_circle_label.setImageResource(R.mipmap.circle_normal);
                image_buy_label.setImageResource(R.mipmap.buy_normal);
                image_mine_label.setImageResource(R.mipmap.mine_pressed);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //适配6.0以上机型请求权限
        PermissionGen.with(MainActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.CAMERA
                )
                .request();
    }

    //以下三个方法用于6.0以上权限申请适配
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionGen.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @PermissionSuccess(requestCode = 100)
    public void doSomething(){
        //Toast.makeText(this, "相关权限已允许", Toast.LENGTH_SHORT).show();
    }

    @PermissionFail(requestCode = 100)
    public void doFailSomething(){
        //Toast.makeText(this, "相关权限已拒绝", Toast.LENGTH_SHORT).show();
    }
}

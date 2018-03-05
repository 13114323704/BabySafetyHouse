package com.example.babysafetyhouse;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.babysafetyhouse.statusbar.BaseActivity;
import com.example.babysafetyhouse.statusbar.SystemBarTintManager;

/**
 * Created by 94284 on 2017/5/8.
 */

public class RechargeActivity extends BaseActivity {

    private SystemBarTintManager mTintManager;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recharge);
        initStatusbar();
        initView();
    }

    private void initStatusbar() {
        mTintManager = new SystemBarTintManager(this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.statusColor);
    }

    private void initView(){
        editText=(EditText)this.findViewById(R.id.edittext_getmoney);
    }

    public void image_back(View v) {
        finish();
    }

    public void recharge1(View v) {
        editText.setText("1");
        editText.setSelection(editText.getText().length());
    }

    public void recharge2(View v) {
        editText.setText("2");
        editText.setSelection(editText.getText().length());
    }

    public void recharge3(View v) {
        editText.setText("3");
        editText.setSelection(editText.getText().length());
    }

    public void recharge4(View v) {
        editText.setText("5");
        editText.setSelection(editText.getText().length());
    }

    public void recharge5(View v) {
        editText.setText("10");
        editText.setSelection(editText.getText().length());
    }

    public void recharge6(View v) {
        editText.setText("50");
        editText.setSelection(editText.getText().length());
    }

    public void recharge7(View v) {
        editText.setText("100");
        editText.setSelection(editText.getText().length());
    }

    public void recharge8(View v) {
        editText.setText("200");
        editText.setSelection(editText.getText().length());
    }

    public void bywechat(View v){
        Toast.makeText(RechargeActivity.this,"该功能暂未开放哦",Toast.LENGTH_SHORT).show();
    }

    public void byzfb(View v){
        Toast.makeText(RechargeActivity.this,"该功能暂未开放哦",Toast.LENGTH_SHORT).show();
    }

}

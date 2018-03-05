package com.example.babysafetyhouse;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.babysafetyhouse.statusbar.BaseActivity;
import com.example.babysafetyhouse.statusbar.SystemBarTintManager;

/**
 * Created by 94284 on 2017/5/6.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {
    private SystemBarTintManager mTintManager;
    private EditText editText_username, editText_password;
    private Button btn_register;
    private SharedPreferences sharedPreferences;
    private ImageView image_exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initStatusbar();
        initData();
        initView();
        initListener();
    }

    private void initStatusbar() {
        mTintManager = new SystemBarTintManager(RegisterActivity.this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.android_purple);
    }

    private void initData(){
        sharedPreferences=getSharedPreferences("userinfo",MODE_PRIVATE);
    }

    private void initView() {
        editText_username = (EditText) this.findViewById(R.id.edittext_username);
        editText_password = (EditText) this.findViewById(R.id.edittext_password);
        btn_register = (Button) this.findViewById(R.id.btn_register_rightaway);
        image_exit=(ImageView)this.findViewById(R.id.image_exit);
    }

    private void initListener() {
        btn_register.setOnClickListener(this);
        image_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor=sharedPreferences.edit();
        switch (v.getId()) {
            case R.id.btn_register_rightaway:
                editor.putString("username",editText_username.getText().toString());
                editor.putString("password",editText_password.getText().toString());
                editor.commit();
                Toast.makeText(RegisterActivity.this,"注册成功！",Toast.LENGTH_LONG).show();
                break;
            case R.id.image_exit:
                finish();
                break;
        }

    }
}

package com.example.babysafetyhouse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private SystemBarTintManager mTintManager;
    private Button btn_real_login, btn_real_register;
    private EditText edittext_username, edittext_password;
    private ImageView image_exit;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initStatusbar();
        initData();
        initView();
        initListener();

    }


    private void initStatusbar() {
        mTintManager = new SystemBarTintManager(LoginActivity.this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.android_purple);
    }

    private void initData() {

        sharedPreferences = getSharedPreferences("userinfo", MODE_PRIVATE);
    }

    private void initView() {
        btn_real_login = (Button) this.findViewById(R.id.btn_real_login);
        btn_real_register = (Button) this.findViewById(R.id.btn_real_register);
        edittext_username = (EditText) this.findViewById(R.id.edittext_username);
        edittext_password = (EditText) this.findViewById(R.id.edittext_password);
        image_exit = (ImageView) this.findViewById(R.id.image_exit);
    }

    private void initListener() {
        btn_real_login.setOnClickListener(this);
        btn_real_register.setOnClickListener(this);
        image_exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (v.getId()) {
            case R.id.btn_real_login:
                String string_username = sharedPreferences.getString("username", "");
                String string_password = sharedPreferences.getString("password", "");
                String input_username=edittext_username.getText().toString();
                String input_password=edittext_password.getText().toString();
                if(TextUtils.isEmpty(input_username)||TextUtils.isEmpty(input_password)){
                    Toast.makeText(LoginActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                }else{
                    if (input_username.equals(string_username) && input_password.equals(string_password)) {
                        SharedPreferences.Editor editor1=sharedPreferences.edit();
                        editor1.putString("isreallogin","true");
                        editor1.commit();
                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "您输入的用户名或者密码不正确", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
            case R.id.btn_real_register:
                Intent intent1 = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(intent1);
                break;
            case R.id.image_exit:
                finish();
                break;
        }
        editor.commit();
    }
}

package com.example.babysafetyhouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babysafetyhouse.statusbar.BaseActivity;
import com.example.babysafetyhouse.statusbar.SystemBarTintManager;

/**
 * Created by 94284 on 2017/5/6.
 */

public class DetailInfoActivity extends BaseActivity {

    private ImageView imageView, image_love;
    private SystemBarTintManager mTintManager;
    private TextView ratingbar_text;
    private RatingBar ratingBar;
    private TextView textView_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailinfo);

        initStatusbar();
        initView();
        initListener();

    }

    private void initStatusbar() {
        mTintManager = new SystemBarTintManager(DetailInfoActivity.this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.statusColor);
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageview);
        ratingbar_text = (TextView) this.findViewById(R.id.ratingBar_text);
        ratingBar = (RatingBar) this.findViewById(R.id.ratingBar);
        image_love=(ImageView)this.findViewById(R.id.image_love);
        textView_location=(TextView)findViewById(R.id.textView_location);

        Intent intent = getIntent();
        String locationinfo=intent.getStringExtra("locationinfo");
        if(!TextUtils.isEmpty(locationinfo)){
            textView_location.setText("位于"+locationinfo);
        }
        int position = intent.getIntExtra("position", 1);
        switch (position) {
            case 1:
                imageView.setBackgroundResource(R.mipmap.image_info1);
                ratingBar.setRating(5.0f);
                ratingbar_text.setText("5.0");
                break;
            case 2:
                ratingBar.setRating(4.5f);
                ratingbar_text.setText("4.5");
                imageView.setBackgroundResource(R.mipmap.image_info2);
                break;
            case 3:
                ratingBar.setRating(4.0f);
                ratingbar_text.setText("4.0");
                imageView.setBackgroundResource(R.mipmap.image_info3);
                break;
            case 4:
                ratingBar.setRating(4.5f);
                ratingbar_text.setText("4.5");
                imageView.setBackgroundResource(R.mipmap.image_info4);
                break;
            default:
                ratingBar.setRating(3.0f);
                ratingbar_text.setText("3.0");
                imageView.setBackgroundResource(R.mipmap.image_info1);
                break;
        }


    }

    private void initListener() {
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingbar_text.setText(rating + "");
            }
        });

        image_love.setOnClickListener(new View.OnClickListener() {
            boolean islove=false;
            @Override
            public void onClick(View v) {
                islove=!islove;
                if(islove){
                    image_love.setImageResource(R.mipmap.love_focus);
                    Toast.makeText(DetailInfoActivity.this,"已添加到喜欢",Toast.LENGTH_SHORT).show();
                }else{
                    image_love.setImageResource(R.mipmap.love_normal);
                    Toast.makeText(DetailInfoActivity.this,"已取消喜欢",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void image_back(View v) {
        finish();
    }

    public void order_time1(View v) {
        Intent intent = new Intent(DetailInfoActivity.this, QRCodeActivity.class);
        DetailInfoActivity.this.startActivity(intent);
    }

    public void order_time2(View v) {
        Intent intent = new Intent(DetailInfoActivity.this, QRCodeActivity.class);
        DetailInfoActivity.this.startActivity(intent);
    }

    public void order_time3(View v) {
        Intent intent = new Intent(DetailInfoActivity.this, QRCodeActivity.class);
        DetailInfoActivity.this.startActivity(intent);
    }


}

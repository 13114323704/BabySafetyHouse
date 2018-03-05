package com.example.babysafetyhouse;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.babysafetyhouse.statusbar.BaseActivity;
import com.example.babysafetyhouse.statusbar.SystemBarTintManager;

/**
 * Created by 94284 on 2017/5/8.
 */

public class CommodityInfoActivity extends BaseActivity {

    private SystemBarTintManager mTintMagager;
    private RatingBar ratingBar;
    private TextView ratingBar_text;
    private ImageView image_love,image_commodity_info;

    private View view_evaluate1,view_evaluate2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_detailinfo);
        initStatusbar();
        initView();
        initListener();
    }

    private void initStatusbar() {
        mTintMagager=new SystemBarTintManager(CommodityInfoActivity.this);
        mTintMagager.setStatusBarTintEnabled(true);
        mTintMagager.setStatusBarTintResource(R.color.statusColor);
    }

    private void initView(){
        ratingBar=(RatingBar)this.findViewById(R.id.ratingBar);
        ratingBar_text=(TextView)this.findViewById(R.id.ratingBar_text);
        image_love=(ImageView)this.findViewById(R.id.image_love);
        image_commodity_info=(ImageView)this.findViewById(R.id.image_commodity_info);
        int position=getIntent().getIntExtra("position",1);
        switch (position){
            case 1:
                image_commodity_info.setImageResource(R.mipmap.commodity_info1);
                break;
            case 2:
                image_commodity_info.setImageResource(R.mipmap.commodity_info2);
                break;
            case 3:
                image_commodity_info.setImageResource(R.mipmap.commodity_info3);
                break;
            case 4:
                image_commodity_info.setImageResource(R.mipmap.commodity_info4);
                break;
            default:
                image_commodity_info.setImageResource(R.mipmap.commodity_info1);
                break;
        }
        view_evaluate1=(View)this.findViewById(R.id.comment_layout_first);
        view_evaluate2=(View)this.findViewById(R.id.comment_layout_third);

        TextView text_content=(TextView)view_evaluate1.findViewById(R.id.content_text);
        text_content.setText("这个商品非常不错哦，用过一次感觉非常好，下次还会再来这里买的！");
        TextView text_content2=(TextView)view_evaluate2.findViewById(R.id.content_text);
        text_content2.setText("很不错的一个宝贝，不仅质量好，而且价格也比较合理，建议想买的妈妈就赶紧买了吧！五星好评！/笑脸");
    }

    private void initListener(){
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar_text.setText(rating+"");
            }
        });

        image_love.setOnClickListener(new View.OnClickListener() {
            boolean islove=false;
            @Override
            public void onClick(View v) {
                islove=!islove;
                if(islove){
                    image_love.setImageResource(R.mipmap.love_focus);
                    Toast.makeText(CommodityInfoActivity.this,"已添加到喜欢",Toast.LENGTH_SHORT).show();
                }else{
                    image_love.setImageResource(R.mipmap.love_normal);
                    Toast.makeText(CommodityInfoActivity.this,"已取消喜欢",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void image_back(View v){
        finish();
    }
}

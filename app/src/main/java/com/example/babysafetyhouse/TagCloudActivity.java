package com.example.babysafetyhouse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.babysafetyhouse.statusbar.BaseActivity;
import com.example.babysafetyhouse.statusbar.SystemBarTintManager;
import com.example.babysafetyhouse.utils.TextTagAdapter;
import com.moxun.tagcloudlib.view.TagCloudView;

/**
 * Created by 94284 on 2017/5/6.
 */

public class TagCloudActivity extends BaseActivity{

    private SystemBarTintManager mTintManager;

    private TagCloudView tagCloudView;
    private TextTagAdapter textTagAdapter;

    private ImageView image_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tagcloud);
        initStatusBar();
        initView();
    }

    private void initStatusBar(){
        mTintManager=new SystemBarTintManager(TagCloudActivity.this);
        mTintManager.setStatusBarTintEnabled(true);
        mTintManager.setStatusBarTintResource(R.color.statusColor);
    }

    private void initView(){
        tagCloudView=(TagCloudView)this.findViewById(R.id.tag_cloud);
        tagCloudView.setBackgroundColor(Color.TRANSPARENT);

        textTagAdapter=new TextTagAdapter(new String[20]);
        tagCloudView.setAdapter(textTagAdapter);
        tagCloudView.setOnTagClickListener(new TagCloudView.OnTagClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, int position) {
                Intent intent=new Intent(TagCloudActivity.this,DetailInfoActivity.class);
                intent.putExtra("position",position);
                TagCloudActivity.this.startActivity(intent);
            }
        });

        image_back=(ImageView)this.findViewById(R.id.image_back);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

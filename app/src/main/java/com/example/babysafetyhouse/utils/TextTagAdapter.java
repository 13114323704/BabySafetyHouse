package com.example.babysafetyhouse.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.moxun.tagcloudlib.view.TagsAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 94284 on 2017/5/1.
 */

public class TextTagAdapter extends TagsAdapter {

    private List<String> dataSet = new ArrayList<>();

    public TextTagAdapter(@NonNull String... data) {
        dataSet.clear();
        Collections.addAll(dataSet, data);
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public View getView(final Context context, final int position, ViewGroup parent) {
        TextView tv = new TextView(context);
        if(position==1){
            tv.setText("众圆安全屋" );
        }else if(position==2){
            tv.setText("欢乐宝贝安全屋");
        }else if(position==3){
            tv.setText("宜佳宝贝安全屋");
        }else if(position==4){
            tv.setText("乐宝安全屋");
        }else{
            tv.setText( "安全屋"+position);
        }
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(10);
        tv.setPadding(5,5,5,5);
        /*tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, "Tag " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });*/
        tv.setTextColor(Color.WHITE);
        return tv;
    }

    @Override
    public Object getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getPopularity(int position) {
        return position % 7;
    }

    @Override
    public void onThemeColorChanged(View view, int themeColor) {
        view.setBackgroundColor(themeColor);
    }
}

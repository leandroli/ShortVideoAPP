package com.rett.androidcouresfinalwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.rett.androidcouresfinalwork.model.VideoInfo;

import java.util.LinkedList;
import java.util.List;

public class VideoPlay extends AppCompatActivity{

    private List<VideoInfo> videoInfos;
    private ViewPager2 videoPager;
    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String list = intent.getStringExtra("videoInfos");
        String feedurl = intent.getStringExtra("feedurl");
        Gson gson = new Gson();
        List<LinkedTreeMap> maps = gson.fromJson(list, List.class);
        videoInfos = new LinkedList<>();
        int flag = 0;
        if(maps != null) {
            for (LinkedTreeMap map: maps) {
                Log.d("map", String.valueOf(map));
                if (feedurl.equals(map.get("feedurl"))) {
                    flag = 1;
                }
                if (flag == 1) {
                    VideoInfo videoInfo = new VideoInfo(map);
                    videoInfos.add(videoInfo);
                }
            }
        }
        // 设置全屏
        /*if (Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }*/
        setContentView(R.layout.video_play);
        videoPager = findViewById(R.id.video_pager);
        videoAdapter = new VideoAdapter(this);
        videoPager.setAdapter(videoAdapter);
        videoAdapter.setVideoInfoList(videoInfos);
    }
}

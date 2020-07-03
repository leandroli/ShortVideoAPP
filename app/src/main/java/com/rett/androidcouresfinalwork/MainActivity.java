package com.rett.androidcouresfinalwork;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.gson.Gson;
import com.rett.androidcouresfinalwork.model.VideoInfo;
import com.rett.androidcouresfinalwork.network.TiktokAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: 李卓
 * @Date: 2020年6月4日 00点15分
 * @LastEditors: 李卓
 * @LastEditTime: 2020年6月4日 00点15分
 */

public class MainActivity extends AppCompatActivity{
    private ViewPager2 videoPager;
    private VideoAdapter videoAdapter;
    private TextView listPager;
    private TextView message;
    private TextView following;
    private ImageView add;
    private List<VideoInfo> videoInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //实现全屏
        if (Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        setContentView(R.layout.activity_main);

        //viewPager2和它的Adapter
        videoPager = findViewById(R.id.video_pager);
        videoAdapter = new VideoAdapter(this);

        //导航栏里的几个View
        message = findViewById(R.id.message);
        following = findViewById(R.id.following);
        add = findViewById(R.id.add_box);
        listPager = findViewById(R.id.recycler);

        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "功能正在开发中，敬请期待！", Toast.LENGTH_SHORT).show();
            }
        });

        following.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "功能正在开发中，敬请期待！", Toast.LENGTH_SHORT).show();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "功能正在开发中，敬请期待！", Toast.LENGTH_SHORT).show();
            }
        });

        //点击视频列会跳转到RecyclerView实现的视频列中
        listPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, VideoListActivity.class);
                Gson gson = new Gson();
                String videoinfolist = gson.toJson(videoInfoList);
                intent.putExtra("videoInfos", videoinfolist);
                startActivity(intent);
            }
        });

        //使用Retrofit获得数据
        getData();

        videoPager.setAdapter(videoAdapter);
    }

    @Override
    protected void onResume() {
        //实现全屏
        if (Build.VERSION.SDK_INT < 19) {
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        super.onResume();
    }

    private void setVideoInfos(List<VideoInfo> videoinfos) {
        this.videoInfoList = videoinfos;
    }

    private void getData()
    {
        //设置baseurl并使用Gson转换数据
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //绑定retrofit与tiktokAPI，设置请求成功与请求失败两种情况下的行为
        TiktokAPI tiktokAPI = retrofit.create(TiktokAPI.class);
        tiktokAPI.getVideoInfo().enqueue(new Callback<List<VideoInfo>>() {
            @Override
            public void onResponse(Call<List<VideoInfo>> call, Response<List<VideoInfo>> response) {
                if(response.body() != null){
                    List<VideoInfo> videoInfos = response.body();
                    Log.d("retrofit", videoInfos.toString());
                    if(videoInfos.size() != 0){
                        videoAdapter.setVideoInfoList(videoInfos);
                        videoAdapter.notifyDataSetChanged();
                        setVideoInfos(videoInfos);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VideoInfo>> call, Throwable t) {
                Log.d("retrofit", "on failure");
                Toast.makeText(MainActivity.this, "There is something wrong with network!\nPlease check it.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


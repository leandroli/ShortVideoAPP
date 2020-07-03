package com.rett.androidcouresfinalwork;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.rett.androidcouresfinalwork.model.VideoInfo;
import com.rett.androidcouresfinalwork.network.TiktokAPI;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VideoListActivity extends AppCompatActivity {

    private RecyclerView rv_video_list;
    private VideoListAdapter itemAdapter;
    private LinearLayoutManager layoutManager;
    private List<VideoInfo> videoInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        Intent intent = getIntent();
        String list = intent.getStringExtra("videoInfos");
        Gson gson = new Gson();
        List<LinkedTreeMap> maps = gson.fromJson(list, List.class);
        Log.d("videoInfos", String.valueOf(videoInfos));
        videoInfos = new LinkedList<>();
        if(maps != null) {
            for (LinkedTreeMap map: maps) {
                VideoInfo videoInfo = new VideoInfo(map);
                videoInfos.add(videoInfo);
            }
        }

        rv_video_list = findViewById(R.id.rv_video_list);
        rv_video_list.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rv_video_list.setLayoutManager(layoutManager);
        itemAdapter = new VideoListAdapter(this);
        rv_video_list.setAdapter(itemAdapter);
        itemAdapter.setVideoInfoList(videoInfos);
    }

    /*private void getData()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TiktokAPI tiktokAPI = retrofit.create(TiktokAPI.class);
        tiktokAPI.getVideoInfo().enqueue(new Callback<List<VideoInfo>>() {
            @Override
            public void onResponse(Call<List<VideoInfo>> call, Response<List<VideoInfo>> response) {
                if(response.body() != null){
                    List<VideoInfo> videoInfos = response.body();
                    Log.d("retrofit2", videoInfos.toString());
                    if(videoInfos.size() != 0){
                        itemAdapter.setVideoInfoList(videoInfos);
                        itemAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<VideoInfo>> call, Throwable t) {
                Log.d("retrofit", "on failure");
            }
        });
    }*/

}


package com.rett.androidcouresfinalwork;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.rett.androidcouresfinalwork.model.VideoInfo;

import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {

    private Context context;
    private List<VideoInfo> videoInfos;

    public VideoListAdapter(Context context){
        this.context = context;
    }


    @NonNull
    @Override
    public VideoListAdapter.VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        VideoViewHolder videoViewHolder = new VideoViewHolder(view);
        return videoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull VideoListAdapter.VideoViewHolder holder, int position) {

        VideoInfo videoInfo = videoInfos.get(position);

        holder.description.setText(videoInfo.description);
        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions().frame(0))
                .load(videoInfo.getFeedurl())
                //.override(2000,3000)
                //.fitCenter()
                .centerCrop()
                .into(holder.imageView1);
        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions().frame(3000000))
                .load(videoInfo.getFeedurl())
                //.override(2000,3000)
                //.fitCenter()
                .centerCrop()
                .into(holder.imageView2);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VideoPlay.class);
                Gson gson = new Gson();
                String videoinfolist = gson.toJson(videoInfos);
                intent.putExtra("videoInfos", videoinfolist);
                intent.putExtra("feedurl", videoInfo.getFeedurl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoInfos == null ? 0 : videoInfos.size();
    }

    public void setVideoInfoList(List<VideoInfo> videoInfos) {
        this.videoInfos = videoInfos;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView1;
        private ImageView imageView2;
        private TextView description;
        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.image1);
            imageView2 = itemView.findViewById(R.id.image2);
            description = itemView.findViewById(R.id.description);
        }
    }

}

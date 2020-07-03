package com.rett.androidcouresfinalwork.network;

import com.rett.androidcouresfinalwork.model.VideoInfo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @Author: 李卓
 * @Date: 2020年6月4日 00点15分
 * @LastEditors: 李卓
 * @LastEditTime: 2020年6月4日 00点15分
 */

public interface TiktokAPI {
    @GET("api/invoke/video/invoke/video")
    Call<List<VideoInfo>> getVideoInfo();
}

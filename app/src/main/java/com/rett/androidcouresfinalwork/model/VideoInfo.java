package com.rett.androidcouresfinalwork.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

/**
 * @Author: 李卓
 * @Date: 2020年6月4日 00点15分
 * @LastEditors: 李卓
 * @LastEditTime: 2020年6月4日 00点15分
 */

public class VideoInfo {
    @SerializedName("_id")
    public String id;
    @SerializedName("feedurl")
    public String feedurl;
    @SerializedName("nickname")
    public String nickname;
    @SerializedName("description")
    public String description;
    @SerializedName("likecount")
    public int likecount;
    @SerializedName("avatar")
    public String avatar;

    public VideoInfo(LinkedTreeMap map) {
        this.id = (String)map.get("id");
        this.feedurl = (String)map.get("feedurl");
        this.nickname = (String)map.get("nickname");
        this.description = (String)map.get("description");
        this.likecount = ((Double)map.get("likecount")).intValue();
        this.avatar = (String)map.get("avatar");
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", feedurl='" + feedurl + '\'' +
                ", nickname='" + nickname + '\'' +
                ", description='" + description + '\'' +
                ", likecount=" + likecount +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public int getLikecount() {
        return likecount;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDescription() {
        return description;
    }

    public String getFeedurl() {
        return feedurl;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}

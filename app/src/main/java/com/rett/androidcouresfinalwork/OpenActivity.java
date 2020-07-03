package com.rett.androidcouresfinalwork;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author: 李卓
 * @Date: 2020年6月4日 00点15分
 * @LastEditors: 李卓
 * @LastEditTime: 2020年6月4日 00点15分
 */

public class OpenActivity extends AppCompatActivity {
    private Handler handler;

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

        setContentView(R.layout.activity_open);
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setFlags(flag, flag);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //跳转到首界面
                Intent intent = new Intent(OpenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);//延迟2S发送信息


    }
}

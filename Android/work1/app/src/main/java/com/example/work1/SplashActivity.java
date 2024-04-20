package com.example.work1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {
    private LinearLayout ll;
    private Button skip;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ll = findViewById(R.id.ll);
        skip = findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        setAlphaAnimation();
    }
    public void setAlphaAnimation() {
        //设置透明度动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        //设置动画时间,3s
        alphaAnimation.setDuration(3000);
        //设置动画次数，0为一次,1为两次
        alphaAnimation.setRepeatCount(0);
        //设置动画结束后保持结束状态
        ll.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //动画开始时调用
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                //动画结束时调用
                //跳转到主界面
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
                //动画重复时调用
            }
        });
    }
}

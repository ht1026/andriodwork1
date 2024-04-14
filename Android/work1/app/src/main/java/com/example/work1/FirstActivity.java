package com.example.work1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FirstActivity extends AppCompatActivity {
    private RecyclerView rv;
    private MyAdapter myadapter;
    private List<Essay> essayList; // 声明为类成员变量

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);
        setTitle("文章列表");
        rv=findViewById(R.id.list);
        //设置布局管理器
        rv.setLayoutManager(new LinearLayoutManager(this));
        //设置适配器
        Essay essay1=new Essay();
        essay1.setTitle("标题1");
        essay1.setContent("内容1");
        essay1.setTime();
        Essay essay2=new Essay();
        essay2.setTitle("标题2");
        essay2.setContent("内容2");
        essay2.setTime();
        essayList = new ArrayList<>(Arrays.asList(essay1, essay2)); // 初始化类成员变量
        myadapter=new MyAdapter(essayList);
        rv.setAdapter(myadapter);
        //注册上下文菜单
        registerForContextMenu(rv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        getMenuInflater().inflate(R.menu.menu_1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId = item.getItemId();
        if (itemId == R.id.add) {
            Essay newEssay = new Essay();
            newEssay.setTitle("标题3");
            newEssay.setContent("内容3");
            newEssay.setTime();
            essayList.add(newEssay); // 直接对类成员变量进行操作
            myadapter.notifyDataSetChanged(); // 通知适配器数据变化
        } else if (itemId == R.id.delete) {
            myadapter.save();
            Toast.makeText(FirstActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}



package com.example.work1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {
    private EditText it_title;
    private EditText it_content;
    private Button btn1;
    private List<Essay> essayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        it_title = findViewById(R.id.it_title);
        it_content = findViewById(R.id.it_content);
        btn1 = findViewById(R.id.btn_save1);

        // 假设你通过 Intent 接收了来自第一页的日记内容和位置
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        String Title = intent.getStringExtra("Title");
        String Content = intent.getStringExtra("Content");

        // 在相应的 EditText 中显示接收到的日记内容
        it_title.setText(Title);
        it_content.setText(Content);

        essayList = new ArrayList<>(); // 初始化或从其他地方获取日记列表

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = it_title.getText().toString();
                String content = it_content.getText().toString();

                if (!title.isEmpty() && !content.isEmpty()) {
                    Essay updatedEssay = new Essay();
                    updatedEssay.setTitle(title);
                    updatedEssay.setContent(content);
                    updatedEssay.setTime();

                    // 在指定位置覆盖原有的日记内容
                    if (position >= 0 && position < essayList.size()) {
                        essayList.set(position, updatedEssay);
                    }

                    // 创建一个 Intent 并传递更新后的日记数据
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newEssay", updatedEssay);
                    setResult(RESULT_OK, resultIntent);
                    finish(); // 返回第一页并传递数据
                } else {
                    Toast.makeText(ThirdActivity.this, "标题和内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

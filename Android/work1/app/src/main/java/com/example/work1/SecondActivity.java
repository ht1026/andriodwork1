package com.example.work1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private EditText et_title;
    private EditText et_content;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        et_title=findViewById(R.id.et_title);
        et_content=findViewById(R.id.et_content);
        btn=findViewById(R.id.btn_save);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String title = et_title.getText().toString();
                String content = et_content.getText().toString();

                if (!title.isEmpty() && !content.isEmpty()) {
                    Essay newEssay = new Essay();
                    newEssay.setTitle(title);
                    newEssay.setContent(content);
                    newEssay.setTime();

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newEssay", newEssay);
                    setResult(RESULT_OK, resultIntent);
                    finish(); // 返回第一页并传递数据
                } else {
                    Toast.makeText(SecondActivity.this, "标题和内容不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

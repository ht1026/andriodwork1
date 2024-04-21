package com.example.work1;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegiActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    EditText password2;
    Button register;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.username);
        password=findViewById(R.id.pwd);
        password2=findViewById(R.id.pwd2);
        register=findViewById(R.id.register);
        //创建数据库
        SQLiteOpenHelper helper=new LoginSQL(this,"userinfo",null,1);
        db=helper.getWritableDatabase();
        //注册 点击事件
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name = username.getText().toString();
                String pwd = password.getText().toString();
                String pwd2 = password2.getText().toString();
                if (name.equals("") || pwd.equals("") || pwd2.equals("")) {
                    Toast.makeText(RegiActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                } else{
                    Cursor cursor = db.rawQuery("select * from user where username=?", new String[]{name});
                    if (cursor.getCount() != 0) {
                        Toast.makeText(RegiActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                    } else {
                        if (pwd.equals(pwd2)) {
                            db.execSQL("insert into user(username,password) values(?,?)", new String[]{name, pwd});
                            Toast.makeText(RegiActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegiActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegiActivity.this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}

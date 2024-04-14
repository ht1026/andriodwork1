package com.example.work1;
//登录页面
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login,register,forget;
    SQLiteDatabase db;
    private SharedPreferences sp1,sp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        register=findViewById(R.id.register);
        forget=findViewById(R.id.forget);
        //便于下次登录    保存用户名和密码
        sp1 =  this.getSharedPreferences("useinfo",this.MODE_PRIVATE);
        sp2 = this.getSharedPreferences("username",this.MODE_PRIVATE);
        username.setText(sp1.getString("username",null));
        password.setText(sp1.getString("pwd",null));
        //创建数据库
        SQLiteOpenHelper helper=new LoginSQL(this,"userinfo",null,1);
        db=helper.getWritableDatabase();

        //登录 点击事件
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                String pwd=password.getText().toString();
                Cursor cursor=db.rawQuery("select * from user where username=? and password=?",new String[]{name,pwd});
                if(cursor.getCount()==0){
                    //登录失败
                    Toast.makeText(MainActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
            }else {
                    //登录成功
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    //保存用户名和密码
                    SharedPreferences.Editor editor1 = sp1.edit();
                    editor1.putString("username",name);
                    editor1.putString("pwd",pwd);
                    editor1.commit();
                    //跳转到一个页面
                    Intent intent=new Intent(MainActivity.this,FirstActivity.class);
                    startActivity(intent);
                }
                cursor.close();
            }
        });
        //注册 点击事件
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,RegiActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,"注册",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
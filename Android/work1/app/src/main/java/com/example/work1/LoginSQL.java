package com.example.work1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

//用于登录的SQL语句
public class LoginSQL extends SQLiteOpenHelper {
    public LoginSQL(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user(id integer primary key autoincrement,username varchar(20),password varchar(20))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    //插入数据
    public void insertData(SQLiteDatabase db,String username,String password){
        db.execSQL("insert into user(username,password) values(?,?)",new String[]{username,password});
    }
    //删除数据
    public void deleteData(SQLiteDatabase db,String username){
        db.execSQL("delete from user where username=?",new String[]{username});
    }
    //修改数据
    public void updateData(SQLiteDatabase db,String username,String password){
        db.execSQL("update user set password=? where username=?",new String[]{password,username});
    }
    //查询数据,返回是否存在，返回值为true表示存在，false表示不存在
    public boolean queryData(SQLiteDatabase db,String username,String password){
        String sql="select * from user where username=? and password=?";
        String[] args={username,password};
        return db.rawQuery(sql,args).getCount()!=0;
    }
}

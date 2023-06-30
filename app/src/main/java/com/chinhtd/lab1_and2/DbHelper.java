package com.chinhtd.lab1_and2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    Context context;
    public DbHelper(@Nullable Context context) {
        super(context, "ToDoDatabase",null,1);
        this.context = context;
    }
    String table_name = "CREATE TABLE TODO(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,CONTENT TEXT,DATE TEXT,TYPE TEXT,STATUS INTEGER)";

    String data = "INSERT INTO TODO VALUES(1,'Học JAVA','Học Java Cơ bản','27/2/2023','Bình Thường',1),"+
            "(2,'Học REACT NATIVE','Học React native Cơ bản','24/3/2023','Khó',0),"+
            "(3,'Học Kotlin','Học Kotlin Cơ bản','1/4/2023','Dễ',0)";
    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL(table_name);
    db.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS TODO");
            onCreate(db);
        }
    }
}

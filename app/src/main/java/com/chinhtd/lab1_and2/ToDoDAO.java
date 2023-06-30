package com.chinhtd.lab1_and2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class ToDoDAO {
    DbHelper dbHelper;
    Context context;

    public ToDoDAO(DbHelper dbHelper, Context context) {
        this.dbHelper = dbHelper;
        this.context = context;
    }
    public ArrayList<ToDo> getAll() {
        ArrayList<ToDo> list = new ArrayList<>();
        String getAll = "SELECT * FROM TODO";
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(getAll, null);
            if (cursor != null && cursor.getCount() > 0) {
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    list.add(new ToDo(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5)));
                    cursor.moveToNext();
                }
            } else {
                Toast.makeText(context, "Không có giá trị nào", Toast.LENGTH_SHORT).show();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }
        return list;
    }
    public void addToDo(ToDo toDo){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TITLE",toDo.getTiltel());
        values.put("CONTENT",toDo.getContent());
        values.put("DATE",toDo.getDate());
        values.put("TYPE",toDo.getType());
        values.put("STATUS",toDo.getStatus());
        long kq = sqLiteDatabase.insert("TODO",null,values);
        if (kq>0){
            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
        }
    }
    public void deleteToDo(String title){
        ToDo toDo = new ToDo();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        long kq = database.delete("TODO","TITLE = ?",new String[]{title});
    }
    public void updateToDo(ToDo toDo) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("TITLE", toDo.getTiltel());
        values.put("CONTENT", toDo.getContent());
        values.put("DATE", toDo.getDate());
        values.put("TYPE", toDo.getType());
        values.put("STATUS", toDo.getStatus());
        long kq = database.update("TODO", values, "TITLE = ?", new String[]{toDo.getTiltel()});
    }
}

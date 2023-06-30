package com.chinhtd.lab1_and2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ArrayList<ToDo> list;
    ToDoAdapter adapter;

    EditText text_title, text_content, text_date, text_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_title = findViewById(R.id.edt_title);
        text_content = findViewById(R.id.edt_content);
        text_date = findViewById(R.id.edt_date);
        text_type = findViewById(R.id.edt_type);

        DbHelper dbHelper = new DbHelper(this);
        ListView listView = findViewById(R.id.lv);
        ToDoDAO dao = new ToDoDAO(dbHelper, this);
        list = dao.getAll();
        adapter = new ToDoAdapter(this, list);
        listView.setAdapter(adapter);

        Button button_add = findViewById(R.id.btn_add);
        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate() == true) {
                    String title = text_title.getText().toString();
                    String content = text_content.getText().toString();
                    String date = text_date.getText().toString();
                    String type = text_type.getText().toString();
                    ToDo toDo = new ToDo( title, content, date, type, 1 + new Random().nextInt());
                    list.add(toDo);
                    dao.addToDo(toDo);
                    dao.getAll();
                    adapter.notifyDataSetChanged();
                }

            }
        });
        Button button_update = findViewById(R.id.btn_update);
        button_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate() == true) {
                    String title = text_title.getText().toString();
                    String content = text_content.getText().toString();
                    String date = text_date.getText().toString();
                    String type = text_type.getText().toString();
                    ToDo toDo = new ToDo( title, content, date, type, 1 + new Random().nextInt());
                    list.set(ToDoAdapter.Key_Position,toDo);
                    dao.updateToDo(toDo);
                    dao.getAll();
                    adapter.notifyDataSetChanged();

                }
            }
        });
        Button button_delete = findViewById(R.id.btn_delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setMessage("Bạn có chắc chắn muốn xóa không?").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (list.size() == 0) {
                            Toast.makeText(MainActivity.this, "Khong con gi de mat !!", Toast.LENGTH_SHORT).show();
                        }else{
                            list.remove(ToDoAdapter.Key_Position);
                            dao.deleteToDo(text_title.getText().toString());
                            dao.getAll();
                            adapter.notifyDataSetChanged();
                            clearText();
                            Toast.makeText(MainActivity.this, "Xoa Thanh cong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }).setNegativeButton("Cacel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

            }
        });


    }

    private void clearText() {
        text_title.setText("");
        text_content.setText("");
        text_date.setText("");
        text_type.setText("");
    }

    private boolean validate() {
        if (text_title.getText().toString().equals("")) {
            Toast.makeText(this, "Bạn chưa nhập title", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (text_content.getText().toString().equals("")) {
            Toast.makeText(this, "Bạn chưa nhập content", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (text_date.getText().toString().equals("")) {
            Toast.makeText(this, "Bạn chưa nhập date", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
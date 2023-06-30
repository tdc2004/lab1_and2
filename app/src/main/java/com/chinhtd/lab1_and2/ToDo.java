package com.chinhtd.lab1_and2;

import java.io.Serializable;

public class ToDo implements Serializable{
   int id;
   String tiltel,content,date,type;
   int status;

    public ToDo() {
    }

    public ToDo(String tiltel, String content, String date, String type, int status) {
        this.tiltel = tiltel;
        this.content = content;
        this.date = date;
        this.type = type;
        this.status = status;
    }

    public ToDo(int id, String tiltel, String content, String date, String type, int status) {
        this.id = id;
        this.tiltel = tiltel;
        this.content = content;
        this.date = date;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTiltel() {
        return tiltel;
    }

    public void setTiltel(String tiltel) {
        this.tiltel = tiltel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

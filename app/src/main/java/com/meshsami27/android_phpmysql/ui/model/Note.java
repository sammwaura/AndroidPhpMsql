package com.meshsami27.android_phpmysql.ui.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {

int notes_id; String title; String note; int color;
    public Note(int notes_id, String title, String note, int color) {
        this.notes_id=notes_id;
        this.title=title;
        this.note=note;
        this.color=color;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getId() {
        return notes_id;
    }
    public void id(int id){ this.notes_id = id;}
}
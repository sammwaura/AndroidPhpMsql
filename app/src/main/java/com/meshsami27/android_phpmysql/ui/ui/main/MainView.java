package com.meshsami27.android_phpmysql.ui.ui.main;

import com.meshsami27.android_phpmysql.ui.model.Note;

import java.util.List;

public interface MainView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Note> notes);
    void onErrorLoading(String message);
}

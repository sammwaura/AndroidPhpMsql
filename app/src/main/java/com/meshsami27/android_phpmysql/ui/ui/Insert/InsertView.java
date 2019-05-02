package com.meshsami27.android_phpmysql.ui.ui.Insert;

public interface InsertView {

    void showProgress();
    void hideProgress();
    void onAddSuccess(String message);
    void onAddError(String message);
}

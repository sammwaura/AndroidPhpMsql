package com.meshsami27.android_phpmysql.ui.ui.Insert;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.meshsami27.android_phpmysql.ui.api.ApiClient;
import com.meshsami27.android_phpmysql.ui.api.ApiInterface;
import com.meshsami27.android_phpmysql.ui.model.Note;

import retrofit2.Callback;
import retrofit2.Response;

public class InsertPresenter {

    private InsertView view;

    public InsertPresenter(InsertView view){
        this.view = view;
    }
    void saveNote(final String title, final String note, final int color) {



        /*view.showProgress();

        ApiInterface  apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        retrofit2.Call<Note> call = apiInterface.saveNote(title,note,color);

        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<Note> call, Response<Note> response) {
                view.hideProgress();

                if (response.isSuccessful() && response.body() != null){

                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onAddSuccess(response.body().getMessage());

                    } else {
                        view.onAddError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<Note> call, @NonNull Throwable t) {
                view.hideProgress();
                view.onAddError(t.getLocalizedMessage());
            }
        });*/

    }
}


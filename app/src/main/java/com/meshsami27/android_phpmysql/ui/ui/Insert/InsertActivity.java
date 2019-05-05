package com.meshsami27.android_phpmysql.ui.ui.Insert;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.meshsami27.android_phpmysql.R;
import com.meshsami27.android_phpmysql.ui.api.ApiClient;
import com.meshsami27.android_phpmysql.ui.api.ApiInterface;
import com.meshsami27.android_phpmysql.ui.model.Note;
import com.thebluealliance.spectrum.SpectrumDialog;
import com.thebluealliance.spectrum.SpectrumPalette;

import retrofit2.Callback;
import retrofit2.Response;

public class InsertActivity extends AppCompatActivity implements InsertView {

    EditText et_title, et_note;
    ProgressDialog progressDialog;
    SpectrumPalette palette;

    InsertPresenter presenter;

    ApiInterface apiInterface;

    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        et_title = findViewById(R.id.title);
        et_note = findViewById(R.id.note);
        palette = findViewById(R.id.palette);


//        palette.setOnColorSelectedListener(new SpectrumPalette.OnColorSelectedListener() {
//            @Override
//            public void onColorSelected(int color) {
//
//            }
//
//            abstract class onColorSelectedListener {
//                public abstract void onColorSelected(int selectedColor);
//            }
//        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");


        presenter = new InsertPresenter(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_insert, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                // Save

                String title = et_title.getText().toString().trim();
                String note = et_note.getText().toString().trim();
                int color = -2184710;

                if (title.isEmpty()){
                    et_title.setError("Please enter a title");
                } else if (note.isEmpty()){
                    et_note.setError("Please enter a note");
                } else {
                    presenter.saveNote(title, note, color);
                }
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onAddSuccess(String message) {
        Toast.makeText(InsertActivity.this, message, Toast.LENGTH_SHORT).show();
        finish(); //back to main activity
    }

    @Override
    public void onAddError(String message) {
        Toast.makeText(InsertActivity.this, message, Toast.LENGTH_SHORT).show();
        finish(); //back to main activity
    }
}

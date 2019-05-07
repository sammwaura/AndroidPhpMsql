package com.meshsami27.android_phpmysql.ui.ui.Insert;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.meshsami27.android_phpmysql.R;
import com.meshsami27.android_phpmysql.ui.api.ApiInterface;
import com.meshsami27.android_phpmysql.ui.model.Note;
import com.thebluealliance.spectrum.SpectrumPalette;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class InsertActivity extends AppCompatActivity implements InsertView {

    EditText et_title, et_note;
    ProgressDialog progressDialog;
    SpectrumPalette palette;

    InsertPresenter presenter;

    ApiInterface apiInterface;

    private int mColor;
    private int mCurrentValue;
    private int mDialogColor;
    private boolean mCloseOnSelected;
    private Context context;
    private VolleyResponseListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        et_title = findViewById(R.id.title);
        et_note = findViewById(R.id.note);
        palette = findViewById(R.id.palette);


        palette.setOnColorSelectedListener(new SpectrumPalette.OnColorSelectedListener() {

            @Override
            public void onColorSelected(int color) {
                mDialogColor = color;
                if (mCloseOnSelected) {
                    palette.setSelectedColor(color);
                }
            }
        });
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

                final String title = et_title.getText().toString().trim();
                final String note = et_note.getText().toString().trim();
                final int color = -2184710;

                if (title.isEmpty()) {
                    et_title.setError("Please enter a title");
                } else if (note.isEmpty()) {
                    et_note.setError("Please enter a note");
                } else {
                    //presenter.saveNote(title, note, color);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "", new Response.Listener <String>() {
                        public Set <Note> noter;

                        @Override
                        public void onResponse(String s) {
                            System.out.println("sssssssssssssssssssssss" + s);
                            if (s != null) {
                                JSONArray array = null;
                                try {
                                    JSONObject jsonObject = new JSONObject(s);

                                    JSONArray posts = jsonObject.getJSONArray("notes");
                                    noter.clear();
                                    if (posts.length() > 0) {
                                        for (int i = 0; i < posts.length(); i++) {
                                            JSONObject row = posts.getJSONObject(i);

                                            String note_id = row.getString("note_id");
                                            String title = row.getString("title");
                                            String note = row.getString("note");
                                            int color = row.getInt("color");
                                            noter.add(new Note(note_id, title, note, color));
                                        }

                                        initializeData();

                                        findViewById(R.id.title).setVisibility(View.VISIBLE);
                                        findViewById(R.id.note).setVisibility(View.VISIBLE);
                                        findViewById(R.id.palette).setVisibility(View.VISIBLE);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                            System.out.println("MAIN response " + s);
                        }

                        private void initializeData() {
                           noter.add(new Note("notes_id", "title", "note", "color"));

                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    System.out.println("volleyError error" + error.getMessage());
                                    Toast.makeText(getApplicationContext(), "Poor network connection.", Toast.LENGTH_LONG).show();
                                }


                            }) {
                        @Override
                        protected Map <String, String> getParams() throws AuthFailureError {
                            Map <String, String> params = new Hashtable <>();
                            params.put("title", title.toString());
                            params.put("note", note.toString());
                            params.put("color", color(toString().));
                            System.out.println();
                            return super.getParams();
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(InsertActivity.this);
                    requestQueue.add(stringRequest);

                }
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
        System.out.println(message+"###################");
        Toast.makeText(InsertActivity.this, "error", Toast.LENGTH_SHORT).show();
        finish(); //back to main activity
    }


}

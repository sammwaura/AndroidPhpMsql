package com.meshsami27.android_phpmysql.ui.ui.main;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.meshsami27.android_phpmysql.R;
import com.meshsami27.android_phpmysql.ui.model.Note;
import com.meshsami27.android_phpmysql.ui.ui.Insert.InsertActivity;
import com.meshsami27.android_phpmysql.ui.ui.Update.UpdateActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefresh;

    RecyclerView.Adapter adapter;
    AdapterView.OnItemClickListener itemClickListener;
    ArrayList <Note> noter;
    private int INTENT_ADD = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noter = new ArrayList <>();

//      swipeRefresh = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(MainActivity.this, noter);
        recyclerView.setAdapter(adapter);


//        retrieveData();
//        retrieveUpdatedData();

        floatingActionButton = findViewById(R.id.add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.add:
                        Intent intent = new Intent(getApplicationContext(), InsertActivity.class);
                        startActivityForResult(intent, INTENT_ADD);
                }
            }
        });
    }



    private void retrieveData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Retrieving data....");
        progressDialog.show();


        System.out.println("!!!!!!saving!!!!!!");
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://my-noter.000webhostapp.com/notes.php",
                new Response.Listener <String>() {

                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("notes");

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject row = array.getJSONObject(i);
                                Note note = new Note(
                                        row.getInt("note_id"),
                                        row.getString("title"),
                                        row.getString("note"),
                                        row.getInt("color")
                                );
                                noter.add(note);

                            }

                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("volleyError error" + error.getMessage());
                        Toast.makeText(getApplicationContext(), "Poor network connection.", Toast.LENGTH_LONG).show();
                    }


                }) {
        };

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);


    }


    private void retrieveUpdatedData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Retrieving Updateddata....");
        progressDialog.show();


        System.out.println("!!!!!!!!updating!!!!!!!");
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                "http://my-noter.000webhostapp.com/update.php",
               new Response.Listener <String>() {

                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray array = jsonObject.getJSONArray("notes");

                                if (success.equals("1")){

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject row = array.getJSONObject(i);

                                Note note = new Note(
                                        row.getInt("note_id"),
                                        row.getString("title"),
                                        row.getString("note"),
                                        row.getInt("color")
                                );
                                noter.add(note);
                            }
                                }

                            adapter = new MainAdapter(getApplicationContext(), noter);
                            adapter.notifyDataSetChanged();
                            recyclerView.setAdapter(adapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("volleyError error" + error.getMessage());
                        Toast.makeText(getApplicationContext(), "Poor network connection.", Toast.LENGTH_LONG).show();
                    }
                });


        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
       requestQueue.add(stringRequest);
    }


    @Override
    public void onResume() {
        super.onResume();
        // put your code here...
        retrieveData();
//        retrieveUpdatedData();
    }

}
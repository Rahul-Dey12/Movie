package com.example.kiit.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.android.volley.Response.*;

public class MainActivity extends AppCompatActivity {
    StringRequest stringRequest;
    RequestQueue queue;
    ArrayList<Movies> movielist;
    MovieAdapter movieAdapter;
    ListView lv;
    ProgressDialog p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConnectivityManager cm = (ConnectivityManager) MainActivity.this.getSystemService(MainActivity.this.CONNECTIVITY_SERVICE);
        movielist = new ArrayList<Movies>();
        lv = (ListView) findViewById(R.id.list);
        p = new ProgressDialog(this);
        p.setMessage("Loading...");
        p.show();
        NetworkInfo an = cm.getActiveNetworkInfo();
        String reqUrl = "https://waking-railroads.000webhostapp.com/elabs/one.php";
        queue = Volley.newRequestQueue(this);
        movieAdapter = new MovieAdapter(MainActivity.this, R.layout.movielist, movielist);
        lv.setAdapter(movieAdapter);
        if (an == null || !an.isConnectedOrConnecting()) {
            Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, "Try Again!", Toast.LENGTH_SHORT).show();
            if(p.isShowing())
                p.dismiss();
            finish();
        }
         else {
            stringRequest = new StringRequest(Request.Method.GET, reqUrl,  new Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        // Toast.makeText(MainActivity.this,response, Toast.LENGTH_SHORT).show();
                        JSONArray jsonArray = new JSONArray(response);
                        for (int index = 0; index < jsonArray.length(); index++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(index);
                            String name = jsonObject.getString("name");
                            String date = jsonObject.getString("date");
                            String rating = jsonObject.getString("rating");
                            String murl = jsonObject.getString("poster");
                            Movies movie = new Movies(name, date, rating, murl);
                            movielist.add(movie);

                        }
                        movieAdapter.notifyDataSetChanged();
                        if (p.isShowing())
                            p.dismiss();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT);
                }
            });

            queue.add(stringRequest);
        }

    }
}

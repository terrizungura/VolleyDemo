package com.example.volleydemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    Button btnShowText;
    TextView txtServerText;

    String server_url = "http://192.168.8.101/test.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowText = findViewById(R.id.btnShowText);
        txtServerText = findViewById(R.id.txtServerText);

        btnShowText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url,

                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                txtServerText.setText(response);
                                requestQueue.stop();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        txtServerText.setText("something went wrong");
                        error.printStackTrace();
                        requestQueue.stop();
                    }
                });
                requestQueue.add(stringRequest);
            }
        });
    }
}

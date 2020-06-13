package com.example.githubapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText editText_username;
Button button_submit;
TextView textView_name,textView_githuburl,textView_followers,textView_following,textView_repos,textView_joinedat,textView_bio;
ImageView imageView_profilepic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_username=findViewById(R.id.edittext_github_userid);
        button_submit=findViewById(R.id.btn_submit);
        textView_bio=findViewById(R.id.user_bio);
        textView_followers=findViewById(R.id.user_followers);
        textView_following=findViewById(R.id.user_following);
        textView_githuburl=findViewById(R.id.github_link);
        textView_joinedat=findViewById(R.id.user_joinedat);
        textView_name=findViewById(R.id.user_name);
        textView_repos=findViewById(R.id.user_repos);
        imageView_profilepic=findViewById(R.id.user_profilepic);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid=editText_username.getText().toString();
                getUserdata(userid);
            }
        });
    }
    public void getUserdata(String githubuserid){
String url="https://api.github.com/users/"+githubuserid;
        RequestQueue requestQueue= Volley.newRequestQueue(this);
StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
    @Override
    public void onResponse(String response) {
        Toast.makeText(MainActivity.this, ""+response, Toast.LENGTH_SHORT).show();
    }
}, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {

    }
}){
    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
         super.getHeaders();
         Map<String, String> header = new HashMap<>();
         header.put("Content-Type","application/json");
         return header;
    }
};
        requestQueue.add(stringRequest);


    }
}

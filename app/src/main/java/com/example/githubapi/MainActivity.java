package com.example.githubapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText editText_username;
Button button_submit;
TextView textView_name,textView_githuburl,textView_followers,textView_following,textView_repos,textView_joinedat,textView_bio;
ImageView imageView_profilepic;
LinearLayout linearLayout_name,linearLayout_githublink,linearLayout_followers,linearLayout_following,linearLayout_repos,linearLayout_joinedat,linearLayout_bio;

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
        linearLayout_bio=findViewById(R.id.layout_bio);
        linearLayout_followers=findViewById(R.id.layout_followers);
        linearLayout_following=findViewById(R.id.layout_following);
        linearLayout_githublink=findViewById(R.id.layout_githublink);
        linearLayout_joinedat=findViewById(R.id.layout_joinedat);
        linearLayout_name=findViewById(R.id.layout_name);
        linearLayout_repos=findViewById(R.id.layout_repositories);
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userid=editText_username.getText().toString().trim();
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
        try {
            JSONObject jsonObject_response = new JSONObject(response);
            String name=jsonObject_response.getString("name");
            textView_name.setText(name);
            String image_url=jsonObject_response.getString("avatar_url");
            Glide.with( MainActivity.this)
                    .load(image_url)
                    .apply(RequestOptions.circleCropTransform())
                    .into(imageView_profilepic);
            String followers=jsonObject_response.getString("followers");
            textView_followers.setText(followers);
            String following=jsonObject_response.getString("following");
            textView_following.setText(following);
            String bio=jsonObject_response.getString("bio");
            textView_bio.setText(bio);
            String githuburl=jsonObject_response.getString("html_url");
            textView_githuburl.setText(githuburl);
            String joinedat=jsonObject_response.getString("created_at");
            textView_joinedat.setText(joinedat);
            String repos=jsonObject_response.getString("public_repos");
            textView_repos.setText(repos);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        imageView_profilepic.setVisibility(View.VISIBLE);
        linearLayout_bio.setVisibility(View.VISIBLE);
        linearLayout_followers.setVisibility(View.VISIBLE);
        linearLayout_following.setVisibility(View.VISIBLE);
        linearLayout_githublink.setVisibility(View.VISIBLE);
        linearLayout_joinedat.setVisibility(View.VISIBLE);
        linearLayout_name.setVisibility(View.VISIBLE);
        linearLayout_repos.setVisibility(View.VISIBLE);
    }
}, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(MainActivity.this, ""+error, Toast.LENGTH_SHORT).show();
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

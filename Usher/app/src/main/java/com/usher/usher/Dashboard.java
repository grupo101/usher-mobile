package com.usher.usher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;



import java.util.List;
import retrofit2.Call;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {


    TextView tvUser,tvName,tvSurname,tvPassword;
    //ListView list;
    //ArrayList<String> titles = new ArrayList<>();
    Button btn_sesion, btn_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tvName = findViewById(R.id.tvNameDs);
        btn_sesion = findViewById(R.id.btn_sess);
        btn_out = findViewById(R.id.btn_out);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        String name_show = name + " " + surname;
        tvName.setText(name_show);


        /*inal ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,titles);
        list = findViewById(R.id.list);

        list.setAdapter(arrayAdapter);
        getPosts(arrayAdapter);*/

        btn_sesion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent openSession = new Intent(Dashboard.this, OpenSession.class);
                Dashboard.this.startActivity(openSession);
            }
        });

        btn_out.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });


    }


    /*private void getPosts(final ArrayAdapter arrayAdapter) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostService postService = retrofit.create(PostService.class);
        Call< List<PostObject> > call = postService.getPost();

        call.enqueue(new Callback<List<PostObject>>() {
            @Override
            public void onResponse(Call<List<PostObject>> call, Response<List<PostObject>> response) {
                for(PostObject post : response.body()) {
                    titles.add(post.getTitle());
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PostObject>> call, Throwable t) {
            }
        });
    }*/

}

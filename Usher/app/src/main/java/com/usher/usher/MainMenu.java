package com.usher.usher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {


    TextView tvUser,tvName,tvSurname,tvPassword;
    Button btn_sesion, btn_out, btn_hist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tvName = findViewById(R.id.tvNameDs);
        btn_sesion = findViewById(R.id.btn_sess);
        btn_out = findViewById(R.id.btn_out);
        btn_hist = findViewById(R.id.btn_historial);
        final Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        String name_show = "Bienvenido: " + name + " " + surname;
        tvName.setText(name_show);

        btn_sesion.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent openSession = new Intent(MainMenu.this, OpenSession.class);
                MainMenu.this.startActivity(openSession);
            }
        });

        btn_hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, SessionStatistics.class);
                intent.putExtra("method", "bars");
                startActivity(intent);
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

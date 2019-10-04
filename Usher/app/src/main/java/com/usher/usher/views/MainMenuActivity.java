package com.usher.usher.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.usher.usher.OpenSession;
import com.usher.usher.R;
import com.usher.usher.SessionStatistics;
import com.usher.usher.interfaces.MainMenuActivityPresenter;
import com.usher.usher.interfaces.MainMenuActivityView;
import com.usher.usher.presenters.LoginActivityPresenterImpl;
import com.usher.usher.presenters.MainMenuActivityPresenterImpl;

public class MainMenuActivity extends AppCompatActivity implements MainMenuActivityView {


    private TextView tvUser, tvName, tvSurname, tvPassword;
    private Button btn_sesion, btn_out, btn_hist;
    private ProgressBar pr_progressMainMenu;
    private MainMenuActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        tvName = findViewById(R.id.tvNameDs);
        btn_sesion = findViewById(R.id.btn_sess);
        btn_out = findViewById(R.id.btn_out);
        btn_hist = findViewById(R.id.btn_historial);
        pr_progressMainMenu = findViewById(R.id.progressLogin);

        //Pasamos metodos del View al Presenter
        presenter = new MainMenuActivityPresenterImpl(this);

        final Intent intent = getIntent();
        final String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        String name_show = "Bienvenido: " + name + " " + surname;
        tvName.setText(name_show);

        btn_sesion.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                presenter.checkSesion();
            }
        });

        btn_hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, SessionStatistics.class);
                intent.putExtra("method", "bars");
                startActivity(intent);
            }
        });

        btn_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }

    @Override
    public void showProgress(boolean option) {
        pr_progressMainMenu.setVisibility(option ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onSesion(boolean status) {
        Intent openSession = new Intent(MainMenuActivity.this, OpenSession.class);
        MainMenuActivity.this.startActivity(openSession);
    }

    @Override
    public void offSesion(boolean status) {
        Toast.makeText( getApplicationContext(), "Sesión innactiva. Intente en otro momento", Toast.LENGTH_LONG).show();
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

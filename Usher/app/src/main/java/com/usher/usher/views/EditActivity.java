package com.usher.usher.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;
import com.usher.usher.R;
import com.usher.usher.interfaces.EditActivityPresenter;
import com.usher.usher.interfaces.EditActivityView;
import com.usher.usher.presenters.EditActivityPresenterImpl;

public class EditActivity extends AppCompatActivity implements EditActivityView {

    private EditActivityPresenter presenter;

    private TextView tv_user;
    private EditText et_nameEdit, et_surnameEdit, et_newPasswordEdit;
    private String password;
    private Button btn_editUser;
    private ProgressBar pr_progresBarUpdate;
    String welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        tv_user = findViewById(R.id.tvUser);
        et_nameEdit = findViewById(R.id.etNameEdit);
        et_surnameEdit = findViewById(R.id.etSurnameEdit);
        et_newPasswordEdit = findViewById(R.id.etPassEdit);
        btn_editUser = findViewById(R.id.btnEditUser);
        pr_progresBarUpdate = findViewById(R.id.progressEdit);
        //welcome = ((MainMenuActivity)context).findViewById(R.id.tvName);

        final Intent intent = getIntent();
        et_nameEdit.setText(intent.getStringExtra("name"));
        tv_user.setText(intent.getStringExtra("username"));
        et_surnameEdit.setText(intent.getStringExtra("surname"));
        password = intent.getStringExtra("password");
        et_newPasswordEdit.setText(intent.getStringExtra("password"));

        presenter = new EditActivityPresenterImpl(this);

        btn_editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.doUpdate(et_nameEdit.getText().toString(), et_surnameEdit.getText().toString(), tv_user.getText().toString(), password, et_newPasswordEdit.getText().toString());
            }
        });
    }


    @Override
    public void showProgress(boolean option) {
        pr_progresBarUpdate.setVisibility(option ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onUpdateSuccessfullView(String name, String surname, String username, String password) {
        Toast.makeText( getApplicationContext(), "Perfil actualizado", Toast.LENGTH_LONG).show();
        Intent resultIntent = new Intent();
        resultIntent.putExtra("name", name);
        resultIntent.putExtra("surname", surname);
        resultIntent.putExtra("password", password);
        setResult(EditActivity.RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void showUpdateFailed(String error) {
        Toast.makeText( getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showError() {
        Toast.makeText( getApplicationContext(), "Error en la conexion", Toast.LENGTH_LONG).show();
    }
}

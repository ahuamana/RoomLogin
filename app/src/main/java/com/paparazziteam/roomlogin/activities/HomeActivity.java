package com.paparazziteam.roomlogin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.paparazziteam.roomlogin.R;
import com.paparazziteam.roomlogin.appdatabase.AppDatabase;
import com.paparazziteam.roomlogin.interfaces.UserDao;

public class HomeActivity extends AppCompatActivity {

    TextView txtmatricula;
    String mExtraMatricula;

    Button btnupdate;
    EditText newpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mExtraMatricula = getIntent().getStringExtra("matricula");

        txtmatricula = findViewById(R.id.txtmatricula);
        newpassword = findViewById(R.id.newpassword);
        btnupdate = findViewById(R.id.btnActualizar);

        txtmatricula.setText(mExtraMatricula);

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pass = newpassword.getText().toString();

                if(pass.isEmpty())
                {
                    Toast.makeText(HomeActivity.this, "Completa los campos", Toast.LENGTH_SHORT).show();

                }else
                {
                    //Perform Query
                    AppDatabase appDatabase = AppDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = appDatabase.userDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            //run query
                            userDao.updatePassword(pass,mExtraMatricula);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(HomeActivity.this, "Contraseña Actualizado", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }).start();

                }

            }
        });

    }
}
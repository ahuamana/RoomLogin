package com.paparazziteam.roomlogin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paparazziteam.roomlogin.R;
import com.paparazziteam.roomlogin.appdatabase.AppDatabase;
import com.paparazziteam.roomlogin.interfaces.UserDao;
import com.paparazziteam.roomlogin.models.User;

public class LoginActivity extends AppCompatActivity {

    Button btningresar, btnregistrar;
    EditText matricula, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        matricula = findViewById(R.id.Lmatricula);
        password = findViewById(R.id.Lpassword);
        btningresar = findViewById(R.id.Lbtningresar);
        btnregistrar = findViewById(R.id.Lbtnregister);


        btningresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //do everything

                String matriculaa = matricula.getText().toString();
                String passwordd = password.getText().toString();

                if(matriculaa.isEmpty()||passwordd.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Completa los campos", Toast.LENGTH_SHORT).show();
                }else
                {
                    //Perform Query
                    AppDatabase appDatabase = AppDatabase.getUserDatabase(getApplicationContext());
                    UserDao userDao = appDatabase.userDao();

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            User user = userDao.login(matriculaa,passwordd);

                            if(user == null)
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            } else
                            {
                                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                                i.putExtra("matricula",user.getMatricula());

                                startActivity(i);
                            }

                        }
                    }).start();
                }

            }
        });


        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

    }
}
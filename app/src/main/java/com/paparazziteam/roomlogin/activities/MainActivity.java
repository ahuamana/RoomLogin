package com.paparazziteam.roomlogin.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.paparazziteam.roomlogin.R;
import com.paparazziteam.roomlogin.appdatabase.AppDatabase;
import com.paparazziteam.roomlogin.interfaces.UserDao;
import com.paparazziteam.roomlogin.models.User;

public class MainActivity extends AppCompatActivity {

    EditText matricula, password, name;
    Button register;

    User mUser;
    AppDatabase mAppDatabase;
    UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matricula = findViewById(R.id.matricula);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        mUser= new User();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUser.setMatricula(matricula.getText().toString());
                mUser.setPassword(password.getText().toString());

                if(validateInput(mUser))
                {
                    //Do inset operation
                    mAppDatabase = AppDatabase.getUserDatabase(getApplicationContext()); // instancia a la dabase de datos
                    mUserDao = mAppDatabase.userDao(); // crea la lista de consultar a utilizar

                    //start insert operation on thread, asyncronis task

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            User user = mUserDao.consultarMatricula(mUser.getMatricula());

                            //validar usuario registrado
                            if(user == null)
                            {
                                //Registrar usuario
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {

                                        //call register method
                                        mUserDao.registerUser(mUser);

                                        //start UIThead to show toast
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {

                                                Toast.makeText(MainActivity.this, "Usuario Registrado!", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                                    }
                                }).start();
                                //fin de registrar usuario
                            }
                            else
                            {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "No puedes registrarte otra vez", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            //fin de validar usuario registrado

                        }
                    }).start();



                }
                else
                {
                    Toast.makeText(MainActivity.this, "Rellenar todos los campos", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private Boolean validateInput(User user)
    {
        if(user.matricula.isEmpty() || user.password.isEmpty())
        {
            return false;
        }else {
            return true;
        }


    }
}
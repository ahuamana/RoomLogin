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

    EditText userId, password, name;
    Button register;

    User mUser;
    AppDatabase mAppDatabase;
    UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId = findViewById(R.id.userid);
        password = findViewById(R.id.password);
        name = findViewById(R.id.name);
        register = findViewById(R.id.register);

        mUser= new User();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mUser.setUserId(userId.getText().toString());
                mUser.setName(name.getText().toString());
                mUser.setPassword(password.getText().toString());

                if(validateInput(mUser))
                {
                    //Do inset operation
                    mAppDatabase = AppDatabase.getUserDatabase(getApplicationContext());
                    mUserDao = mAppDatabase.userDao();

                    //start insert operation on thread, asyncronis task

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
        if(user.getName().isEmpty() || user.getUserId().isEmpty() || user.getPassword().isEmpty() )
        {
            return false;
        }else {
            return true;
        }


    }
}
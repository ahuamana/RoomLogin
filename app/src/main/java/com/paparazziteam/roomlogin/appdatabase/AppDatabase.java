package com.paparazziteam.roomlogin.appdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.paparazziteam.roomlogin.interfaces.UserDao;
import com.paparazziteam.roomlogin.models.User;

@Database(entities = {User.class},version = 1)
public abstract class AppDatabase  extends RoomDatabase {

    private static final String dbName = "user";
    private static AppDatabase userDatabase;


    public static synchronized AppDatabase getUserDatabase(Context context)
    {
        if(userDatabase ==null)
        {
            userDatabase = Room.databaseBuilder(context, AppDatabase.class, dbName)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return userDatabase;
    }

    public abstract UserDao userDao();

}

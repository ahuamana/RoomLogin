package com.paparazziteam.roomlogin.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.paparazziteam.roomlogin.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);

    @Delete
    void delete(User user);

    @Query("Select * from users where matricula = (:Matricula) and password =(:Password)")
    User login(String Matricula, String Password);
}

package com.paparazziteam.roomlogin.interfaces;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.paparazziteam.roomlogin.models.User;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);

    @Delete
    void delete(User user);

    @Query("Select * from users where matricula = (:Matricula) and password =(:Password)")
    User login(String Matricula, String Password);

    @Query("Select * from users where matricula =(:Matricula)")
    User consultarMatricula(String Matricula);

    @Query("Update users SET password = (:NewPassword) where matricula = (:Matricula) ")
    void updatePassword(String NewPassword, String Matricula);

}

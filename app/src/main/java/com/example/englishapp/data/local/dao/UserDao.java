package com.example.englishapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.englishapp.data.entity.UserEntity;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(UserEntity userEntity);
    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    Single<UserEntity> getUser(String username, String password);
}

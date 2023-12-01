package com.example.englishapp.data.repository.impl;

import com.example.englishapp.data.entity.UserEntity;
import com.example.englishapp.data.local.dao.UserDao;
import com.example.englishapp.data.model.UserModel;
import com.example.englishapp.data.remote.UserService;
import com.example.englishapp.data.repository.UserRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class UserRepositoryImpl implements UserRepository {
    private final UserDao userDao;
    private final UserService userService;

    public UserRepositoryImpl(UserDao userDao, UserService userService) {
        this.userDao = userDao;
        this.userService = userService;
    }

    @Override
    public void insertUser(UserEntity userEntity) {
        // Perform the insertion operation on a background thread
        Completable.fromAction(() -> userDao.insertUser(userEntity))
                .subscribeOn(Schedulers.io())
                .subscribe();
    }
    @Override
    public Observable<UserModel> getUser(String email, String pass) {
        return userService.login(email, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
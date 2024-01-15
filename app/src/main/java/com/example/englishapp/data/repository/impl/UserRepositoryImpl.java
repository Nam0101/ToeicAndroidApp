package com.example.englishapp.data.repository.impl;

import com.example.englishapp.data.entity.UserEntity;
import com.example.englishapp.data.local.dao.UserDao;
import com.example.englishapp.data.model.ExamDateResponse;
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

    @Override
    public Observable<UserModel> register(String username, String password, String email, String mobile) {
        return userService.register(email, password, username, mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UserModel> forgotPassword(String email, String mobile) {
        return userService.forgotPassword(email, mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ExamDateResponse> updateExamDate(int user_id, String exam_date) {
        return userService.updateExamDate(user_id, exam_date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ExamDateResponse> getExamDate(int user_id) {
        return userService.getExamDate(user_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
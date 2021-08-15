package com.chilitech.data.repository.datasource.remote;

import com.chilitech.data.net.entity.TokenEntity;
import com.chilitech.data.net.entity.UserEntity;
import com.chilitech.data.net.impl.UserDaoImpl;
import com.chilitech.data.repository.datasource.UserDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;


public class UserRemoteDataSource implements UserDataSource.Remote {

    private UserDaoImpl userDao = new UserDaoImpl();

    private static UserRemoteDataSource INSTANCE;

    @Inject
    public UserRemoteDataSource() {
    }

    public static UserRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserRemoteDataSource();
        }
        return INSTANCE;
    }

    @Override
    public Flowable<TokenEntity> login(String username, String password) {
        return userDao.login(username, password);
    }

    @Override
    public Flowable<UserEntity> getInfo(String accessToken, String userId) {
        return userDao.getInfo(accessToken, userId);
    }

    @Override
    public Flowable<List<UserEntity>> getFriendList(String accessToken, String userId, int skip, int limit) {
        return userDao.getFriendList(accessToken, userId, skip, limit);
    }
}

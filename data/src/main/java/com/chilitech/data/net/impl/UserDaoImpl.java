package com.chilitech.data.net.impl;

import com.chilitech.data.net.dao.UserDao;
import com.chilitech.data.net.entity.TokenEntity;
import com.chilitech.data.net.entity.UserEntity;
import com.chilitech.data.net.manager.OkHttpManager;
import com.chilitech.data.repository.datasource.UserDataSource;

import java.util.List;

import io.reactivex.Flowable;


public class UserDaoImpl implements UserDataSource.Remote {

    private UserDao userDao = OkHttpManager.getInstance().create(UserDao.BASE_URL, UserDao.class);

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

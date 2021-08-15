package com.chilitech.data.repository;

import com.chilitech.data.repository.datasource.local.UserLocalDataSource;
import com.chilitech.data.repository.datasource.remote.UserRemoteDataSource;


public class Injection {

    public static UserRepository provideUserRepository() {
        return UserRepository.getInstance(UserLocalDataSource.getInstance(), UserRemoteDataSource.getInstance());
    }
}

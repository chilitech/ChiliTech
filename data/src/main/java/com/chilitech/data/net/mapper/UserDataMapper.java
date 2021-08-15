package com.chilitech.data.net.mapper;

import com.chilitech.data.cache.database.model.UserModel;
import com.chilitech.data.net.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;


public class UserDataMapper {

    public UserDataMapper() {
    }

    public UserModel transform(UserEntity entity) {
        UserModel model = new UserModel();
        model.setId(entity.getId());
        model.setUserName(entity.getUsername());
        model.setUserNick(entity.getUserNick());
        model.setCreateTime(System.currentTimeMillis());
        return model;
    }

    public List<UserModel> transform(List<UserEntity> entityList) {
        List<UserModel> modelList = new ArrayList<>();
        for (UserEntity entity : entityList) {
            modelList.add(transform(entity));
        }
        return modelList;
    }
}

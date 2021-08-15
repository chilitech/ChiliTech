package com.chilitech.data.net.mapper;

import com.chilitech.data.cache.database.model.TokenModel;
import com.chilitech.data.net.entity.TokenEntity;


public class TokenDataMapper {

    public TokenDataMapper() {
    }

    public TokenModel transform(TokenEntity tokenEntity) {
        TokenModel tokenModel=new TokenModel();
        tokenModel.setRefreshToken(tokenEntity.getRefreshToken());
        tokenModel.setAccessToken(tokenEntity.getAccessToken());
        tokenModel.setExpiresIn(tokenEntity.getExpiresIn());
        return tokenModel;
    }
}

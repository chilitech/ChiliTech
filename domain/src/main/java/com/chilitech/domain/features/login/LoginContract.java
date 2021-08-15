package com.chilitech.domain.features.login;

import com.chilitech.data.cache.database.model.TokenModel;
import com.chilitech.domain.base.BasePresenter;



public class LoginContract {

    public interface View {

        void loginSucceed(TokenModel tokenModel);

        void loginError();
    }

    public interface Presenter extends BasePresenter<View> {

        void login(String username, String password);
    }
}

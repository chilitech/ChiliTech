package com.chilitech.domain.features.login;

import com.chilitech.domain.base.BaseUseCase;
import com.chilitech.domain.usecase.LoginRemoteTask;

import javax.inject.Inject;


public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View view;

    @Inject
    LoginRemoteTask task;

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void setView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.view = null;
        if (task != null) {
            task.cancel();
        }
    }

    @Override
    public void login(String username, String password) {
        task.setRequestValues(new LoginRemoteTask.RequestValues(username, password));
        task.setUseCaseCallback(new BaseUseCase.UseCaseCallback<LoginRemoteTask.ResponseValues>() {
            @Override
            public void onSuccess(LoginRemoteTask.ResponseValues response) {
                if (view == null) return;
                view.loginSucceed(response.getTokenModel());
            }

            @Override
            public void onError() {
                if (view == null) return;
                view.loginError();
            }
        });
        task.run();
    }
}

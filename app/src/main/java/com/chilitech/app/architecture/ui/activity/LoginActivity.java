package com.chilitech.app.architecture.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.chilitech.app.architecture.R;
import com.chilitech.app.architecture.databinding.ActivityLoginBinding;
import com.chilitech.app.architecture.di.DaggerBaseActivity;
import com.chilitech.base.utils.ToolBarUtil;
import com.chilitech.data.cache.database.model.TokenModel;
import com.chilitech.data.cache.database.model.UserModel;
import com.chilitech.domain.features.login.LoginContract;
import com.chilitech.domain.features.login.LoginPresenter;
import com.chilitech.domain.features.user.UserContract;
import com.chilitech.domain.features.user.UserPresenter;

import java.util.List;

import javax.inject.Inject;

//import butterknife.BindView;

public class LoginActivity extends DaggerBaseActivity implements LoginContract.View, UserContract.View {

    @Inject
    LoginPresenter loginPresenter;
    @Inject
    UserPresenter userPresenter;

    private ActivityLoginBinding activityLoginBinding;

    public static void goActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        ToolBarUtil.setToolBarTitle(getToolbar(), "Login");
        ToolBarUtil.setToolbarHomeAsUp(this);

        loginPresenter.setView(this);
        userPresenter.setView(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        if (loginPresenter != null) {
            loginPresenter.destroy();
        }
        if (userPresenter != null) {
            userPresenter.destroy();
        }
        super.onDestroy();
    }

    public void toLogin(View view) {

        String username = activityLoginBinding.etUsername.getText().toString().trim();
        String password = activityLoginBinding.etPassword.getText().toString().trim();


        loginPresenter.login(username, password);
    }

    @Override
    public void loginSucceed(TokenModel tokenModel) {
        Log.e(TAG, "===loginSucceed===");
        // TODO: 2017/8/3 通过token获取个人信息
        //模块化的presenter组合使用
        userPresenter.getInfo(tokenModel.getAccessToken(), null);
    }

    @Override
    public void loginError() {
        Log.e(TAG, "===loginError===");
    }

    @Override
    public void getInfoSuccess(UserModel userModel) {
        Log.e(TAG, "===getInfoSuccess===");
    }

    @Override
    public void getInfoError() {
        Log.e(TAG, "===getInfoError===");
    }

    @Override
    public void getFriendListSuccess(List<UserModel> friendList) {

    }

    @Override
    public void getFriendListError() {

    }
}

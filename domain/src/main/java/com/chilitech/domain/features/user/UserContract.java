package com.chilitech.domain.features.user;

import com.chilitech.data.cache.database.model.UserModel;
import com.chilitech.domain.base.BasePresenter;

import java.util.List;



public class UserContract {

    public interface View {

        void getInfoSuccess(UserModel userModel);

        void getInfoError();

        void getFriendListSuccess(List<UserModel> friendList);

        void getFriendListError();

    }

    public interface Presenter extends BasePresenter<View> {

        void getInfo(String accessToken, String userId);

        void getFriendList(String accessToken, String userId, int skip, int limit);
    }
}

package com.chilitech.domain.features.home;

import com.chilitech.domain.base.BasePresenter;

import java.util.List;

public class HomeContract {

    public interface View {

        void requestCategorySuccess(List<String> list);

        void requestCategoryFail();

        void requestCategoryDetailSuccess(List<String> list);

        void requestCategoryDetailFail();

        void requestBannerSuccess(String url);

        void requestBannerFail();

        void requestUpdateStrategySuccess(boolean needToBeUpdated);

        void requestUpdateStrategyFail();

        //TODO Kael 有策略更新后，更新逻辑是否有现成的代码调用
    }

    public interface Presenter extends BasePresenter<HomeContract.View> {

        void requestCategory(String token);

        void requestCategoryDetail(String id);

        void requestBanner(String token);

        void requestUpdateStrategy(String token);

        void gotoAddCategoryActivity();

        void gotoSearchActivity();
    }
}

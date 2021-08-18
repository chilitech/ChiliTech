package com.chilitech.domain.features.home;

import com.chilitech.domain.base.BaseUseCase;
import com.chilitech.domain.usecase.RequestBannerTask;
import com.chilitech.domain.usecase.RequestCategoryDetailTask;
import com.chilitech.domain.usecase.RequestCategoryTask;
import com.chilitech.domain.usecase.RequestUpdateStrategyTask;

import javax.inject.Inject;

public class HomePresenter implements HomeContract.Presenter {

    HomeContract.View mView;

    @Inject
    RequestBannerTask mRequestBannerTask;

    @Inject
    RequestCategoryDetailTask mRequestCategoryDetailTask;

    @Inject
    RequestCategoryTask mRequestCategoryTask;

    @Inject
    RequestUpdateStrategyTask mRequestUpdateStrategyTask;

    @Inject
    public HomePresenter() {
    }

    @Override
    public void setView(HomeContract.View view) {
        this.mView = view;
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.mView = null;
        if (mRequestBannerTask != null) {
            mRequestBannerTask.cancel();
        }
        if (mRequestCategoryDetailTask != null) {
            mRequestCategoryDetailTask.cancel();
        }
        if (mRequestCategoryTask != null) {
            mRequestCategoryTask.cancel();
        }
        if (mRequestUpdateStrategyTask != null) {
            mRequestUpdateStrategyTask.cancel();
        }
    }

    @Override
    public void requestCategory(String token) {
        mRequestCategoryTask.setRequestValues(new RequestCategoryTask.RequestValues(token));
        mRequestCategoryTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<RequestCategoryTask.ResponseValues>() {
            @Override
            public void onSuccess(RequestCategoryTask.ResponseValues response) {
                if (mView == null) {
                    return;
                }
                mView.requestCategorySuccess(response.getList());
            }

            @Override
            public void onError() {
                if (mView == null) {
                    return;
                }
                mView.requestCategoryFail();
            }
        });
        mRequestCategoryTask.run();
    }

    @Override
    public void requestCategoryDetail(String id) {
        mRequestCategoryDetailTask.setRequestValues(new RequestCategoryDetailTask.RequestValues(id));
        mRequestCategoryDetailTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<RequestCategoryDetailTask.ResponseValues>() {
            @Override
            public void onSuccess(RequestCategoryDetailTask.ResponseValues response) {
                if (mView == null) {
                    return;
                }
                mView.requestCategoryDetailSuccess(response.getList());
            }

            @Override
            public void onError() {
                if (mView == null) {
                    return;
                }
                mView.requestCategoryDetailFail();
            }
        });
        mRequestCategoryDetailTask.run();
    }

    @Override
    public void requestBanner(String token) {
        mRequestBannerTask.setRequestValues(new RequestBannerTask.RequestValues(token));
        mRequestBannerTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<RequestBannerTask.ResponseValues>() {
            @Override
            public void onSuccess(RequestBannerTask.ResponseValues response) {
                if (mView == null) {
                    return;
                }
                mView.requestBannerSuccess(response.getUrl());
            }

            @Override
            public void onError() {
                if (mView == null) {
                    return;
                }
                mView.requestBannerFail();
            }
        });
        mRequestBannerTask.run();
    }

    @Override
    public void requestUpdateStrategy(String token) {
        mRequestUpdateStrategyTask.setRequestValues(new RequestUpdateStrategyTask.RequestValues(token));
        mRequestUpdateStrategyTask.setUseCaseCallback(new BaseUseCase.UseCaseCallback<RequestUpdateStrategyTask.ResponseValues>() {
            @Override
            public void onSuccess(RequestUpdateStrategyTask.ResponseValues response) {
                if (mView == null) {
                    return;
                }
                mView.requestUpdateStrategySuccess(response.isNeedToBeUpdated());
            }

            @Override
            public void onError() {
                if (mView == null) {
                    return;
                }
                mView.requestUpdateStrategyFail();
            }
        });
        mRequestUpdateStrategyTask.run();
    }

    @Override
    public void gotoAddCategoryActivity() {

    }

    @Override
    public void gotoSearchActivity() {

    }
}

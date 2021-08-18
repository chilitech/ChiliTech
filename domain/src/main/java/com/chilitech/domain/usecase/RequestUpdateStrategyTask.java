package com.chilitech.domain.usecase;

import com.chilitech.domain.base.BaseUseCase;

public class RequestUpdateStrategyTask extends BaseUseCase<RequestUpdateStrategyTask.RequestValues, RequestUpdateStrategyTask.ResponseValues> {

    @Override
    protected void executeUseCase(RequestValues requestValues) {

    }

    @Override
    protected void cancelUseCase() {

    }

    public static final class RequestValues implements BaseUseCase.RequestValues {
        private final String token;

        public RequestValues(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }
    }

    public static final class ResponseValues implements BaseUseCase.ResponseValues {

        private final boolean needToBeUpdated;

        public ResponseValues(boolean needToBeUpdated) {
            this.needToBeUpdated = needToBeUpdated;
        }

        public boolean isNeedToBeUpdated() {
            return needToBeUpdated;
        }
    }
}

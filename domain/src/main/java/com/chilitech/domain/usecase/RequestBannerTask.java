package com.chilitech.domain.usecase;

import com.chilitech.domain.base.BaseUseCase;

public class RequestBannerTask extends BaseUseCase<RequestBannerTask.RequestValues, RequestBannerTask.ResponseValues> {

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

        private final String url;

        public ResponseValues(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}

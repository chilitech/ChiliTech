package com.chilitech.domain.usecase;

import com.chilitech.domain.base.BaseUseCase;

import java.util.List;

public class RequestCategoryTask extends BaseUseCase<RequestCategoryTask.RequestValues, RequestCategoryTask.ResponseValues> {
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

        private final List<String> list;

        public ResponseValues(List<String> list) {
            this.list = list;
        }

        public List<String> getList() {
            return list;
        }
    }
}

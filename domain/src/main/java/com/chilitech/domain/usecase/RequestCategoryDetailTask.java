package com.chilitech.domain.usecase;

import com.chilitech.domain.base.BaseUseCase;

import java.util.List;

public class RequestCategoryDetailTask extends BaseUseCase<RequestCategoryDetailTask.RequestValues, RequestCategoryDetailTask.ResponseValues> {

    @Override
    protected void executeUseCase(RequestValues requestValues) {

    }

    @Override
    protected void cancelUseCase() {

    }

    public static final class RequestValues implements BaseUseCase.RequestValues {
        private final String id;

        public RequestValues(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
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

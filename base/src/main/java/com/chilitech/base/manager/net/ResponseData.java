package com.chilitech.base.manager.net;

import java.util.Map;



public class ResponseData<B> {

    private int code;
    private Map<String, Object> headers;
    private B body;

    public ResponseData(int code, Map<String, Object> headers, B body) {
        this.code = code;
        this.headers = headers;
        this.body = body;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public B getBody() {
        return body;
    }

    public int getCode() {
        return code;
    }
}

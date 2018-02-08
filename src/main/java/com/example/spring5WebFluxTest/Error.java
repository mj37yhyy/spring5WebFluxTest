package com.example.spring5WebFluxTest;

import java.util.List;

public class Error {

    String code = "UNAUTHORIZED";
    String message = "access to the requested resource is not authorized";
    private List<Scope> detail;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Scope> getDetail() {
        return detail;
    }

    public void setDetail(List<Scope> detail) {
        this.detail = detail;
    }
}

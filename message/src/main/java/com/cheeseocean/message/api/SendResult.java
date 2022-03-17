package com.cheeseocean.message.api;

import java.io.Serializable;

public class SendResult implements Serializable {
    private static final long serialVersionUID = -81791163265289846L;

    private int code;

    private String message;

    public SendResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

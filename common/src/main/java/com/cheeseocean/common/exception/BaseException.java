package com.cheeseocean.common.exception;

import org.springframework.util.ObjectUtils;

import com.cheeseocean.common.web.response.IResultStatus;

public class BaseException extends RuntimeException {
    Object data;
    IResultStatus status;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public IResultStatus getStatus() {
        return status;
    }

    public void setStatus(IResultStatus status) {
        this.status = status;
    }

    public BaseException(IResultStatus status) {
        super(status.getMessage());
        this.status = status;
    }

    public BaseException(IResultStatus status, Object... data) {
        super(status.getMessage());
        this.status = status;
        if (!ObjectUtils.isEmpty(data)) {
            this.data = data;
        }
    }

    public BaseException(IResultStatus status, Throwable cause, Object... data) {
        super(status.getMessage(), cause);
        this.status = status;
        if (!ObjectUtils.isEmpty(data)) {
            this.data = data;
        }
    }
}

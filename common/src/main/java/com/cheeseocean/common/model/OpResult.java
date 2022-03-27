package com.cheeseocean.common.model;

public class OpResult {

    private Integer errCode;

    private String errMsg;

    public OpResult() {
    }

    private OpResult(Builder builder) {
        setErrCode(builder.errCode);
        setErrMsg(builder.errMsg);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public static final class Builder {
        private Integer errCode;
        private String errMsg;

        private Builder() {}

        public Builder errCode(Integer val) {
            errCode = val;
            return this;
        }

        public Builder errMsg(String val) {
            errMsg = val;
            return this;
        }

        public OpResult build() {
            return new OpResult(this);
        }
    }
}

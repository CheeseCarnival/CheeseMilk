package com.cheeseocean.common.web.response;

public class Result<T> {

    private int code;

    private String message;

    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result(ResultStatus status, T data){
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }
    public Result(int code, String message, T data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(IResultStatus status){
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public Result(int code, String message){
        this.code = code;
        this.message = message;
    }
    public static <T> Result<T> ok(T data){
        return new Result<>(ResultStatus.SUCCESS, data);
    }

    public static <T> Result<T> error(T data){
        return new Result<>(ResultStatus.ERROR, data);
    }

    public static <T> Result<T> error(ResultStatus status, T data){
        return new Result<>(status, data);
    }
}

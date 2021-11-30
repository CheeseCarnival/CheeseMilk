package cn.qisee.common.exception;

public class UnexpectedException extends RuntimeException{
    private String errorKey;
    public UnexpectedException(String errorKey) {
        this.errorKey = errorKey;
    }

}

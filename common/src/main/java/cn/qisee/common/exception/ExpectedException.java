package cn.qisee.common.exception;

public class ExpectedException extends RuntimeException{
    private String errorKey;

    public ExpectedException(String errorKey) {
        this.errorKey = errorKey;
    }

}

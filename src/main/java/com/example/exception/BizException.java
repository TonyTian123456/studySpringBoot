package com.example.exception;

public class BizException extends RuntimeException {
    private int errorCode;
    private String msg;
    private String developMsg;
    private String uri;

    public BizException(ErrMsgEnum errMsgEnum) {
        this.errorCode = errMsgEnum.getErrCode();
        this.msg = errMsgEnum.getErrMsg();
    }

    public BizException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.msg = message;
    }

    public BizException(int errorCode, String message, String developMsg) {
        this(errorCode, message);
        this.developMsg = developMsg;
    }

    public BizException(int errorCode, String message, Throwable cause) {
        this(errorCode, message);
        this.developMsg = cause.getMessage();
    }

    public BizException(int errorCode, String message, String developMsg, String uri) {
        this(errorCode, message, developMsg);
        this.uri = uri;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDevelopMsg() {
        return this.developMsg;
    }

    public void setDevelopMsg(String developMsg) {
        this.developMsg = developMsg;
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String toString() {
        return "BizException{errorCode='" + this.errorCode + '\'' + ", msg='" + this.msg + '\'' + ", developMsg='" + this.developMsg + '\'' + ", uri='" + this.uri + '\'' + '}';
    }
}
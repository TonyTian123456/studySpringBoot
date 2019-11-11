package com.example.entity;

import com.example.exception.BizException;
import com.example.exception.ErrMsgEnum;

import java.io.Serializable;

public final class StdJson<T> implements Serializable {
    public static final int DEFAULT_SUCCESS_CODE = 0;
    public static final int DEFAULT_FAILURE_CODE = 1;
//    @ApiModelProperty("异常时的错误编码")
    private final int status;
//    @ApiModelProperty("返回对象信息")
    private final T data;
//    @ApiModelProperty("返回提示信息")
    private final String message;

    private StdJson(int status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public int getStatus() {
        return this.status;
    }

    public T getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public static StdJson ok() {
        return ok((Object)null);
    }

    public static StdJson ok(Object data) {
        return new StdJson(0, data, (String)null);
    }

    public static StdJson err(String message) {
        return err(1, message);
    }

    public static StdJson err(BizException ex) {
        return new StdJson(ex.getErrorCode(), (Object)null, ex.getMsg());
    }

    public static StdJson err(ErrMsgEnum errCode) {
        return err(errCode.getErrCode(), errCode.getErrMsg());
    }

    public static StdJson err(int code, String message) {
        return new StdJson(code, (Object)null, message);
    }

    public String toString() {
        return "StdJson{status=" + this.status + ", data=" + this.data + ", message='" + this.message + '\'' + '}';
    }
}

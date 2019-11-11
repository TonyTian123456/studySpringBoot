package com.example.exception;

/**
 * Created by cicada on 2019/8/21.
 */
public enum ErrCode implements ErrMsgEnum{

    SYSTEM_ERROR(3, "网络不给力，请稍后再试"),
    BIZ_PARAM_INVALID(1, "参数不合法"),
    TOKEN_STATUS_NO(8000,"无token，请重新登录"),
    TOKEN_STATUS_INVALID(8001,"token过期，请重新登录"),
    TOKEN_STATUS_ERROR(8002,"accessToken 错误"),


    USER_LOG_ERROR(8003,"用户名或密码不正确"),
    USER_INFO_IS_EMPTY(8004,"用户名或密码不能为空")
    ;

    private int errCode;

    private String errMsg;

    ErrCode(int code, String message) {
        this.errCode = code;
        this.errMsg = message;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }


    @Override
    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }


}

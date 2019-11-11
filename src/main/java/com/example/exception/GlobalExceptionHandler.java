package com.example.exception;



import com.example.entity.StdJson;
import com.example.utils.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 异常拦截处理。
     *
     * @param ex
     *            异常
     * @return 返回异常视图。
     */
    @ExceptionHandler(value = {BizException.class, MethodArgumentNotValidException.class,
            Exception.class })
    public StdJson checkedException(Exception ex) {
//        ex.printStackTrace();
        logger.error(ex.getMessage(),ex);
        if (ex instanceof BizException) {
            return StdJson.err((BizException)ex);
        }else if (ex instanceof MethodArgumentNotValidException) {
            List<String> errorMsgs = new ArrayList<String>();
            List<ObjectError> errors =  ((MethodArgumentNotValidException)ex).getBindingResult()
                    .getAllErrors();
            for (ObjectError error : errors) {
                errorMsgs.add(error.getDefaultMessage());
            }
            String errorMsg = StringUtils.join(errorMsgs, "|");
            logger.error("统一日志处理：{}",errorMsg);
            return StdJson.err(ErrCode.BIZ_PARAM_INVALID.getErrCode(),errorMsg);
        }else if(ex instanceof ConstraintViolationException){
            List<String> errorMsgs = new ArrayList<>();
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException)ex).getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                errorMsgs.add(violation.getMessage());
            }
            String errorMsg = StringUtils.join(errorMsgs, "|");
            logger.error("统一日志处理：{}",errorMsg);
            return StdJson.err(ErrCode.BIZ_PARAM_INVALID.getErrCode(),errorMsg);
        }else if(ex instanceof BindException){
            BindException bindException = (BindException) ex;
            List<FieldError> fieldErrorList = bindException.getFieldErrors();
            String message = "参数异常";
            if (fieldErrorList != null && fieldErrorList.size() > 0) {
                message = fieldErrorList.get(0).getDefaultMessage();
            }
            logger.error("统一日志处理：{}",message);
            return StdJson.err(ErrCode.BIZ_PARAM_INVALID.getErrCode(),message);

        }else{
            logger.error("统一日志处理：{}", ExceptionUtils.getStackTrace(ex));
            return StdJson.err(ErrCode.SYSTEM_ERROR.getErrCode(), ErrCode.SYSTEM_ERROR.getErrMsg());
        }
    }
}

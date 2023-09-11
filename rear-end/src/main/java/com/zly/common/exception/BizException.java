package com.zly.common.exception;


import com.zly.common.enums.ErrorCode;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * 自定义异常
 */
@Data
public class BizException extends RuntimeException{

    private int code;

    public BizException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BizException(String message) {
        super(message);
        this.code = HttpStatus.BAD_REQUEST.value();
    }
}

package com.tangrl.pan.core.exception;

import com.tangrl.pan.core.response.ResponseCode;
import lombok.Data;

/**
 * 自定义全局业务异常类
 * 业务逻辑中抛出和捕获特定的业务异常
 */
@Data
public class RPanBusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String message;

    public RPanBusinessException(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getDesc();
    }

    public RPanBusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RPanBusinessException(String message) {
        this.code = ResponseCode.ERROR_PARAM.getCode();
        this.message = message;
    }

    public RPanBusinessException() {
        this.code = ResponseCode.ERROR_PARAM.getCode();
        this.message = ResponseCode.ERROR_PARAM.getDesc();
    }

}

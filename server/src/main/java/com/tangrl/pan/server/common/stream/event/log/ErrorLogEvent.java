package com.tangrl.pan.server.common.stream.event.log;

import lombok.*;

import java.io.Serializable;

/**
 * 错误日志事件
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ErrorLogEvent implements Serializable {

    private static final long serialVersionUID = -8680220649817382860L;

    /**
     * 错误日志的内容
     */
    private String errorMsg;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

    public ErrorLogEvent(String errorMsg, Long userId) {
        this.errorMsg = errorMsg;
        this.userId = userId;
    }

}

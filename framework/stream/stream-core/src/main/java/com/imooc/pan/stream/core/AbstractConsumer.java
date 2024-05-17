package com.imooc.pan.stream.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;

import java.util.Objects;

/**
 * 消费者的公用父类
 * 主要做公用逻辑的抽离
 */
@Slf4j
public abstract class AbstractConsumer {

    /**
     * 公用的消息打印日志
     *
     * @param message
     */
    protected void printLog(Message message) {
        log.info("{} start consume the message, the message is {}", this.getClass().getSimpleName(), message);
    }

    /**
     * 公用的空消息参数校验
     *
     * @param message
     * @return
     */
    protected boolean isEmptyMessage(Message message) {
        if (Objects.isNull(message)) {
            return true;
        }
        Object payload = message.getPayload();
        if (Objects.isNull(payload)) {
            return true;
        }
        return false;
    }

}

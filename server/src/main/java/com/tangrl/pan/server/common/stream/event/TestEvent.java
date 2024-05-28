package com.tangrl.pan.server.common.stream.event;

import lombok.Data;

import java.io.Serializable;

/**
 * 测试事件实体
 */
@Data
public class TestEvent implements Serializable {

    private static final long serialVersionUID = -67041500843171728L;

    /**
     * 消息属性-名称
     */
    private String name;
}

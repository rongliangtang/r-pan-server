package com.tangrl.pan.server.modules.file.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件合并标识枚举类
 */
@Getter
@AllArgsConstructor
public enum MergeFlagEnum {

    /**
     * 不需要合并
     */
    NOT_READY(0),

    /**
     * 需要合并
     */
    READY(1);

    private Integer code;

}

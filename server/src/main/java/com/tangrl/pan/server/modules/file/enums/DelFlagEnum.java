package com.tangrl.pan.server.modules.file.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 文件删除标识枚举类
 */
@AllArgsConstructor
@Getter
public enum DelFlagEnum {

    /**
     * 未删除
     */
    NO(0),
    /**
     * 已删除
     */
    YES(1);

    private Integer code;

}

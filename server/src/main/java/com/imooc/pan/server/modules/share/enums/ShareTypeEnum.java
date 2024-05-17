package com.imooc.pan.server.modules.share.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分享类型枚举类
 */
@AllArgsConstructor
@Getter
public enum ShareTypeEnum {

    NEED_SHARE_CODE(0, "有提取码");

    private Integer code;

    private String desc;

}

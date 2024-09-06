package com.tangrl.pan.core.constants;

import org.apache.commons.lang3.StringUtils;

/**
 * RPan 公用基础常量类
 * 包括需要用到的字符串、整型常量
 */
public interface RPanConstants {

    /**
     * 公用的字符串分隔符
     */
    String COMMON_SEPARATOR = "__,__";

    /**
     * 空字符串
     */
    String EMPTY_STR = StringUtils.EMPTY;

    /**
     * 点 常量
     */
    String POINT_STR = ".";

    /**
     * 斜杠字符串
     */
    String SLASH_STR = "/";

    /**
     * Long 常量 0
     */
    Long ZERO_LONG = 0L;

    /**
     * Integer 常量 0
     */
    Integer ZERO_INT = 0;

    /**
     * Integer 常量 1
     */
    Integer ONE_INT = 1;

    /**
     * Integer 常量 2
     */
    Integer TWO_INT = 2;

    /**
     * Integer 常量 -1
     */
    Integer MINUS_ONE_INT = -1;

    /**
     * TRUE字符串
     */
    String TRUE_STR = "true";

    /**
     * FALSE字符串
     */
    String FALSE_STR = "false";

    /**
     * 组件扫描基础路径
     */
    String BASE_COMPONENT_SCAN_PATH = "com.tangrl.pan";

    /**
     * 问号常量
     */
    String QUESTION_MARK_STR = "?";

    /**
     * 等号常量
     */
    String EQUALS_MARK_STR = "=";

    /**
     * 逻辑与常量
     */
    String AND_MARK_STR = "&";

    /**
     * 左中括号常量
     */
    String LEFT_BRACKET_STR = "[";

    /**
     * 右中括号常量
     */
    String RIGHT_BRACKET_STR = "]";

    /**
     * 公用加密字符串
     */
    String COMMON_ENCRYPT_STR = "****";
}

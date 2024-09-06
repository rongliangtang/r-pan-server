package com.tangrl.pan.core.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 公用返回对象
 * 封装 API 响应结果，包括状态码、状态消息、返回数据
 */
// 保证json序列化的时候，如果属性为null，key也就一起消失
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class R<T> implements Serializable {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 状态说明文案
     */
    private String message;

    /**
     * 返回承载
     */
    private T data;

    // 私有的构造函数，确保对象只能通过类中的静态方法来创建，封装了对象的创建逻辑。
    private R(Integer code) {
        this.code = code;
    }

    private R(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    // 方法不会被序列化的，使用注解的目的是防止自定义序列化逻辑误操作
    // Jackson 库中的忽略序列化
    @JsonIgnore
    // FastJSON 库中的忽略序列化
    @JSONField(serialize = false)
    public boolean isSuccess() {
        return Objects.equals(this.code, ResponseCode.SUCCESS.getCode());
    }

    // 构造 R 对象的静态方法
    public static <T> R<T> success() {
        return new R<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> R<T> success(String message) {
        return new R<T>(ResponseCode.SUCCESS.getCode(), message);
    }

    public static <T> R<T> data(T data) {
        return new R<T>(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getDesc(), data);
    }

    public static <T> R<T> fail() {
        return new R<T>(ResponseCode.ERROR.getCode());
    }

    public static <T> R<T> fail(String message) {
        return new R<T>(ResponseCode.ERROR.getCode(), message);
    }

    public static <T> R<T> fail(Integer code, String message) {
        return new R<T>(code, message);
    }

    public static <T> R<T> fail(ResponseCode responseCode) {
        return new R<T>(responseCode.getCode(), responseCode.getDesc());
    }

}

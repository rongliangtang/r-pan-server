package com.tangrl.pan.server.modules.user.context;

import com.tangrl.pan.server.modules.user.entity.RPanUser;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册业务的上下文实体对象
 */
@Data
public class UserRegisterContext implements Serializable {

    private static final long serialVersionUID = -4835860208501507531L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 密保问题
     */
    private String question;

    /**
     * 密保答案
     */
    private String answer;

    /**
     * 用户实体对象，用于存储数据库，包括 salt、加密后的password 等
     * 创建这个对象属性的原因是，包括不同的字段、且值需要加密
     */
    private RPanUser entity;

}

package com.tangrl.pan.server.modules.user.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 校验用户名称PO对象
 */
@Data
public class CheckUsernameContext implements Serializable {

    private static final long serialVersionUID = -7117844539768126736L;

    /**
     * 用户名称
     */
    private String username;

}

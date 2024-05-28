package com.tangrl.pan.server.modules.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel("用户搜索历史返回实体")
@Data
public class UserSearchHistoryVO implements Serializable {

    private static final long serialVersionUID = -1336804234981929854L;

    @ApiModelProperty("搜索文案")
    private String value;

}

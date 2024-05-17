package com.imooc.pan.server.modules.user.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户查询搜索历史记录上下文实体
 */
@Data
public class QueryUserSearchHistoryContext implements Serializable {

    /**
     * 当前登录用户的ID
     */
    private Long userId;

}

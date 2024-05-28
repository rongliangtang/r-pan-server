package com.tangrl.pan.server.modules.file.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 搜索文件面包屑列表的上下文信息实体
 */
@Data
public class QueryBreadcrumbsContext implements Serializable {

    private static final long serialVersionUID = -5125165545964102997L;

    /**
     * 文件ID
     */
    private Long fileId;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

}

package com.imooc.pan.server.modules.recycle.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询用户回收站文件列表上下文实体对象
 */
@Data
public class QueryRecycleFileListContext implements Serializable {

    private static final long serialVersionUID = -9177250150561429061L;

    private Long userId;

}

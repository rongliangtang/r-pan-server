package com.tangrl.pan.server.modules.file.context;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 查询文件列表上下文实体
 */
@Data
public class QueryFileListContext implements Serializable {

    private static final long serialVersionUID = 1361135823223937852L;

    /**
     * 父文件夹ID
     */
    private Long parentId;

    /**
     * 文件类型的集合
     */
    private List<Integer> fileTypeArray;

    /**
     * 当前的登录用户
     */
    private Long userId;

    /**
     * 文件的删除标识
     */
    private Integer delFlag;

    /**
     * 文件ID集合
     */
    private List<Long> fileIdList;

}

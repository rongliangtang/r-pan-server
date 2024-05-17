package com.imooc.pan.server.modules.file.context;

import com.imooc.pan.server.modules.file.entity.RPanUserFile;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 文件复制操作上下文实体对象
 */
@Data
public class CopyFileContext implements Serializable {

    private static final long serialVersionUID = -4498476381209653058L;

    /**
     * 要复制的文件ID集合
     */
    private List<Long> fileIdList;

    /**
     * 目标文件夹ID
     */
    private Long targetParentId;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

    /**
     * 要复制的文件列表
     */
    private List<RPanUserFile> prepareRecords;

}

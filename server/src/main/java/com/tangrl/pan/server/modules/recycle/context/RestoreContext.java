package com.tangrl.pan.server.modules.recycle.context;

import com.tangrl.pan.server.modules.file.entity.RPanUserFile;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 还原文件的上下文实体对象
 */
@Data
public class RestoreContext implements Serializable {

    private static final long serialVersionUID = 9084417258307062516L;

    /**
     * 要操作的文件ID的集合
     */
    private List<Long> fileIdList;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

    /**
     * 要被还原的文件记录列表
     */
    private List<RPanUserFile> records;

}

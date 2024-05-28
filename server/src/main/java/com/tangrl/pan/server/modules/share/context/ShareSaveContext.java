package com.tangrl.pan.server.modules.share.context;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 保存到我的网盘上下文实体对象
 */
@Data
public class ShareSaveContext implements Serializable {

    private static final long serialVersionUID = -5381349624053569146L;

    /**
     * 要保存的文件ID列表
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
     * 分享的ID
     */
    private Long shareId;

}

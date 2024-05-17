package com.imooc.pan.server.modules.share.context;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 取消分享的上下文实体对象
 */
@Data
public class CancelShareContext implements Serializable {

    private static final long serialVersionUID = -7170454089602402200L;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

    /**
     * 要取消的分享ID集合
     */
    private List<Long> shareIdList;

}

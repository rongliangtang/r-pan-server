package com.tangrl.pan.server.modules.file.context;

import lombok.Data;

import java.io.Serializable;

/**
 * 查询用户实际文件列表上下文实体对象
 */
@Data
public class QueryRealFileListContext implements Serializable {

    private static final long serialVersionUID = -8242260274150084368L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 文件的唯一标识
     */
    private String identifier;

}

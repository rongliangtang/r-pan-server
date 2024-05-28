package com.tangrl.pan.server.modules.file.context;

import com.tangrl.pan.server.modules.file.entity.RPanUserFile;
import lombok.Data;

import java.io.Serializable;

/**
 * 文件重命名参数上下文对象
 */
@Data
public class UpdateFilenameContext implements Serializable {

    private static final long serialVersionUID = 6171000069397717175L;

    /**
     * 要更新的文件ID
     */
    private Long fileId;

    /**
     * 新的文件名称
     */
    private String newFilename;

    /**
     * 当前的登录用户ID
     */
    private Long userId;

    /**
     * 要更新的文件记录实体
     */
    private RPanUserFile entity;

}

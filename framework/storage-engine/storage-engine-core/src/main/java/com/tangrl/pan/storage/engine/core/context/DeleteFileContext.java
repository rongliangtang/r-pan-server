package com.tangrl.pan.storage.engine.core.context;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 删除物理文件的上下文试实体信息
 */
@Data
public class DeleteFileContext implements Serializable {

    private static final long serialVersionUID = 8752492154745347237L;

    /**
     * 要删除的物理文件路径的集合
     */
    private List<String> realFilePathList;

}

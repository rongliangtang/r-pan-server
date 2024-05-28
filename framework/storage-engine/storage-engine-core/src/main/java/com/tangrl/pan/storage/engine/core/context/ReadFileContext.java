package com.tangrl.pan.storage.engine.core.context;

import lombok.Data;

import java.io.OutputStream;
import java.io.Serializable;

/**
 * 文件读取的上下文实体信息
 */
@Data
public class ReadFileContext implements Serializable {

    private static final long serialVersionUID = 2506771761529717302L;

    /**
     * 文件的真实存储路径
     */
    private String realPath;

    /**
     * 文件的输出流
     */
    private OutputStream outputStream;

}

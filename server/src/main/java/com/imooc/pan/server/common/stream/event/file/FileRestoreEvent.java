package com.imooc.pan.server.common.stream.event.file;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 文件还原事件实体
 */
@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
public class FileRestoreEvent implements Serializable {

    private static final long serialVersionUID = -5823524176954356392L;

    /**
     * 被成功还原的文件记录ID集合
     */
    private List<Long> fileIdList;

    public FileRestoreEvent(List<Long> fileIdList) {
        this.fileIdList = fileIdList;
    }

}

package com.tangrl.pan.server.common.stream.event.file;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * 文件删除事件
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class DeleteFileEvent implements Serializable {

    private static final long serialVersionUID = 1052162045311253928L;

    private List<Long> fileIdList;

    public DeleteFileEvent(List<Long> fileIdList) {
        this.fileIdList = fileIdList;
    }

}

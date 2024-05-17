package com.imooc.pan.server.modules.file.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@ApiModel("查询用户已上传的文件分片列表返回实体")
@Data
public class UploadedChunksVO implements Serializable {

    private static final long serialVersionUID = 8694674586602329820L;

    @ApiModelProperty("已上传的分片编号列表")
    private List<Integer> uploadedChunks;

}

package com.tangrl.pan.server.modules.file.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel("文件搜索参数实体")
@Data
public class FileSearchPO implements Serializable {

    private static final long serialVersionUID = 5477817929836619174L;

    @ApiModelProperty(value = "搜索的关键字", required = true)
    @NotBlank(message = "搜索关键字不能为空")
    private String keyword;

    @ApiModelProperty(value = "文件类型，多个文件类型使用公用分隔符拼接")
    private String fileTypes;

}

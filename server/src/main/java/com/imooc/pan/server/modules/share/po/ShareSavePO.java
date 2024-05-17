package com.imooc.pan.server.modules.share.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@ApiModel("保存至我的网盘参数实体对象")
@Data
public class ShareSavePO implements Serializable {

    private static final long serialVersionUID = 5218212643469089685L;

    @ApiModelProperty(value = "要转存的文件ID集合，多个使用公用分隔符拼接", required = true)
    @NotBlank(message = "请选择要保存的文件")
    private String fileIds;

    @ApiModelProperty(value = "要转存到的文件夹ID", required = true)
    @NotBlank(message = "请选择要保存到的文件夹")
    private String targetParentId;

}

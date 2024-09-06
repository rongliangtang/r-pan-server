package com.tangrl.pan.swagger2;

import com.tangrl.pan.core.constants.RPanConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * swagger2 配置属性实体
 * 一个自定义的配置类，用于加载和管理与 Swagger 2 相关的配置信息
 * 赋值的是默认值，在
 */
// 来自 Lombok，用于生成 getter 和 setter 方法、toString() 方法、equals() 方法和 hashCode() 方法。
@Data
// 将该类标识为一个 Spring bean 组件，使其能够被 Spring 容器管理。
@Component
// 将 application.yaml 配置文件中以 swagger2 开头的属性映射到这个类的字段中。
@ConfigurationProperties(prefix = "swagger2")
public class Swagger2ConfigProperties {

    private boolean show = true;

    private String groupName = "r-pan";

    private String basePackage = RPanConstants.BASE_COMPONENT_SCAN_PATH;

    private String title = "r-pan-server";

    private String description = "r-pan-server";

    private String termsOfServiceUrl = "http://127.0.0.1:${server.port}";

    private String contactName = "tom";

    private String contactUrl = "https://tangrl.cn";

    private String contactEmail = "947925189@qq.com";

    private String version = "1.0";

}

package com.tangrl.pan.server;

import com.tangrl.pan.core.constants.RPanConstants;
import com.tangrl.pan.server.common.stream.channel.PanChannels;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 标识这是一个 Spring Boot 应用程序的启动类。scanBasePackages 用于指定要扫描的基础包路径。
// 引入了模块，则能扫描到其它模块。com.tangrl.pan 是基础包名。
@SpringBootApplication(scanBasePackages = RPanConstants.BASE_COMPONENT_SCAN_PATH)
// 启用对 @WebServlet、@WebFilter 和 @WebListener 注解的扫描，并注册为 Servlet 组件。basePackages 用于指定要扫描的基础包路径。
@ServletComponentScan(basePackages = RPanConstants.BASE_COMPONENT_SCAN_PATH)
// 启用注解驱动的事务管理。
@EnableTransactionManagement
// 指定要扫描的 MyBatis 映射器接口的包路径。
@MapperScan(basePackages = RPanConstants.BASE_COMPONENT_SCAN_PATH + ".server.modules.**.mapper")
// 启用异步方法执行。
@EnableAsync
// 用于绑定 Spring Cloud Stream 的通道接口 PanChannels。
@EnableBinding(PanChannels.class)
@Slf4j
public class RPanServerLauncher {

    public static void main(String[] args) {
        // 启动 Spring Boot 应用，并返回
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RPanServerLauncher.class);
        // 调用自定义方法打印启动日志。
        printStartLog(applicationContext);
    }

    /**
     * 打印启动日志
     * 打印成功启动后的端口信息
     * @param applicationContext
     */
    private static void printStartLog(ConfigurableApplicationContext applicationContext) {
        String serverPort = applicationContext.getEnvironment().getProperty("server.port");
        String serverUrl = String.format("http://%s:%s", "127.0.0.1", serverPort);
        log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "r pan server started at: ", serverUrl));
        if (checkShowServerDoc(applicationContext)) {
            log.info(AnsiOutput.toString(AnsiColor.BRIGHT_BLUE, "r pan server's doc started at:", serverUrl + "/doc.html"));
        }
        log.info(AnsiOutput.toString(AnsiColor.BRIGHT_YELLOW, "r pan server has started successfully!"));
    }

    /**
     * 校验是否开启了接口文档
     *
     * @param applicationContext
     * @return
     */
    private static boolean checkShowServerDoc(ConfigurableApplicationContext applicationContext) {
        // 检查配置属性 swagger2.show 是否为 true，并且检查 Spring 容器中是否包含名为 swagger2Config 的 Bean。
        return applicationContext.getEnvironment().getProperty("swagger2.show", Boolean.class, true) && applicationContext.containsBean("swagger2Config");
    }

}

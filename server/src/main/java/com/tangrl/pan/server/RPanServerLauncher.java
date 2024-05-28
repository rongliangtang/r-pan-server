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

@SpringBootApplication(scanBasePackages = RPanConstants.BASE_COMPONENT_SCAN_PATH)
@ServletComponentScan(basePackages = RPanConstants.BASE_COMPONENT_SCAN_PATH)
@EnableTransactionManagement
@MapperScan(basePackages = RPanConstants.BASE_COMPONENT_SCAN_PATH + ".server.modules.**.mapper")
@EnableAsync
@EnableBinding(PanChannels.class)
@Slf4j
public class RPanServerLauncher {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RPanServerLauncher.class);
        printStartLog(applicationContext);
    }

    /**
     * 打印启动日志
     *
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
        return applicationContext.getEnvironment().getProperty("swagger2.show", Boolean.class, true) && applicationContext.containsBean("swagger2Config");
    }

}

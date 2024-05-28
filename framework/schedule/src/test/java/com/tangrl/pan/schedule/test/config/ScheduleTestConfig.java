package com.tangrl.pan.schedule.test.config;

import com.tangrl.pan.core.constants.RPanConstants;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 单元测试配置类
 */
@SpringBootConfiguration
@ComponentScan(RPanConstants.BASE_COMPONENT_SCAN_PATH + ".schedule")
public class ScheduleTestConfig {
}

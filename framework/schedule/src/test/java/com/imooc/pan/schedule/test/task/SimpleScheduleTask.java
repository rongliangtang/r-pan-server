package com.imooc.pan.schedule.test.task;

import com.imooc.pan.schedule.ScheduleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 简单的定时任务执行逻辑
 */
@Component
@Slf4j
public class SimpleScheduleTask implements ScheduleTask {

    @Override
    public String getName() {
        return "测试定时任务";
    }

    @Override
    public void run() {
        log.info(getName() + "正在执行。。。");
    }

}

package com.tangrl.pan.server.common.schedule.launcher;

import com.tangrl.pan.schedule.ScheduleManager;
import com.tangrl.pan.server.common.schedule.task.RebuildShareSimpleDetailBloomFilterTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 定时重建简单分享详情布隆过滤器任务触发器
 */
@Slf4j
@Component
public class RebuildShareSimpleDetailBloomFilterTaskLauncher implements CommandLineRunner {

    private final static String CRON = "1 0 0 * * ? ";

    @Autowired
    private RebuildShareSimpleDetailBloomFilterTask task;

    @Autowired
    private ScheduleManager scheduleManager;

    @Override
    public void run(String... args) throws Exception {
        scheduleManager.startTask(task, CRON);
    }

}

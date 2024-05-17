package com.imooc.pan.schedule;

/**
 * 定时任务的任务接口
 */
public interface ScheduleTask extends Runnable {

    /**
     * 获取定时任务的名称
     *
     * @return
     */
    String getName();

}

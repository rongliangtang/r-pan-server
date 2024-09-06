package com.tangrl.pan.schedule;

/**
 * 定时任务的任务接口
 * 实现定时任务业务的类会继承这个接口
 */
public interface ScheduleTask extends Runnable {

    /**
     * 获取定时任务的名称
     *
     * @return
     */
    String getName();

}

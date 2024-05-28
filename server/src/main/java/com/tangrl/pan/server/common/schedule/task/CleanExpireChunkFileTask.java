package com.tangrl.pan.server.common.schedule.task;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tangrl.pan.core.constants.RPanConstants;
import com.tangrl.pan.schedule.ScheduleTask;
import com.tangrl.pan.server.common.stream.channel.PanChannels;
import com.tangrl.pan.server.common.stream.event.log.ErrorLogEvent;
import com.tangrl.pan.server.modules.file.entity.RPanFileChunk;
import com.tangrl.pan.server.modules.file.service.IFileChunkService;
import com.tangrl.pan.storage.engine.core.StorageEngine;
import com.tangrl.pan.storage.engine.core.context.DeleteFileContext;
import com.tangrl.pan.stream.core.IStreamProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 过期分片清理任务
 */
@Component
@Slf4j
public class CleanExpireChunkFileTask implements ScheduleTask {

    private static final Long BATCH_SIZE = 500L;

    @Autowired
    private IFileChunkService iFileChunkService;

    @Autowired
    private StorageEngine storageEngine;

    @Autowired
    @Qualifier(value = "defaultStreamProducer")
    private IStreamProducer producer;

    /**
     * 获取定时任务的名称
     *
     * @return
     */
    @Override
    public String getName() {
        return "CleanExpireChunkFileTask";
    }

    /**
     * 执行清理任务
     * <p>
     * 1、滚动查询过期的文件分片
     * 2、删除物理文件（委托文件存储引擎去实现）
     * 3、删除过期文件分片的记录信息
     * 4、重置上次查询的最大文件分片记录ID，继续滚动查询
     */
    @Override
    public void run() {
        log.info("{} start clean expire chunk file...", getName());

        List<RPanFileChunk> expireFileChunkRecords;
        Long scrollPointer = 1L;

        do {
            expireFileChunkRecords = scrollQueryExpireFileChunkRecords(scrollPointer);
            if (CollectionUtils.isNotEmpty(expireFileChunkRecords)) {
                deleteRealChunkFiles(expireFileChunkRecords);
                List<Long> idList = deleteChunkFileRecords(expireFileChunkRecords);
                scrollPointer = Collections.max(idList);
            }
        } while (CollectionUtils.isNotEmpty(expireFileChunkRecords));

        log.info("{} finish clean expire chunk file...", getName());
    }

    /*********************************************private*********************************************/

    /**
     * 滚动查询过期的文件分片记录
     *
     * @param scrollPointer
     * @return
     */
    private List<RPanFileChunk> scrollQueryExpireFileChunkRecords(Long scrollPointer) {
        QueryWrapper queryWrapper = Wrappers.query();
        queryWrapper.le("expiration_time", new Date());
        queryWrapper.ge("id", scrollPointer);
        queryWrapper.last(" limit " + BATCH_SIZE);
        return iFileChunkService.list(queryWrapper);
    }

    /**
     * 物理删除过期的文件分片文件实体
     *
     * @param expireFileChunkRecords
     */
    private void deleteRealChunkFiles(List<RPanFileChunk> expireFileChunkRecords) {
        DeleteFileContext deleteFileContext = new DeleteFileContext();
        List<String> realPaths = expireFileChunkRecords.stream().map(RPanFileChunk::getRealPath).collect(Collectors.toList());
        deleteFileContext.setRealFilePathList(realPaths);
        try {
            storageEngine.delete(deleteFileContext);
        } catch (IOException e) {
            saveErrorLog(realPaths);
        }
    }

    /**
     * @param realPaths
     */
    private void saveErrorLog(List<String> realPaths) {
        ErrorLogEvent event = new ErrorLogEvent("文件物理删除失败，请手动执行文件删除！文件路径为：" + JSON.toJSONString(realPaths), RPanConstants.ZERO_LONG);
        producer.sendMessage(PanChannels.ERROR_LOG_OUTPUT, event);
    }

    /**
     * 删除过期文件分片记录
     *
     * @param expireFileChunkRecords
     * @return
     */
    private List<Long> deleteChunkFileRecords(List<RPanFileChunk> expireFileChunkRecords) {
        List<Long> idList = expireFileChunkRecords.stream().map(RPanFileChunk::getId).collect(Collectors.toList());
        iFileChunkService.removeByIds(idList);
        return idList;
    }

}

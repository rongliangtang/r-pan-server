package com.tangrl.pan.server.common.stream.consumer.search;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tangrl.pan.core.utils.IdUtil;
import com.tangrl.pan.server.common.stream.channel.PanChannels;
import com.tangrl.pan.server.common.stream.event.search.UserSearchEvent;
import com.tangrl.pan.server.modules.user.entity.RPanUserSearchHistory;
import com.tangrl.pan.server.modules.user.service.IUserSearchHistoryService;
import com.tangrl.pan.stream.core.AbstractConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 用户搜索事件监听器
 */
@Component
public class UserSearchEventConsumer extends AbstractConsumer {

    @Autowired
    private IUserSearchHistoryService iUserSearchHistoryService;

    /**
     * 监听用户搜索事件，将其保存到用户的搜索历史记录当中
     *
     * @param message
     */
    @StreamListener(PanChannels.USER_SEARCH_INPUT)
    public void saveSearchHistory(Message<UserSearchEvent> message) {
        if (isEmptyMessage(message)) {
            return;
        }
        printLog(message);
        UserSearchEvent event = message.getPayload();
        RPanUserSearchHistory record = new RPanUserSearchHistory();

        record.setId(IdUtil.get());
        record.setUserId(event.getUserId());
        record.setSearchContent(event.getKeyword());
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());

        try {
            iUserSearchHistoryService.save(record);
        } catch (DuplicateKeyException e) {
            UpdateWrapper updateWrapper = Wrappers.update();
            updateWrapper.eq("user_id", event.getUserId());
            updateWrapper.eq("search_content", event.getKeyword());
            updateWrapper.set("update_time", new Date());
            iUserSearchHistoryService.update(updateWrapper);
        }

    }

}

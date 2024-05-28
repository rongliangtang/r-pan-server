package com.tangrl.pan.server.common.stream.consumer;

import com.tangrl.pan.server.common.stream.channel.PanChannels;
import com.tangrl.pan.server.common.stream.event.TestEvent;
import com.tangrl.pan.stream.core.AbstractConsumer;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 测试消息消费者
 */
@Component
public class TestConsumer extends AbstractConsumer {

    /**
     * 消费测试消息
     *
     * @param message
     */
    @StreamListener(PanChannels.TEST_INPUT)
    public void consumeTestMessage(Message<TestEvent> message) {
        printLog(message);
    }

}

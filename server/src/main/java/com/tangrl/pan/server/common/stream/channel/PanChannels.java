package com.tangrl.pan.server.common.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * 事件通道定义类
 * Spring Cloud Stream会自动创建这些消息通道的bean，并将它们注册到Spring上下文中。
 * 由于AbstractStreamProducer中使用了@Autowired注解和Map<String, MessageChannel> channelMap，Spring会自动注入这些通道到channelMap中。
 */
public interface PanChannels {

    String TEST_INPUT = "testInput";
    String TEST_OUTPUT = "testOutput";

    String ERROR_LOG_INPUT = "errorLogInput";
    String ERROR_LOG_OUTPUT = "errorLogOutput";

    String DELETE_FILE_INPUT = "deleteFileInput";
    String DELETE_FILE_OUTPUT = "deleteFileOutput";

    String FILE_RESTORE_INPUT = "fileRestoreInput";
    String FILE_RESTORE_OUTPUT = "fileRestoreOutput";

    String PHYSICAL_DELETE_FILE_INPUT = "physicalDeleteFileInput";
    String PHYSICAL_DELETE_FILE_OUTPUT = "physicalDeleteFileOutput";

    String USER_SEARCH_INPUT = "userSearchInput";
    String USER_SEARCH_OUTPUT = "userSearchOutput";

    /**
     * 测试输入通道
     *
     * @return
     */
    @Input(TEST_INPUT)
    SubscribableChannel testInput();

    /**
     * 测试输出通道
     *
     * @return
     */
    @Output(TEST_OUTPUT)
    MessageChannel testOutput();

    @Input(PanChannels.ERROR_LOG_INPUT)
    SubscribableChannel errorLogInput();

    @Output(PanChannels.ERROR_LOG_OUTPUT)
    MessageChannel errorLogOutput();

    @Input(PanChannels.DELETE_FILE_INPUT)
    SubscribableChannel deleteFileInput();

    @Output(PanChannels.DELETE_FILE_OUTPUT)
    MessageChannel deleteFileOutput();

    @Input(PanChannels.FILE_RESTORE_INPUT)
    SubscribableChannel fileRestoreInput();

    @Output(PanChannels.FILE_RESTORE_OUTPUT)
    MessageChannel fileRestoreOutput();

    @Input(PanChannels.PHYSICAL_DELETE_FILE_INPUT)
    SubscribableChannel physicalDeleteFileInput();

    @Output(PanChannels.PHYSICAL_DELETE_FILE_OUTPUT)
    MessageChannel physicalDeleteFileOutput();

    @Input(PanChannels.USER_SEARCH_INPUT)
    SubscribableChannel userSearchInput();

    @Output(PanChannels.USER_SEARCH_OUTPUT)
    MessageChannel userSearchOutput();

}

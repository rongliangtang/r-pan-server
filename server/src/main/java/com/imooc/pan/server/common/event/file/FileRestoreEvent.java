//package com.imooc.pan.server.common.event.file;
//
//import lombok.EqualsAndHashCode;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//import org.springframework.context.ApplicationEvent;
//
//import java.util.List;
//
///**
// * 文件还原事件实体
// */
//@EqualsAndHashCode
//@ToString
//@Getter
//@Setter
//public class FileRestoreEvent extends ApplicationEvent {
//
//    /**
//     * 被成功还原的文件记录ID集合
//     */
//    private List<Long> fileIdList;
//
//    public FileRestoreEvent(Object source, List<Long> fileIdList) {
//        super(source);
//        this.fileIdList = fileIdList;
//    }
//
//}

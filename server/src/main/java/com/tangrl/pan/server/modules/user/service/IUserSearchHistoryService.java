package com.tangrl.pan.server.modules.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tangrl.pan.server.modules.user.context.QueryUserSearchHistoryContext;
import com.tangrl.pan.server.modules.user.entity.RPanUserSearchHistory;
import com.tangrl.pan.server.modules.user.vo.UserSearchHistoryVO;

import java.util.List;

/**
 * @description 针对表【r_pan_user_search_history(用户搜索历史表)】的数据库操作Service
 */
public interface IUserSearchHistoryService extends IService<RPanUserSearchHistory> {

    /**
     * 查询用户的搜索历史记录，默认十条
     *
     * @param context
     * @return
     */
    List<UserSearchHistoryVO> getUserSearchHistories(QueryUserSearchHistoryContext context);

}

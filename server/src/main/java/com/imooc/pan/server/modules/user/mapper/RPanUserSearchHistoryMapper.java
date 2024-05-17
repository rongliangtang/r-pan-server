package com.imooc.pan.server.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.pan.server.modules.user.context.QueryUserSearchHistoryContext;
import com.imooc.pan.server.modules.user.entity.RPanUserSearchHistory;
import com.imooc.pan.server.modules.user.vo.UserSearchHistoryVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author imooc
 * @description 针对表【r_pan_user_search_history(用户搜索历史表)】的数据库操作Mapper
 * @createDate 2022-11-09 18:34:37
 * @Entity com.imooc.pan.server.modules.user.entity.RPanUserSearchHistory
 */
public interface RPanUserSearchHistoryMapper extends BaseMapper<RPanUserSearchHistory> {

    /**
     * 查询用户的最近十条搜索历史记录
     *
     * @param context
     * @return
     */
    List<UserSearchHistoryVO> selectUserSearchHistories(@Param("param") QueryUserSearchHistoryContext context);

}





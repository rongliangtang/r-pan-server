package com.imooc.pan.server.modules.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.pan.server.modules.user.entity.RPanUser;
import org.apache.ibatis.annotations.Param;

/**
 * @author imooc
 * @description 针对表【r_pan_user(用户信息表)】的数据库操作Mapper
 * @createDate 2022-11-09 18:34:37
 * @Entity com.imooc.pan.server.modules.user.entity.RPanUser
 */
public interface RPanUserMapper extends BaseMapper<RPanUser> {

    /**
     * 通过用户名称查询用户设置的密保问题
     *
     * @param username
     * @return
     */
    String selectQuestionByUsername(@Param("username") String username);

}





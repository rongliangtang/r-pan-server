package com.tangrl.pan.server.modules.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tangrl.pan.server.modules.file.context.FileSearchContext;
import com.tangrl.pan.server.modules.file.context.QueryFileListContext;
import com.tangrl.pan.server.modules.file.entity.RPanUserFile;
import com.tangrl.pan.server.modules.file.vo.FileSearchResultVO;
import com.tangrl.pan.server.modules.file.vo.RPanUserFileVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description 针对表【r_pan_user_file(用户文件信息表)】的数据库操作Mapper
 * @Entity com.tangrl.pan.server.modules.file.entity.RPanUserFile
 */
public interface RPanUserFileMapper extends BaseMapper<RPanUserFile> {

    /**
     * 查询用户的文件列表
     *
     * @param context
     * @return
     */
    List<RPanUserFileVO> selectFileList(@Param("param") QueryFileListContext context);

    /**
     * 文件搜索
     *
     * @param context
     * @return
     */
    List<FileSearchResultVO> searchFile(@Param("param") FileSearchContext context);

}





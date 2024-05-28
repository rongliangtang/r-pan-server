package com.tangrl.pan.server.modules.share;

import cn.hutool.core.lang.Assert;
import com.google.common.collect.Lists;
import com.tangrl.pan.core.exception.RPanBusinessException;
import com.tangrl.pan.server.RPanServerLauncher;
import com.tangrl.pan.server.modules.file.context.CreateFolderContext;
import com.tangrl.pan.server.modules.file.service.IUserFileService;
import com.tangrl.pan.server.modules.file.vo.RPanUserFileVO;
import com.tangrl.pan.server.modules.share.context.*;
import com.tangrl.pan.server.modules.share.enums.ShareDayTypeEnum;
import com.tangrl.pan.server.modules.share.enums.ShareTypeEnum;
import com.tangrl.pan.server.modules.share.service.IShareService;
import com.tangrl.pan.server.modules.share.vo.RPanShareUrlListVO;
import com.tangrl.pan.server.modules.share.vo.RPanShareUrlVO;
import com.tangrl.pan.server.modules.share.vo.ShareDetailVO;
import com.tangrl.pan.server.modules.share.vo.ShareSimpleDetailVO;
import com.tangrl.pan.server.modules.user.context.UserLoginContext;
import com.tangrl.pan.server.modules.user.context.UserRegisterContext;
import com.tangrl.pan.server.modules.user.service.IUserService;
import com.tangrl.pan.server.modules.user.vo.UserInfoVO;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * 分享模块单元测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RPanServerLauncher.class)
@Transactional
public class ShareTest {

    @Autowired
    private IUserFileService iUserFileService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IShareService iShareService;

    /**
     * 创建分享链接成功
     */
    @Test
    public void createShareUrlSuccess() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name");

        Long fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        CreateShareUrlContext createShareUrlContext = new CreateShareUrlContext();
        createShareUrlContext.setShareName("share-1");
        createShareUrlContext.setShareDayType(ShareDayTypeEnum.SEVEN_DAYS_VALIDITY.getCode());
        createShareUrlContext.setShareType(ShareTypeEnum.NEED_SHARE_CODE.getCode());
        createShareUrlContext.setUserId(userId);
        createShareUrlContext.setShareFileIdList(Lists.newArrayList(fileId));
        RPanShareUrlVO vo = iShareService.create(createShareUrlContext);
        Assert.isTrue(Objects.nonNull(vo));
    }

    /**
     * 查询分享链接列表成功
     */
    @Test
    public void queryShareUrlListSuccess() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name");

        Long fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        CreateShareUrlContext createShareUrlContext = new CreateShareUrlContext();
        createShareUrlContext.setShareName("share-1");
        createShareUrlContext.setShareDayType(ShareDayTypeEnum.SEVEN_DAYS_VALIDITY.getCode());
        createShareUrlContext.setShareType(ShareTypeEnum.NEED_SHARE_CODE.getCode());
        createShareUrlContext.setUserId(userId);
        createShareUrlContext.setShareFileIdList(Lists.newArrayList(fileId));
        RPanShareUrlVO vo = iShareService.create(createShareUrlContext);
        Assert.isTrue(Objects.nonNull(vo));

        QueryShareListContext queryShareListContext = new QueryShareListContext();
        queryShareListContext.setUserId(userId);
        List<RPanShareUrlListVO> result = iShareService.getShares(queryShareListContext);
        Assert.notEmpty(result);
    }

    /**
     * 取消分享成功
     */
    @Test
    public void cancelShareSuccess() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name");

        Long fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        CreateShareUrlContext createShareUrlContext = new CreateShareUrlContext();
        createShareUrlContext.setShareName("share-1");
        createShareUrlContext.setShareDayType(ShareDayTypeEnum.SEVEN_DAYS_VALIDITY.getCode());
        createShareUrlContext.setShareType(ShareTypeEnum.NEED_SHARE_CODE.getCode());
        createShareUrlContext.setUserId(userId);
        createShareUrlContext.setShareFileIdList(Lists.newArrayList(fileId));
        RPanShareUrlVO vo = iShareService.create(createShareUrlContext);
        Assert.isTrue(Objects.nonNull(vo));

        QueryShareListContext queryShareListContext = new QueryShareListContext();
        queryShareListContext.setUserId(userId);
        List<RPanShareUrlListVO> result = iShareService.getShares(queryShareListContext);
        Assert.notEmpty(result);

        CancelShareContext cancelShareContext = new CancelShareContext();
        cancelShareContext.setUserId(userId);
        cancelShareContext.setShareIdList(Lists.newArrayList(vo.getShareId()));
        iShareService.cancelShare(cancelShareContext);

        result = iShareService.getShares(queryShareListContext);
        Assert.isTrue(CollectionUtils.isEmpty(result));
    }

    /**
     * 校验分享码成功
     */
    @Test
    public void checkShareCodeSuccess() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name");

        Long fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        CreateShareUrlContext createShareUrlContext = new CreateShareUrlContext();
        createShareUrlContext.setShareName("share-1");
        createShareUrlContext.setShareDayType(ShareDayTypeEnum.SEVEN_DAYS_VALIDITY.getCode());
        createShareUrlContext.setShareType(ShareTypeEnum.NEED_SHARE_CODE.getCode());
        createShareUrlContext.setUserId(userId);
        createShareUrlContext.setShareFileIdList(Lists.newArrayList(fileId));
        RPanShareUrlVO vo = iShareService.create(createShareUrlContext);
        Assert.isTrue(Objects.nonNull(vo));

        CheckShareCodeContext checkShareCodeContext = new CheckShareCodeContext();
        checkShareCodeContext.setShareId(vo.getShareId());
        checkShareCodeContext.setShareCode(vo.getShareCode());
        String token = iShareService.checkShareCode(checkShareCodeContext);
        Assert.notBlank(token);
    }

    /**
     * 校验分享码失败
     */
    @Test(expected = RPanBusinessException.class)
    public void checkShareCodeFail() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name");

        Long fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        CreateShareUrlContext createShareUrlContext = new CreateShareUrlContext();
        createShareUrlContext.setShareName("share-1");
        createShareUrlContext.setShareDayType(ShareDayTypeEnum.SEVEN_DAYS_VALIDITY.getCode());
        createShareUrlContext.setShareType(ShareTypeEnum.NEED_SHARE_CODE.getCode());
        createShareUrlContext.setUserId(userId);
        createShareUrlContext.setShareFileIdList(Lists.newArrayList(fileId));
        RPanShareUrlVO vo = iShareService.create(createShareUrlContext);
        Assert.isTrue(Objects.nonNull(vo));

        CheckShareCodeContext checkShareCodeContext = new CheckShareCodeContext();
        checkShareCodeContext.setShareId(vo.getShareId());
        checkShareCodeContext.setShareCode(vo.getShareCode() + "_change");
        String token = iShareService.checkShareCode(checkShareCodeContext);
        Assert.notBlank(token);
    }

    /**
     * 校验查询分享详情成功
     */
    @Test
    public void queryShareDetailSuccess() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name");

        Long fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        CreateShareUrlContext createShareUrlContext = new CreateShareUrlContext();
        createShareUrlContext.setShareName("share-1");
        createShareUrlContext.setShareDayType(ShareDayTypeEnum.SEVEN_DAYS_VALIDITY.getCode());
        createShareUrlContext.setShareType(ShareTypeEnum.NEED_SHARE_CODE.getCode());
        createShareUrlContext.setUserId(userId);
        createShareUrlContext.setShareFileIdList(Lists.newArrayList(fileId));
        RPanShareUrlVO vo = iShareService.create(createShareUrlContext);
        Assert.isTrue(Objects.nonNull(vo));

        QueryShareDetailContext queryShareDetailContext = new QueryShareDetailContext();
        queryShareDetailContext.setShareId(vo.getShareId());
        ShareDetailVO shareDetailVO = iShareService.detail(queryShareDetailContext);
        Assert.notNull(shareDetailVO);
    }

    /**
     * 校验查询分享简单详情成功
     */
    @Test
    public void queryShareSimpleDetailSuccess() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name");

        Long fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        CreateShareUrlContext createShareUrlContext = new CreateShareUrlContext();
        createShareUrlContext.setShareName("share-1");
        createShareUrlContext.setShareDayType(ShareDayTypeEnum.SEVEN_DAYS_VALIDITY.getCode());
        createShareUrlContext.setShareType(ShareTypeEnum.NEED_SHARE_CODE.getCode());
        createShareUrlContext.setUserId(userId);
        createShareUrlContext.setShareFileIdList(Lists.newArrayList(fileId));
        RPanShareUrlVO vo = iShareService.create(createShareUrlContext);
        Assert.isTrue(Objects.nonNull(vo));

        QueryShareSimpleDetailContext queryShareSimpleDetailContext = new QueryShareSimpleDetailContext();
        queryShareSimpleDetailContext.setShareId(vo.getShareId());
        ShareSimpleDetailVO shareSimpleDetailVO = iShareService.simpleDetail(queryShareSimpleDetailContext);
        Assert.notNull(shareSimpleDetailVO);
    }

    /**
     * 校验查询分享下一级文件列表成功
     */
    @Test
    public void queryShareFileListSuccess() {
        Long userId = register();
        UserInfoVO userInfoVO = info(userId);

        CreateFolderContext context = new CreateFolderContext();
        context.setParentId(userInfoVO.getRootFileId());
        context.setUserId(userId);
        context.setFolderName("folder-name");

        Long fileId = iUserFileService.createFolder(context);
        Assert.notNull(fileId);

        CreateShareUrlContext createShareUrlContext = new CreateShareUrlContext();
        createShareUrlContext.setShareName("share-1");
        createShareUrlContext.setShareDayType(ShareDayTypeEnum.SEVEN_DAYS_VALIDITY.getCode());
        createShareUrlContext.setShareType(ShareTypeEnum.NEED_SHARE_CODE.getCode());
        createShareUrlContext.setUserId(userId);
        createShareUrlContext.setShareFileIdList(Lists.newArrayList(userInfoVO.getRootFileId()));
        RPanShareUrlVO vo = iShareService.create(createShareUrlContext);
        Assert.isTrue(Objects.nonNull(vo));

        QueryChildFileListContext queryChildFileListContext = new QueryChildFileListContext();
        queryChildFileListContext.setShareId(vo.getShareId());
        queryChildFileListContext.setParentId(userInfoVO.getRootFileId());
        List<RPanUserFileVO> fileVOList = iShareService.fileList(queryChildFileListContext);
        Assert.notEmpty(fileVOList);
    }

//    @Test
//    public void init() {
//        CreateShareUrlContext context = new CreateShareUrlContext();
//        context.setUserId(1664949523080634368L);
//        context.setShareType(ShareTypeEnum.NEED_SHARE_CODE.getCode());
//        context.setShareDayType(ShareDayTypeEnum.PERMANENT_VALIDITY.getCode());
//        context.setShareFileIdList(Lists.newArrayList(1664950147318898688L));
//        for (int i = 0; i < 10000000; i++) {
//            context.setShareName("测试分享" + i);
//            iShareService.create(context);
//        }
//    }

    /************************************************private************************************************/

    /**
     * 用户注册
     *
     * @return 新用户的ID
     */
    private Long register() {
        UserRegisterContext context = createUserRegisterContext();
        Long register = iUserService.register(context);
        Assert.isTrue(register.longValue() > 0L);
        return register;
    }

    /**
     * 查询登录用户的基本信息
     *
     * @param userId
     * @return
     */
    private UserInfoVO info(Long userId) {
        UserInfoVO userInfoVO = iUserService.info(userId);
        Assert.notNull(userInfoVO);
        return userInfoVO;
    }

    private final static String USERNAME = "tangrl";
    private final static String PASSWORD = "123456789";
    private final static String QUESTION = "question";
    private final static String ANSWER = "answer";

    /**
     * 构建注册用户上下文信息
     *
     * @return
     */
    private UserRegisterContext createUserRegisterContext() {
        UserRegisterContext context = new UserRegisterContext();
        context.setUsername(USERNAME);
        context.setPassword(PASSWORD);
        context.setQuestion(QUESTION);
        context.setAnswer(ANSWER);
        return context;
    }

    /**
     * 构建用户登录上下文实体
     *
     * @return
     */
    private UserLoginContext createUserLoginContext() {
        UserLoginContext userLoginContext = new UserLoginContext();
        userLoginContext.setUsername(USERNAME);
        userLoginContext.setPassword(PASSWORD);
        return userLoginContext;
    }


}

package com.imooc.pan.server.common.aspect;

import com.imooc.pan.core.response.R;
import com.imooc.pan.core.response.ResponseCode;
import com.imooc.pan.core.utils.JwtUtil;
import com.imooc.pan.server.common.utils.ShareIdUtil;
import com.imooc.pan.server.modules.share.constants.ShareConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 统一的分享码校验切面逻辑实现类
 */
@Component
@Aspect
@Slf4j
public class ShareCodeAspect {

    /**
     * 登录认证参数名称
     */
    private static final String SHARE_CODE_AUTH_PARAM_NAME = "shareToken";

    /**
     * 请求头登录认证key
     */
    private static final String SHARE_CODE_AUTH_REQUEST_HEADER_NAME = "Share-Token";

    /**
     * 切点表达式
     */
    private final static String POINT_CUT = "@annotation(com.imooc.pan.server.common.annotation.NeedShareCode)";

    /**
     * 切点模版方法
     */
    @Pointcut(value = POINT_CUT)
    public void shareCodeAuth() {

    }

    /**
     * 切点的环绕增强逻辑
     * <p>
     * 1、需要判断需不需要校验分享token信息
     * 2、校验登录信息：
     * a、获取token 从请求头或者参数
     * b、解析token
     * c、解析的shareId存入线程上下文，供下游使用
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("shareCodeAuth()")
    public Object shareCodeAuthAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String requestURI = request.getRequestURI();
        log.info("成功拦截到请求，URI为：{}", requestURI);
        if (!checkAndSaveShareId(request)) {
            log.warn("成功拦截到请求，URI为：{}. 检测到用户的分享码失效，将跳转至分享码校验页面", requestURI);
            return R.fail(ResponseCode.ACCESS_DENIED);
        }
        log.info("成功拦截到请求，URI为：{}，请求通过", requestURI);
        return proceedingJoinPoint.proceed();
    }

    /**
     * 校验token并提取shareId
     *
     * @param request
     * @return
     */
    private boolean checkAndSaveShareId(HttpServletRequest request) {
        String shareToken = request.getHeader(SHARE_CODE_AUTH_REQUEST_HEADER_NAME);
        if (StringUtils.isBlank(shareToken)) {
            shareToken = request.getParameter(SHARE_CODE_AUTH_PARAM_NAME);
        }
        if (StringUtils.isBlank(shareToken)) {
            return false;
        }
        Object shareId = JwtUtil.analyzeToken(shareToken, ShareConstants.SHARE_ID);
        if (Objects.isNull(shareId)) {
            return false;
        }
        saveShareId(shareId);
        return true;
    }

    /**
     * 保存分享ID到线程上下文中
     *
     * @param shareId
     */
    private void saveShareId(Object shareId) {
        ShareIdUtil.set(Long.valueOf(String.valueOf(shareId)));
    }

}

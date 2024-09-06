package com.tangrl.pan.web.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * HttpLogFilter 类是一个过滤器，用于拦截 HTTP 请求并记录请求和响应的详细日志。
 * 继承了 Spring 的 OncePerRequestFilter，实现 doFilterInternal，确保每个请求只调用一次过滤器逻辑。
 * 否则在需要在 doFilter 方法中手动处理是否应该过滤的逻辑，并且需要确保过滤器只执行一次。这增加了代码的复杂性和出错的风险。
 */

//@WebFilter(filterName = "httpLogFilter")
@Slf4j
@Order(Integer.MAX_VALUE)
public class HttpLogFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // HttpServletRequest 和 HttpServletResponse 的输入流和输出流在读取一次后会被消费，无法再次读取。
        // 这意味着如果在日志记录时读取了内容，应用程序后续的处理将无法再读取这些内容。
        // 所以使用 ContentCachingRequestWrapper 和 ContentCachingResponseWrapper 来缓存请求和响应的内容。

        // StopWatch 是 Apache Commons Lang 库提供的一个简单的计时器类，用于测量代码执行的时间。
        // 它非常适用于需要记录方法执行时间或代码块执行时间的场景。
        StopWatch stopWatch = StopWatch.createStarted();
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(requestWrapper, responseWrapper);
        HttpLogEntity httpLogEntity = HttpLogEntityBuilder.build(requestWrapper, responseWrapper, stopWatch);
        httpLogEntity.print();
        responseWrapper.copyBodyToResponse();
    }

}

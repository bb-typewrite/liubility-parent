package org.liubility.commons.interceptor;

import org.liubility.commons.controller.BaseController;
import org.liubility.commons.holder.BaseContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import java.io.UnsupportedEncodingException;

import static org.liubility.commons.constant.CommonConstants.*;

/**
 * 填充 BaseContextHolder 本地线程变量拦截器
 *
 * @author llk
 * @date 2019-11-10 03:54
 */
public class ContextHolderInterceptor implements WebRequestInterceptor {

    @Override
    public void preHandle(WebRequest request) throws UnsupportedEncodingException {
        // --- 用户信息
        String userId = request.getHeader(USER_ID);
        String username = request.getHeader(USERNAME);
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        BaseContextHolder.set(USER_ID, userId);
        BaseContextHolder.set(USERNAME, username);
        BaseContextHolder.set(HttpHeaders.AUTHORIZATION, token);

        //页码信息
        String pageNum = request.getHeader(PAGE_NUM);
        String pageSize = request.getHeader(PAGE_SIZE);
        if (pageNum == null) {
            pageNum = "1";
        }
        if (pageSize == null) {
            pageSize = "10";
        }
        BaseContextHolder.set(PAGE_NUM, Long.valueOf(pageNum));
        BaseContextHolder.set(PAGE_SIZE, Long.valueOf(pageSize));

    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {

    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        BaseContextHolder.clear();
    }
}

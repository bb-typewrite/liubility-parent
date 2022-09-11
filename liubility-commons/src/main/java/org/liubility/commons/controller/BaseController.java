package org.liubility.commons.controller;

import org.liubility.commons.constant.CommonConstants;
import org.liubility.commons.holder.BaseContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author JDragon
 * @Date 2021.02.19 下午 5:52
 * @Email 1061917196@qq.com
 * @Des:
 */
public class BaseController {
    public String getToken() {
        return BaseContextHolder.getToken();
    }

    public String getUsername() {
        return BaseContextHolder.getUsername();
    }

    public long getUserId() {
        return BaseContextHolder.getUserId();
    }

    public static Long getPageNum() {
        return BaseContextHolder.getPageNum();
    }


    public static Long getPageSize() {
        return BaseContextHolder.getPageSize();
    }
}

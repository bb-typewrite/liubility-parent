package org.liubility.commons.controller;

import org.liubility.commons.holder.BaseContextHolder;

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

    public String getIP(){return BaseContextHolder.getIP();}

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

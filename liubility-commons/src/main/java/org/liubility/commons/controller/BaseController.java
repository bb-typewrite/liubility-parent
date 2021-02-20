package org.liubility.commons.controller;

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

    @Autowired
    private HttpServletRequest request;


    public String getToken(){
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    public String getUsername(){
        return request.getHeader("username");
    }
    public int getUserId(){
        return Integer.parseInt(request.getHeader("userId"));
    }
}

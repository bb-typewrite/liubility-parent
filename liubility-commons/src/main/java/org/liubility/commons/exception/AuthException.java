package org.liubility.commons.exception;

/**
 * @Author JDragon
 * @Date 2021.02.17 下午 12:16
 * @Email 1061917196@qq.com
 * @Des:
 */
public class AuthException extends RuntimeException {
    public AuthException(String message){
        super(message);
    }
}

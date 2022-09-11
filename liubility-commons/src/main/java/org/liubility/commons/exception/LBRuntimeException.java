package org.liubility.commons.exception;

/**
 * @Author JDragon
 * @Date 2021.02.16 下午 5:32
 * @Email 1061917196@qq.com
 * @Des:
 */

public class LBRuntimeException extends RuntimeException {
    public LBRuntimeException(String message) {
        super(message);
    }

    public LBRuntimeException(String message, Throwable e) {
        super(message, e);
    }
}

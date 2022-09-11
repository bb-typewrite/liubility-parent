package org.liubility.commons.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.liubility.commons.http.response.normal.ICode;
import org.liubility.commons.http.response.normal.ResultCode;

/**
 * @Author JDragon
 * @Date 2021.02.16 下午 5:32
 * @Email 1061917196@qq.com
 * @Des:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LBRuntimeException extends RuntimeException {

    private ICode code = ResultCode.SYSTEM_ERROR;

    private Object data;

    public LBRuntimeException(String message) {
        super(message);
    }

    public LBRuntimeException(ICode baseCode) {
        super(baseCode.getMessage());
        this.code = baseCode;
    }

    public LBRuntimeException(String message, Throwable e) {
        super(message, e);
    }

    public LBRuntimeException(ICode baseCode, Throwable e) {
        super(baseCode.getMessage(), e);
        this.code = baseCode;
    }

    public LBRuntimeException(ICode baseCode, String message, Throwable e) {
        super(message, e);
        this.code = baseCode;
    }
}

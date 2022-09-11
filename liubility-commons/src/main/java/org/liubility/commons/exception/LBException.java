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
public class LBException extends Exception {

    private ICode code = ResultCode.SYSTEM_ERROR;

    private Object data;

    public LBException(String message) {
        super(message);
    }

    public LBException(ICode baseCode) {
        super(baseCode.getMessage());
        this.code = baseCode;
    }

    public LBException(String message, Throwable e) {
        super(message, e);
    }

    public LBException(ICode baseCode, Throwable e) {
        super(baseCode.getMessage(), e);
        this.code = baseCode;
    }

    public LBException(ICode baseCode, String message, Throwable e) {
        super(message, e);
        this.code = baseCode;
    }
}

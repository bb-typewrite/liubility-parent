package org.liubility.commons.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: Jdragon
 * @email: 1061917196@qq.com
 * @Date: 2020.08.01 10:32
 * @Description:
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpException extends RuntimeException{
    private String message;

    public HttpException(Throwable e){
        super(e);
    }
}

package org.liubility.commons.util;

import cn.hutool.crypto.SecureUtil;
import org.liubility.commons.dto.account.NumDto;

/**
 * @Author JDragon
 * @Date 2021.04.29 下午 10:59
 * @Email 1061917196@qq.com
 * @Des:
 */
public class GenerateUtils {
    public static String generateNumKey(NumDto numDto, String secret) {
        int num = numDto.getNum();
        int rightNum = numDto.getRightNum();
        int misNum = numDto.getMisNum();
        int dateNum = numDto.getDateNum();
        return SecureUtil.md5(num + rightNum + misNum + dateNum + secret);
    }
}

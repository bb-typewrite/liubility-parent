package org.liubility.commons.zFeign;

/**
 * @Author: Jdragon
 * @email: 1061917196@qq.com
 * @Date: 2020.11.23 00:28
 * @Description:
 */
public class HttpScanMatch implements ScanMatch {
    @Override
    public boolean match(Class<?> clazz) {
        return clazz.isAnnotationPresent(ZFeign.class);
    }
}

package org.liubility.commons.interceptor.translation;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 * <p>create time: 2021/12/26 23:36 </p>
 *
 * @author : Jdragon
 */
public interface IDictEnum<K, V> {

    K getCode();

    V getValue();
}

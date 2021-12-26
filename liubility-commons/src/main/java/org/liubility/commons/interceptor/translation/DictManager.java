package org.liubility.commons.interceptor.translation;

import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 * <p>create time: 2021/12/26 23:51 </p>
 *
 * @author : Jdragon
 */
public class DictManager {

    private static final Map<String, Map<?, ?>> dictMap = new HashMap<>();

    public static <K, V> void register(String dictKey, Map<K, V> dict) {
        dictMap.put(dictKey, dict);
    }

    @SuppressWarnings("unchecked")
    public static <K, V> V getDictValue(String dictKey, K code) {
        Map<?, ?> map = dictMap.get(dictKey);
        if (map == null) {
            return null;
        }
        Object o = map.get(code);
        if (o == null) {
            return null;
        }
        return (V) o;
    }

}

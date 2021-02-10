package org.liubility.commons.http.response.table;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author: Jdragon
 * @email: 1061917196@qq.com
 * @Date: 2020.06.24 14:29
 * @Description:
 */
public class TableUtils {
    public static Type getTypeArgument(Class<? extends TableRef> aClass) {
        Type[] typeArguments = toParameterizedType(aClass).getActualTypeArguments();
        return typeArguments[0];
    }
    public static ParameterizedType toParameterizedType(Type type) {
        if (type instanceof ParameterizedType) {
            return (ParameterizedType) type;
        } else if (type instanceof Class) {
            return toParameterizedType(((Class<?>) type).getGenericSuperclass());
        }
        return null;
    }
}
package org.liubility.commons.interceptor.translation;

import cn.hutool.core.util.ClassUtil;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.*;

/**
 * 字典翻译拦截器
 *
 * @author llk
 * @date 2019-11-11 19:03
 */
@Intercepts(
        @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = Statement.class)
)
public class TranslationInterceptor implements Interceptor {
    @Override
    @SuppressWarnings("unchecked")
    public Object intercept(Invocation invocation) throws Throwable {
        List<Object> objects = (List<Object>) invocation.proceed();
        return translate(objects);
    }

    private Object translate(List<Object> objects) throws NoSuchFieldException, IllegalAccessException {
        if (objects != null && objects.size() > 0 && Objects.nonNull(objects.get(0))) {
            Class<?> aClass = objects.get(0).getClass();
            Translation translation = aClass.getAnnotation(Translation.class);
            if (Objects.nonNull(translation)) {
                for (Object object : objects) {
                    Field[] fields = ClassUtil.getDeclaredFields(aClass);
                    for (Field field : fields) {
                        Translate translate = field.getAnnotation(Translate.class);
                        if (translate != null) {
                            String byFieldStr = translate.byField();
                            String dictCode = translate.dict().getSimpleName();
                            Field byField = aClass.getDeclaredField(byFieldStr);
                            byField.setAccessible(true);
                            Object o = byField.get(object);
                            Object dictValue = DictManager.getDictValue(dictCode, o);
                            if (dictValue == null) {
                                continue;
                            }
                            field.setAccessible(true);
                            field.set(object, dictValue);
                        }
                    }
                }
            }
        }
        return objects;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public <K, V> void register(Class<? extends IDictEnum<K, V>> dictEnum) {
        DictManager.register(dictEnum.getSimpleName(), getMap(dictEnum));
    }

    private static <K, V> Map<K, V> getMap(Class<? extends IDictEnum<K, V>> dictEnumClass) {
        Map<K, V> map = new HashMap<>();
        IDictEnum<K, V>[] enumConstants = dictEnumClass.getEnumConstants();
        for (IDictEnum<K, V> dictEnum : enumConstants) {
            K code = dictEnum.getCode();
            V value = dictEnum.getValue();
            map.put(code, value);
        }
        return map;
    }
}

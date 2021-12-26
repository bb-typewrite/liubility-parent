package org.liubility.commons.interceptor.translation;

import java.lang.annotation.*;

/**
 * 字段翻译类
 *
 * @author llk
 * @date 2019-11-10 21:42
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(value = ElementType.FIELD)
public @interface Translate {

    String value() default "";

    /**
     * 字典编码
     */
    Class<? extends IDictEnum<?, ?>> dict();

    /**
     * 根据哪个字段翻译
     */
    String byField();
}

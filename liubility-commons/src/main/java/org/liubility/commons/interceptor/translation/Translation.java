package org.liubility.commons.interceptor.translation;

import java.lang.annotation.*;

/**
 * 需要翻译的实体类标识
 * @author llk
 * @date 2019-11-10 21:42
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(value = ElementType.TYPE)
public @interface Translation {

    String value() default "";
}

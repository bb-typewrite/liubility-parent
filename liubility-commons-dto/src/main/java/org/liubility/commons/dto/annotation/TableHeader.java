package org.liubility.commons.dto.annotation;

import java.lang.annotation.*;

/**
 * @Author: Jdragon
 * @email: 1061917196@qq.com
 * @Date: 2020.06.23 22:32
 * @Description:
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableHeader {

    String value(); // 字段中文名称

    String component() default ""; // 组件类型

    int width() default 100;

    boolean merge() default false;
}
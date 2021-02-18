package org.liubility.commons.zFeign;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import java.util.List;

/**
 * @Author: Jdragon
 * @email: 1061917196@qq.com
 * @Date: 2020.11.23 00:11
 * @Description:
 */
public class HttpDyanScannerRegistrar implements ImportBeanDefinitionRegistrar{
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes mapperScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(ZFeignScan.class.getName()));
        if (mapperScanAttrs != null) {
            Scanner scanner = new Scanner(mapperScanAttrs.getStringArray("value"));
            scanner.setScanMatch(new HttpScanMatch());
            List<Class<?>> classes = scanner.doScan();
            for (Class<?> beanClazz : classes) {
                ZFeign zFeign = beanClazz.getAnnotation(ZFeign.class);
                String baseUrl = zFeign.baseUrl();
                String basePath = zFeign.basePath();
                String url = baseUrl + basePath;
                String[] depth = zFeign.depth();

                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(beanClazz);
                GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
                definition.getPropertyValues().add("interfaceClass", beanClazz);
                definition.getPropertyValues().add("baseUrl", url);
                definition.getPropertyValues().add("depth", depth);
                definition.setBeanClass(DefineFactory.class);
                definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
                beanDefinitionRegistry.registerBeanDefinition(beanClazz.getSimpleName(), definition);
            }
        }
    }
}

package org.liubility.commons.zFeign;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: Jdragon
 * @email: 1061917196@qq.com
 * @Date: 2020.11.22 22:55
 * @Description:
 */
@Data
public class DefineFactory<T> implements FactoryBean<T> {

    private Class<T> interfaceClass;

    /**
     * 在bean注册时设置
     */
    private String baseUrl;

    private String[] depth;

    @Override
    public T getObject() throws Exception {
        return new DynaProxyHttp(baseUrl,depth).bindInterface(interfaceClass);
    }

    @Override
    public Class<?> getObjectType() {
        return interfaceClass;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

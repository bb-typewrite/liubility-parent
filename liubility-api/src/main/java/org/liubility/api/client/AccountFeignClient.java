package org.liubility.api.client;

import org.liubility.api.AccountServiceProvider;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Author JDragon
 * @Date 2021.02.11 上午 12:37
 * @Email 1061917196@qq.com
 * @Des:
 */

@FeignClient(value = "account-service", path = "user")
public interface AccountFeignClient extends AccountServiceProvider {

}

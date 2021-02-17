package org.liubility.api.client;

import org.liubility.api.AccountServiceProvider;
import org.liubility.commons.dto.account.AccountDto;
import org.liubility.commons.http.response.normal.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author JDragon
 * @Date 2021.02.11 上午 12:37
 * @Email 1061917196@qq.com
 * @Des:
 */

@FeignClient(value = "security-service", path = "/account")
public interface AccountFeignClient extends AccountServiceProvider {
    @Override
    @GetMapping("/getLoginAccountByName")
    AccountDto getLoginAccountByName(@RequestParam("username") String username);

    @Override
    @PostMapping("/login")
    public Result<String> login(@RequestBody AccountDto accountDto);

}

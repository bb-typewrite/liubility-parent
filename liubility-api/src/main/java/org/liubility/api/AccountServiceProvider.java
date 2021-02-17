package org.liubility.api;

import org.liubility.commons.dto.account.AccountDto;
import org.liubility.commons.http.response.normal.Result;

/**
 * @Author JDragon
 * @Date 2021.02.11 上午 12:36
 * @Email 1061917196@qq.com
 * @Des:
 */
public interface AccountServiceProvider {

    public Result<String> login(AccountDto accountDto);

    public AccountDto getLoginAccountByName(String username);
}

package org.liubility.api;

import org.liubility.commons.dto.account.AccountDto;

/**
 * @Author JDragon
 * @Date 2021.02.11 上午 12:36
 * @Email 1061917196@qq.com
 * @Des:
 */
public interface AccountServiceProvider {

    public AccountDto getLoginAccountByName(String username);
}

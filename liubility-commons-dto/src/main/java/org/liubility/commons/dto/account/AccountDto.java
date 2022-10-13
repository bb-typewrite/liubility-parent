package org.liubility.commons.dto.account;

/**
 * @Author JDragon
 * @Date 2021.02.11 上午 12:42
 * @Email 1061917196@qq.com
 * @Des:
 */

public class AccountDto {

    private Long id;

    private String username;

    private String password;

    private String ip;

    public AccountDto(){}

    public AccountDto(Long id,String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

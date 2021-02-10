package org.liubility.commons.dto.account;

/**
 * @Author JDragon
 * @Date 2021.02.11 上午 12:42
 * @Email 1061917196@qq.com
 * @Des:
 */

public class UserDto {

    private String username;

    private String password;

    public UserDto(){}

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
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
}

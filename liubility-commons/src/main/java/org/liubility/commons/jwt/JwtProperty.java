package org.liubility.commons.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @Author JDragon
 * @Date 2021.02.16 下午 7:21
 * @Email 1061917196@qq.com
 * @Des:
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperty {

    private String expirationTime;

    private String secretKey;

    private String header;
}

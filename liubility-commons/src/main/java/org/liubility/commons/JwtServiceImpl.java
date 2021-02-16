package org.liubility.commons;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.liubility.commons.dto.account.AccountDto;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jdragon
 * @Class: JwtServiceImpl
 * @Date: 2021.02.08 下午 12:27
 * @Description:
 */
@Slf4j
@Component
public class JwtServiceImpl {
    //私钥
//    @Value("${jwt.secretKey}")
    private String SECRET_KEY = "haofangErpJwtSecretKey";

    // 过期时间 毫秒,设置默认1天的时间过期
//    @Value("${jwt.expirationTime}")
    private long EXPIRATION_TIME = 16000000;

    /**
     * 生成令牌
     *
     * @param userDetails 用户
     * @return 令牌
     */
    public String generateToken(AccountDto userDetails) {
        log.debug("使用密钥:" + SECRET_KEY);
        log.debug("过期时间:" + EXPIRATION_TIME);
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(Claims.SUBJECT, userDetails.getUsername());
        claims.put(Claims.ISSUED_AT, new Date());
        return generateToken(claims);
    }

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis()+ EXPIRATION_TIME);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        Claims claims = getClaimsFromToken(token);
        claims.put(Claims.ISSUED_AT, new Date());
        refreshedToken = generateToken(claims);
        return refreshedToken;
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username = null;
        try {
            Claims claims = getClaimsFromToken(token);
            log.debug("claims = " + claims.toString());
            username = claims.getSubject();
        } catch (Exception e) {
            log.debug("e = " + e.getMessage());
        }
        return username;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

//    /**
//     * 验证令牌
//     *
//     * @param token       令牌
//     * @param userDetails 用户
//     * @return 是否有效
//     */
//    public Boolean validateToken(String token, UserDetails userDetails) {
//        AuthUserEntity user = (AuthUserEntity) userDetails;
//        String username = getUsernameFromToken(token);
//        return (username.equals(user.getUsername()) && !isTokenExpired(token));
//    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否有效
     */
    public Boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }
}

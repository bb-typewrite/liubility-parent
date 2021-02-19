package org.liubility.commons.jwt;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.liubility.commons.dto.account.AccountDto;
import org.liubility.commons.json.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private JwtProperty jwtProperty;

    /**
     * 生成令牌
     *
     * @param userDetails 用户
     * @return 令牌
     */
    public String generateToken(AccountDto userDetails) {
        log.debug("使用密钥:" + jwtProperty.getSecretKey());
        log.debug("过期时间:" + jwtProperty.getExpirationTime());
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(Claims.SUBJECT, JSON.toJSONString(userDetails));
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
        Date expirationDate = new Date(System.currentTimeMillis() + jwtProperty.getExpirationTime());
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, jwtProperty.getSecretKey()).compact();
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
    public <T> T getSubjectFromToken(String token, Class<T> c) {
        String subjectStr = null;
        T subject = null;
        try {
            Claims claims = getClaimsFromToken(token);
            log.debug("claims = " + claims.toString());
            subjectStr = claims.getSubject();
            if (subjectStr != null) {
                subject = JSON.parseObject(JSON.toJSONString(token), c);
            }
        } catch (Exception e) {
            log.debug("e = " + e.getMessage());
        }
        return subject;
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtProperty.getSecretKey()).parseClaimsJws(token).getBody();
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

package com.tangrl.pan.core.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Jwt 工具类
 * 提供了生成和解析 JSON Web Token (JWT) 的功能。
 * JWT 是一种用于在各方之间安全地传输信息的紧凑、自包含的方式。
 */
public class JwtUtil {

    public static final Long TWO_LONG = 2L;

    /**
     * 秘钥
     */
    private final static String JWT_PRIVATE_KEY = "0CB16040A41140E48F2F93A7BE222C46";

    /**
     * 刷新时间
     */
    private final static String RENEWAL_TIME = "RENEWAL_TIME";

    /**
     * 生成token
     * 生成一个 JWT，包含主体信息、声明（claims）和过期时间。
     * @param subject
     * @param claimKey
     * @param claimValue
     * @param expire
     * @return
     */
    public static String generateToken(String subject, String claimKey, Object claimValue, Long expire) {
        String token = Jwts.builder()
                .setSubject(subject)
                .claim(claimKey, claimValue)
                .claim(RENEWAL_TIME, new Date(System.currentTimeMillis() + expire / TWO_LONG))
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS256, JWT_PRIVATE_KEY)
                .compact();
        return token;
    }

    /**
     * 解析token
     * 解析 JWT 并返回指定的声明值
     * @param token
     * @return
     */
    public static Object analyzeToken(String token, String claimKey) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JWT_PRIVATE_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.get(claimKey);
        } catch (Exception e) {
            // 如果 token 无效或过期，解析过程会抛出异常
            // 捕获异常并返回 null
            return null;
        }
    }

}

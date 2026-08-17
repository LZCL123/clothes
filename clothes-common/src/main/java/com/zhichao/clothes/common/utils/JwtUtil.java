package com.zhichao.clothes.common.utils;

import com.zhichao.clothes.common.exception.LeaseException;
import com.zhichao.clothes.common.result.ResultCodeEnum;
import com.zhichao.clothes.model.entity.UserInfo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    /**
     * JWT 签名密钥,通过环境变量 JWT_SECRET 注入;
     * 未配置时使用默认值(仅用于本地开发,生产环境务必配置)。
     */
    private static final SecretKey secretKey = Keys.hmacShaKeyFor(
            System.getenv().getOrDefault("JWT_SECRET",
                    "dev-only-secret-key-change-in-production-0123456789").getBytes());

    public static String createToken(Long userId, String username) {

        String jwt = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .setSubject("LOGIN_USER")
                .claim("userId", userId)
                .claim("username", username)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        return jwt;
    }

    public static String createToken(UserInfo userInfo) {

        String jwt = Jwts.builder()
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .setSubject("LOGIN_USER")
                .claim("user", userInfo)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        return jwt;
    }

    public static Claims parseToken(String token) {
        if (token == null) {
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return claimsJws.getBody();
        }catch (ExpiredJwtException e){
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        }catch (JwtException e){
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }
}

package com.yibackend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JWTUtil {

    public static String createJWT(String secretKey, long ttlMillis, Map<String,Object> claims) {


        long expMillis = System.currentTimeMillis() + ttlMillis;

        Date exp = new Date(expMillis);

        JwtBuilder builder = Jwts.builder().
                setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(exp);

        return builder.compact();

    }


    public static Claims parseJWT(String jwt,String secretKey){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }

}

package com.itheima;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//@SpringBootTest
class TliasWebManagementApplicationTests {

    @Test
    public void uuid() {
        for (int i = 0; i < 1000; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);
        }
    }

    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", "1");
        claims.put("username", "ikun");
        String jwt=Jwts.builder()

                .signWith(SignatureAlgorithm.HS256, "myikun")//签名算法
                .setClaims(claims)//设置JWT的载荷部分
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 10000))//jwt令牌有效期为一小时
                .compact();
        System.out.println(jwt);
    }


    //解析jwt
//    @Test
//    public void testParseJwt() {
//
//        Claims claims=Jwts.parser()
//                .setSigningKey("myikun")
//                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6IjEiLCJleHAiOjE3MDU2NTgxMjQsInVzZXJuYW1lIjoiaWt1biJ9.k5RqAeCUgcO7wY7idDnRz-nST5-RKS0mjzdaSRwfCh8")
//                .getBody();
//        System.out.println(claims);

    }







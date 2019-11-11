package com.example.utils;


import com.alibaba.fastjson.JSONObject;

import com.example.entity.data.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/***
 *
 * JWT token
 */
public class TokenUtils {

    public static final String SECRET = "devPortal-token";

    public static final long TOKEN_TIMEOUT_APP = 7 * 24 * 60 * 60 * 1000;

    public static final long TOKEN_TIMEOUT_PC = 7 * 60 * 60 * 1000;

    private Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    /**
     * 生成Token
     *
     * @param id        编号
     * @param issuer    该JWT的签发者，是否使用是可选的
     * @param subject   该JWT所面向的用户，是否使用是可选的；
     * @param ttlMillis 超时时间，单位:毫秒
     * @return token String
     */
    public static String createJwtToken(String id, String issuer, String subject, long ttlMillis) {

        // 签名算法 ，将对token进行签名
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // 生成签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        // 通过秘钥签名JWT
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

    }

    // Sample method to validate and read the JWT
    public static Claims parseJWT(String jwt) {
        // This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 生成token
     *
     * @param user
     * @return
     * @throws Exception
     */
    public static String createJwtToken(User user) throws Exception {
        String phoneNum = user.getPhoneNum();

        if (phoneNum == null || "".equals(phoneNum)) {
            throw new Exception("'User'对象'phoneNum'属性不能为空");
        }

        long userId = user.getUserId() == null ? 0 : user.getUserId();
        String userName = String.valueOf(user.getUserName() == null ? "" : user.getUserName());
        String identityCard=String.valueOf(user.getIdentityCard()==null ? "" :user.getIdentityCard());
        long ttlMillis = System.currentTimeMillis();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", userName);
        jsonObject.put("identityCard", identityCard);
        String subject=jsonObject.toJSONString();

        return createJwtToken(String.valueOf(userId), phoneNum,subject, ttlMillis);
    }

    /**
     * 生成token,支持自定义超时时间
     *
     * @param user
     * @param timeout 参数小于等于0时默认为PC端的超时时间
     * @return
     * @throws Exception
     */
    public static String createJwtToken(User user, long timeout) throws Exception {
        String phoneNum = user.getPhoneNum();

        if (phoneNum == null || "".equals(phoneNum)) {
            throw new Exception("'User'对象'phoneNum'属性不能为空");
        }

        long userId = user.getUserId() == null ? 0 : user.getUserId();
        String userName = String.valueOf(user.getUserName() == null ? "" : user.getUserName());
        String identityCard=String.valueOf(user.getIdentityCard()==null ? "" :user.getIdentityCard());
        long ttlMillis = timeout <= 0 ? TOKEN_TIMEOUT_PC : timeout;

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", userName);
        jsonObject.put("identityCard", identityCard);

        String subject=jsonObject.toJSONString();
        return createJwtToken(String.valueOf(userId), phoneNum, subject, ttlMillis);
    }

    /**
     * 生成token，根据请求的平台设置超时时间
     *
     * @param user
     * @param user_Agent
     * @return
     * @throws Exception
     */
    public static String createJwtToken(User user, String user_Agent) throws  Exception {
        if (null == user_Agent)
            throw new Exception("参数异常，请检查入参'User-Agent'");
        user_Agent = user_Agent.toLowerCase();

        String phoneNum = user.getPhoneNum();

        if (phoneNum == null || "".equals(phoneNum)) {
            throw new Exception("'User'对象'phoneNum'属性不能为空");
        }

        long userId = user.getUserId() == null ? 0 : user.getUserId();
        String userName = String.valueOf(user.getUserName() == null ? "" : user.getUserName());
        String identityCard=String.valueOf(user.getIdentityCard()==null ? "" :user.getIdentityCard());
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userName", userName);
        jsonObject.put("identityCard", identityCard);

        String subject=jsonObject.toJSONString();

        // TODO 只识别android、ios,其他操作系统如windowPhone等其暂未识别
        if (user_Agent.contains("android") || user_Agent.contains("iphone") || user_Agent.contains("ipad")) {
            // 生成移动端Token,有效时间：7天
            return createJwtToken(String.valueOf(userId), phoneNum, subject, TOKEN_TIMEOUT_APP);
        } else {
            // 生成PC端Token，有效时间：7小时
            return createJwtToken(String.valueOf(userId), phoneNum, subject, TOKEN_TIMEOUT_PC);
        }
    }
}

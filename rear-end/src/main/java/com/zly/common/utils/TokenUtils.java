package com.zly.common.utils;

import com.alibaba.fastjson.JSON;
import com.zly.common.dto.TokenHeader;
import com.zly.common.dto.TokenPayload;
import com.zly.common.vo.UserVo;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public class TokenUtils {

    public static String getToken(UserVo userVo){

        TokenHeader tokenHeader = new TokenHeader();
        tokenHeader.setType("JWT");
        tokenHeader.setAlg("RSA");
        String s = JSON.toJSONString(tokenHeader);
        String header = Base64Utils.encode(s);

        TokenPayload tokenPayload = new TokenPayload();
        tokenPayload.setUserId(userVo.getId());
        tokenPayload.setOid(userVo.getOid());
        tokenPayload.setIfManage(userVo.getIfManage());
        tokenPayload.setIat(CalendarUtils.now());
        tokenPayload.setNbf(CalendarUtils.now());
        tokenPayload.setUserName(userVo.getUserName());
        String payload = Base64Utils.encode(JSON.toJSONString(tokenPayload));

        String payload_md5 = MD5Utils.getMD5_50(payload);

        Map<String, String> keyMap = RSAUtil.createKeys(1024);
        String  publicKey = keyMap.get("publicKey");
        //String  privateKey = keyMap.get("privateKey");

        String encodedData = null;
        try {
            encodedData = RSAUtil.publicEncrypt(payload_md5, RSAUtil.getPublicKey(publicKey));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }

        return header+"."+payload+"."+encodedData;
    }

    public static TokenPayload getTokenPayload(String token){

        String[] split = token.split("\\.");
        String decoder = Base64Utils.decoder(split[1]);
        TokenPayload tokenPayload = JSON.parseObject(decoder, TokenPayload.class);

        return tokenPayload;
    }
}

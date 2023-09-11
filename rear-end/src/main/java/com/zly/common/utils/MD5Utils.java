package com.zly.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * message digest Algorithm 5（MD5） 信息摘要算法
 * 可以产生出一个128位元（16位/32位元组）的散列值，用
 * 于确保信息传输完整一致
 * 此算法不可逆
 */
public class MD5Utils {

    /**
     *  一遍生成的hash码，很容易就被破解,所以多来几遍
     */
    public static String getMD5_50(String context){

        for (int i = 0; i < 50; i++) {
            context = getMD5(context);
        }
        return context;
    }

    public static String getMD5(String context){

        try{
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(context.getBytes());
            return getHashString(messageDigest);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * toHexString方法返回一个无符号整数十六进制的字符串
     */
    private static String getHashString(MessageDigest messageDigest) {

        StringBuffer builder = new StringBuffer();
        for (byte b:messageDigest.digest()) {
            // b(二进制) 右移四位 然后 与上 0xf (十六进制中的十五)
            //若两个二进制数长度不同,高位被抛掉(丢失)
            //如 10000
            //  &   10   那么100将丢失
            builder.append(Integer.toHexString(b>>4 & 0xf));
            builder.append(Integer.toHexString(b & 0xf));
        }
        return  builder.toString();
    }
}



package com.cxxy.practice.util;

import java.security.MessageDigest;

/**
 * Author:liuhui
 * Description:
 * Date: 下午4:31 2018/1/4
 */
public class EncryptUtil {

    /**
     * MD5加密
     *
     * @param data
     * @return
     */
    public static String MD5(String data) {

        StringBuffer sb = new StringBuffer(32);
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("utf-8"));

            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
            }
        } catch (Exception e) {
            return null;
        }
        return sb.toString().toLowerCase();
    }

    public static void main(String[] args) throws Exception {
        String word = "123456";

        System.out.println(MD5(word));
    }
}

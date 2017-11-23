package com.cxxy.shop.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;
import java.util.Map;

@Slf4j
public final class AESUtil {

    private AESUtil() {
        throw new Error("工具类不可以实例化");
    }

    /**
     * 密钥算法
     */
    public static final String KEY_ALGORITHM = "AES";

    /**
     * 密钥长度为128位、192位或256位
     */
    public static final int KEY_SIZE = 128;

    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    private static final String KEY_SEED = "3Vsqa36OvwyWHgaL1XrJ8k1+b0/RfujWfLs0/+EaejU=";

    private static byte[] keyBytes;

    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEY_SEED.getBytes());
            generator.init(KEY_SIZE, secureRandom);
            keyBytes = generator.generateKey().getEncoded();
        } catch (Exception e) {
            log.error("初始化密钥出错！", e);
        }
    }

    /**
     * 对给定字符串进行加密。
     *
     * @param str 待加密的字符串
     * @return 加密数据
     * @version 1.0.0
     * @since 1.0.0
     */
    public static String encryptStr(String str) {
        byte[] data = encrypt(StringUtils.getBytesUtf8(str), keyBytes);
        return new String(Base64.encodeBase64(data));
    }

    /**
     * 对给定字符串进行解密。
     *
     * @param str 待解密的字符串
     * @return 解密数据
     * @version 1.0.0
     * @since 1.0.0
     */
    public static String decryptStr(String str) {
        byte[] data = decrypt(Base64.decodeBase64(str), keyBytes);
        return StringUtils.newStringUtf8(data);
    }



    /**
     * 转换密钥。
     *
     * @param key 二进制密钥
     * @return 密钥
     * @version 1.0.0
     * @since 1.0.0
     */
    public static Key toKey(byte[] key) {
        // 实例化AES密钥材料

        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * 加密。
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return 加密数据
     * @version 1.0.0
     * @since 1.0.0
     */
    public static byte[] encrypt(byte[] data, byte[] key) {
        // 还原密钥

        Key k = toKey(key);

        byte[] encryptStrBytes = null;
        try {
            // 实例化 使用PKCS7Padding填充方式，按如下方式实现

            // Cipher.getInstance(CIPHER_ALGORITHM, "BC");

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            // 初始化，设置为加密模式

            cipher.init(Cipher.ENCRYPT_MODE, k);
            // 执行操作

            encryptStrBytes = cipher.doFinal(data);
        } catch (Exception e) {
            log.error("加密出错！", e);
        }

        return encryptStrBytes;
    }

    /**
     * 解密。
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return 解密数据
     * @version 1.0.0
     * @since 1.0.0
     */
    public static byte[] decrypt(byte[] data, byte[] key) {
        // 还原密钥

        Key k = toKey(key);

        byte[] decryptStrBytes = null;
        try {
            // 实例化使用PKCS7Padding填充方式，按如下方式实现

            // Cipher.getInstance(CIPHER_ALGORITHM, "BC");

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            // 初始化，设置为解密模式

            cipher.init(Cipher.DECRYPT_MODE, k);
            // 执行操作

            decryptStrBytes = cipher.doFinal(data);
        } catch (Exception e) {
            log.error("解密出错！", e);
        }

        return decryptStrBytes;
    }

    public static void main(String[] args) {


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mobile", "18551854330");
        jsonObject.put("sid", "m18551854330");

        String pwd = "lwyuuTAuKizSsipBmMgA8bOlW7MMpG4XNcNgN97HlWu8/104wxz3omjrMbCKD59/";

        log.info("加密：{}", AESUtil.encryptStr(jsonObject.toJSONString()));

        String json = AESUtil.decryptStr(pwd);
        log.info("解密：{}", json);


        Map<String, String> paramMap = JSON.parseObject(json,Map.class);
        log.info("解析参数: mobile={}", paramMap.get("mobile"));
        log.info("解析参数: sid={}", paramMap.get("sid"));


    }


}

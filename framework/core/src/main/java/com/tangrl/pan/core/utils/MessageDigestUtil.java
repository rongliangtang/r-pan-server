package com.tangrl.pan.core.utils;

import cn.hutool.core.codec.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * MessageDigest加密工具类
 *
 */
public class MessageDigestUtil {

    private static final String MD5_STR = "MD5";
    private static final String SHA1_STR = "SHA1";
    private static final String SHA256_STR = "SHA-256";
    public static final String ZERO_STR = "0";

    /**
     * 计算 字节数组 的 消息摘要，返回 字节数组
     *
     * @param originBytes
     * @param mode
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] originBytes, String mode) throws Exception {
        if (ArrayUtils.isEmpty(originBytes) || StringUtils.isBlank(mode)) {
            return ArrayUtils.EMPTY_BYTE_ARRAY;
        }
        MessageDigest md = MessageDigest.getInstance(mode);
        byte[] digestBytes = md.digest(originBytes);
        return digestBytes;
    }

    /**
     * 计算 字符串 的 消息摘要，返回 字节数组
     *
     * @param originContent
     * @param mode
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(String originContent, String mode) throws Exception {
        if (StringUtils.isBlank(originContent)) {
            return ArrayUtils.EMPTY_BYTE_ARRAY;
        }
        return encrypt(originContent.getBytes(StandardCharsets.UTF_8), mode);
    }

    /**
     * 计算 字符串 的 消息摘要，返回 字符串
     *
     * @param originContent
     * @param mode
     * @return
     */
    public static String encryptString(String originContent, String mode) {
        String result = StringUtils.EMPTY;
        try {
            byte[] encryptBytes = encrypt(originContent, mode);
            if (ArrayUtils.isEmpty(encryptBytes)) {
                return result;
            }
            result = Base64.encode(encryptBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取 md5 消息摘要
     *
     * @param originContent
     * @return
     */
    public static String md5(String originContent) {
        return encryptString(originContent, MD5_STR);
    }

    /**
     * 获取 sha1 消息摘要
     *
     * @param originContent
     * @return
     */
    public static String sha1(String originContent) {
        return encryptString(originContent, SHA1_STR);
    }

    /**
     * 获取 sha256 消息摘要
     *
     * @param originContent
     * @return
     */
    public static String sha256(String originContent) {
        return encryptString(originContent, SHA256_STR);
    }

}

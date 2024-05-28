package com.tangrl.pan.core.utils;

/**
 * 密码工具类
 */
public class PasswordUtil {

    /**
     * 随机生成盐值
     *
     * @return
     */
    public static String getSalt() {
        return MessageDigestUtil.md5(UUIDUtil.getUUID());
    }

    /**
     * 密码加密
     *
     * @param salt
     * @param inputPassword
     * @return
     */
    public static String encryptPassword(String salt, String inputPassword) {
        return MessageDigestUtil.sha256(MessageDigestUtil.sha1(inputPassword) + salt);
    }

}

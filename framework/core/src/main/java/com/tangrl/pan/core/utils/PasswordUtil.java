package com.tangrl.pan.core.utils;

/**
 * 密码工具类
 * 数据库中会存一个 随机生成的 salt，目的是增加密码的复杂性，防止彩虹表攻击。
 * 用户登陆的时候会利用 数据库 对应的 salt，计算出哈希值进行与数据库的密码哈希值进行对比
 */
public class PasswordUtil {

    /**
     * 随机生成 salt
     *
     * @return
     */
    public static String getSalt() {
        return MessageDigestUtil.md5(UUIDUtil.getUUID());
    }

    /**
     * 加密 password
     *
     * @param salt
     * @param inputPassword
     * @return
     */
    public static String encryptPassword(String salt, String inputPassword) {
        return MessageDigestUtil.sha256(MessageDigestUtil.sha1(inputPassword) + salt);
    }

}

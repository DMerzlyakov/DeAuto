package org.Client.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Util для работы с MD5
 */
public class MD5Util {

    /**
     * Превращаем строку в зашифрованную строку
     * @param password - пароль
     * @return Пароль в MD5
     */
    public static String md5(String password){
        return DigestUtils.md5Hex(password);
    }
}

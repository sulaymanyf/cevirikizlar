package com.yeaile.common.utils;

import com.google.common.base.Charsets;
import org.springframework.util.Base64Utils;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/5
 * @return
 **/
public class Base64Util extends Base64Utils {
    public Base64Util() {
    }

    public static String encode(String value) {
        return encode(value, Charsets.UTF_8);
    }

    public static String encode(String value, Charset charset) {
        byte[] val = value.getBytes(charset);
        return new String(encode(val), charset);
    }

    public static String encodeUrlSafe(String value) {
        return encodeUrlSafe(value, Charsets.UTF_8);
    }

    public static String encodeUrlSafe(String value, Charset charset) {
        byte[] val = value.getBytes(charset);
        return new String(encodeUrlSafe(val), charset);
    }

    public static byte[] decode(String value) {
        return Base64.getDecoder().decode(value);
    }

    public static String decodeUrlSafe(String value) {
        return decodeUrlSafe(value, Charsets.UTF_8);
    }

    public static String decodeUrlSafe(String value, Charset charset) {
        byte[] val = value.getBytes(charset);
        byte[] decodedValue = decodeUrlSafe(val);
        return new String(decodedValue, charset);
    }
}


package com.yeaile.common.utils.conn;


import com.yeaile.common.utils.StringUtil;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/5
 * @return
 **/
public class Charsets {
    public static final Charset ISO_8859_1;
    public static final Charset GBK;
    public static final Charset UTF_8;

    public Charsets() {
    }

    public static Charset charset(String charsetName) throws UnsupportedCharsetException {
        return StringUtil.isBlank(charsetName) ? Charset.defaultCharset() : Charset.forName(charsetName);
    }

    static {
        ISO_8859_1 = StandardCharsets.ISO_8859_1;
        GBK = Charset.forName("GBK");
        UTF_8 = StandardCharsets.UTF_8;
    }
}

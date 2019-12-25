package com.yeaile.common.utils;

import com.google.common.base.Charsets;
import com.yeaile.common.utils.conn.Exceptions;
import org.springframework.lang.Nullable;
import org.springframework.util.StreamUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/5
 * @return
 **/
public class IoUtil extends StreamUtils {
    public IoUtil() {
    }

    public static void closeQuietly(@Nullable Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException var2) {
        }

    }

    public static String toString(InputStream input) {
        return toString(input, Charsets.UTF_8);
    }

    public static String toString(@Nullable InputStream input, Charset charset) {
        String var2;
        try {
            var2 = copyToString(input, charset);
        } catch (IOException var6) {
            throw Exceptions.unchecked(var6);
        } finally {
            closeQuietly(input);
        }

        return var2;
    }

    public static byte[] toByteArray(@Nullable InputStream input) {
        byte[] var1;
        try {
            var1 = copyToByteArray(input);
        } catch (IOException var5) {
            throw Exceptions.unchecked(var5);
        } finally {
            closeQuietly(input);
        }

        return var1;
    }

    public static void write(@Nullable final String data, final OutputStream output, final Charset encoding) throws IOException {
        if (data != null) {
            output.write(data.getBytes(encoding));
        }

    }
}

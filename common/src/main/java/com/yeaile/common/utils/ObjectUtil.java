package com.yeaile.common.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/5
 * @return
 **/
public class ObjectUtil extends ObjectUtils {
    public ObjectUtil() {
    }

    public static boolean isNotEmpty(@Nullable Object obj) {
        return !isEmpty(obj);
    }
}

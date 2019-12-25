package com.yeaile.web.generator.config;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/19
 * @return
 **/
public class CustomMySqlTypeConvert implements ITypeConvert {
    public CustomMySqlTypeConvert() {
    }

    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        String t = fieldType.toLowerCase();
        if (!t.contains("char") && !t.contains("test")) {
            if (t.contains("bigint")) {
                return DbColumnType.BIG_INTEGER;
            } else if (t.contains("int")) {
                return DbColumnType.INTEGER;
            } else if (!t.contains("date") && !t.contains("time") && !t.contains("year")) {
                if (t.contains("test")) {
                    return DbColumnType.STRING;
                } else if (t.contains("bit")) {
                    return DbColumnType.BOOLEAN;
                } else if (t.contains("decimal")) {
                    return DbColumnType.BIG_DECIMAL;
                } else if (t.contains("blob")) {
                    return DbColumnType.BYTE_ARRAY;
                } else if (t.contains("float")) {
                    return DbColumnType.BIG_DECIMAL;
                } else if (t.contains("double")) {
                    return DbColumnType.BIG_DECIMAL;
                } else {
                    return !t.contains("json") && !t.contains("enum") ? DbColumnType.STRING : DbColumnType.STRING;
                }
            } else {
                return DbColumnType.LOCAL_DATE_TIME;
            }
        } else {
            return DbColumnType.STRING;
        }
    }
}


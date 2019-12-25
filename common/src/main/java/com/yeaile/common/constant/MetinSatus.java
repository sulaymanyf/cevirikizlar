package com.yeaile.common.constant;

import com.yeaile.common.result.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Classname MetinSatus
 * @Description TODO
 * @Date 2019/12/25 10:08 下午
 * @Created by SulaymanYf
 */
@Getter
@AllArgsConstructor
public enum MetinSatus implements IResultCode {

    NEW(1,"新增"),
    TRANSLATING(2,"翻译中"),
    UPDATE(3,"更新"),
    END(4,"结束");


    /**
     * 编码
     */
    final int code;

    /**
     * 内容
     */
    final String message;

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}


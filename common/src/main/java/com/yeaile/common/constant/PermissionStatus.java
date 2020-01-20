package com.yeaile.common.constant;

import com.yeaile.common.result.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Classname PermissionStatus
 * @Description TODO
 * @Date 2020/1/10 6:08 下午
 * @Created by SulaymanYf
 */
@Getter
@AllArgsConstructor
public enum  PermissionStatus implements IResultCode {
    MENU(1,"菜单"),
    BUTTON(2,"按钮"),
    API(3,"路径"),
    RESOURCES(4,"资源");

    ///1为菜单 2为功能 3为API',


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

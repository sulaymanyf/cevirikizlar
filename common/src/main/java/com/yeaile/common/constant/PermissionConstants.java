package com.yeaile.common.constant;



import com.yeaile.common.result.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/24
 * @return
 **/
@Getter
@AllArgsConstructor
public enum PermissionConstants implements IResultCode {

    API(1,"接口"),
    MENU(2,"菜单"),
    POINT(3,"按钮");


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

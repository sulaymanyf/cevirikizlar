package com.yeaile.common.result;

import lombok.Data;

/**
 * @Title: 控制器类返回结果
 * @author: 陈宏松
 * @create: 2018-12-18 14:28
 * @version: 1.0.0
 **/
@Data
public class Result {

    /**
     * //是否成功
     */
    private boolean flag;

    /**
     * //返回码
     */
    private Integer code;

    /**
     * //返回信息
     */
    private String message;

    /**
     * //返回数据
     */
    private Object data;

    public Result() {
    }

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(boolean flag, Integer code, Object data) {
        this.flag = flag;
        this.code = code;
        this.data = data;
    }

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

}

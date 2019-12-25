package com.yeaile.common.constant;

public interface CommonResponse {
    /**
     * 获取状态码
     */
    String getCode();

    /**
     * 获取消息
     */
    String getMessage();

    /**
     * 响应结果
     */
    Object getResult();
}

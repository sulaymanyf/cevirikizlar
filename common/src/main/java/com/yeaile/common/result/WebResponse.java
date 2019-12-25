package com.yeaile.common.result;



import com.yeaile.common.constant.CommonResponse;
import com.yeaile.common.constant.HttpResponseStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/5
 * @return
 **/
@Data
@NoArgsConstructor
public class WebResponse {
    /**
     * 响应码
     */
    private String code;

    private int status;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 结果
     */
    private Object result;

    public WebResponse(HttpResponseStatusEnum httpResponseStatusEnum) {
        this.code = httpResponseStatusEnum.getCode();
        this.message = httpResponseStatusEnum.getMessage();
    }

    public WebResponse(CommonResponse commonResponse) {
        this.code = commonResponse.getCode();
        this.message = commonResponse.getMessage();
    }

    public WebResponse(String code, String message, Object result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public WebResponse(String code, int status, String message, Object result) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.result = result;
    }

    /**
     * 成功响应
     */
    public static WebResponse success() {
        return new WebResponse(HttpResponseStatusEnum.SUCCESS.getCode(), HttpResponseStatusEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 成功响应
     */
    public static WebResponse success(Object result) {
        return new WebResponse(HttpResponseStatusEnum.SUCCESS.getCode(), HttpResponseStatusEnum.SUCCESS.getMessage(), result);
    }

    /**
     * 禁止操作
     */
    public static WebResponse forbidden() {
        return new WebResponse(HttpResponseStatusEnum.FORBIDDEN_OPERATION.getCode(), HttpResponseStatusEnum.FORBIDDEN_OPERATION.getMessage(), null);
    }
}

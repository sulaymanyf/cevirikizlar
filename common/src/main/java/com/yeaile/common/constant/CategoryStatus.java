package com.yeaile.common.constant;




import com.yeaile.common.result.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryStatus implements IResultCode {


    NEEDS_REVIEW(0,"需审核"),
    NORMAL(1,"normal"),
    FREEZE(2,"冻结");

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

}

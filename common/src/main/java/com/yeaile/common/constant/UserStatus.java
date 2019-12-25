package com.yeaile.common.constant;




import com.yeaile.common.result.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus implements IResultCode {


    LOCKED(0,"账号锁定"),
    NORMAL(1,"normal"),
    UNSUBSCRIBE(2,"unsubscribe");

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

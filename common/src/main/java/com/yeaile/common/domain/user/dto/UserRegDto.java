package com.yeaile.common.domain.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/4
 * @return
 **/
@Data
@Builder
@ToString
public class UserRegDto implements Serializable {

    private static final long serialVersionUID = 5137410361342175273L;
    private String userName;
    private String password;
    private String rePassword;

    @Tolerate
    public UserRegDto() {
    }
}

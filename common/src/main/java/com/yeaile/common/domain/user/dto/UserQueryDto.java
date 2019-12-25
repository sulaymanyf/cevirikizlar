package com.yeaile.common.domain.user.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Tolerate;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @param
 * @author sulaymanyf
 * @Description
 * @date 2019/12/5
 * @return
 **/
@Data
@Builder
@ToString
public class UserQueryDto extends Page implements Serializable {

    private static final long serialVersionUID = 5085494065785486054L;
    private String email;

    private String loginName;

    private Integer userStatus;

    private String userName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDate birthDay;

    private String mobile;

    private String orderField;

    private String orderType;

    @Tolerate
    public UserQueryDto() {
    }
}

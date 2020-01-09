package com.yeaile.user.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yeaile.common.domain.user.dto.UserDTO;
import com.yeaile.common.domain.user.dto.UserLoginDto;
import com.yeaile.common.domain.user.dto.UserQueryDto;
import com.yeaile.common.domain.user.dto.UserRegDto;
import com.yeaile.common.domain.user.vo.UserAndRoleVo;
import com.yeaile.common.domain.user.vo.UserVo;
import com.yeaile.common.result.Result;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
public interface IUserService {


    UserVo register(UserRegDto user);

    String login(UserLoginDto user);

    IPage<UserVo> userList(UserQueryDto userQueryDto);

    UserVo findById(String id);

    void addUser(UserDTO userDto);

    void updateUser(UserDTO userDto);

    void deleteUserById(String id);

    UserAndRoleVo selectByName(String username);

}

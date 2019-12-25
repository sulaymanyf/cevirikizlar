package com.yeaile.user.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeaile.common.constant.UserStatus;
import com.yeaile.common.domain.user.dto.UserDTO;
import com.yeaile.common.domain.user.dto.UserLoginDto;
import com.yeaile.common.domain.user.dto.UserQueryDto;
import com.yeaile.common.domain.user.dto.UserRegDto;
import com.yeaile.common.domain.user.vo.UserVo;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.StatusCode;
import com.yeaile.common.utils.BeanUtil;
import com.yeaile.common.utils.IdWorkerUtil;
import com.yeaile.user.entity.User;
import com.yeaile.user.mapper.PermissionMapper;
import com.yeaile.user.mapper.RoleMapper;
import com.yeaile.user.mapper.UserMapper;
import com.yeaile.user.service.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigInteger;


/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
@Service
public class UserServiceImpl implements IUserService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PasswordEncoder passwordEncoder;



    @Override
    public UserVo register(UserRegDto userRegDto) {
        User user = BeanUtil.copy(userRegDto, User.class);
        // 加密
        user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
        user.setId(IdWorkerUtil.getIdStr());
        userMapper.insert(user);
        UserVo userVo = BeanUtil.copy(userMapper.selectById(user), UserVo.class);
        return userVo;
    }

    public User getUserByUserName(String userName){
        User user = this.userMapper.getUserByUserName(userName);

        if (user==null){
            //抛异常
        }
        return user;
    }

    @Override
    public Result login(UserLoginDto user) {
        UserVo userVo = BeanUtil.copy(getUserByUserName(user.getUserName()), UserVo.class);
        if (userVo.getUserStatus() != UserStatus.NORMAL.getCode()) {
            return new Result(false, StatusCode.ERROR, "账户已锁定或被注销");
        }
        return new Result(false, StatusCode.ERROR, "登录成功", userVo);
    }

    @Override
    public IPage<UserVo> userList(UserQueryDto userQueryDto) {
        Page<UserVo> page = new Page<>();
        IPage<User> userIPage = userMapper.userList(page, userQueryDto);
        IPage<UserVo> voIPage = BeanUtil.copyPagInfo(userIPage, UserVo.class);
        return voIPage;
    }

    @Override
    public UserVo findById(String id) {
        User user = userMapper.selectById(id);
        return BeanUtil.copy(user, UserVo.class);
    }

    @Override
    public void addUser(UserDTO userDto) {
        User user = BeanUtil.copy(userDto, User.class);
        //密码处理
        user.setUserStatus(0);
        userMapper.insert(user);
    }

    @Override
    public void updateUser(UserDTO userDto) {
        User user = BeanUtil.copy(userDto, User.class);
        userMapper.updateById(user);
    }

    @Override
    public void deleteUserById(String id) {
        userMapper.deleteById(new BigInteger(id));
    }
}

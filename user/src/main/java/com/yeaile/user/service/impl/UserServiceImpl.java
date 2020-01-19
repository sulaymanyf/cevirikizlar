package com.yeaile.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeaile.common.constant.UserStatus;
import com.yeaile.common.domain.ceviri.vo.MetinTypeNodeVO;
import com.yeaile.common.domain.user.dto.UserDTO;
import com.yeaile.common.domain.user.dto.UserLoginDto;
import com.yeaile.common.domain.user.dto.UserQueryDto;
import com.yeaile.common.domain.user.dto.UserRegDto;
import com.yeaile.common.domain.user.vo.*;
import com.yeaile.common.result.Result;
import com.yeaile.common.result.StatusCode;
import com.yeaile.common.utils.*;
import com.yeaile.user.entity.Role;
import com.yeaile.user.entity.User;
import com.yeaile.user.entity.UserRole;
import com.yeaile.user.mapper.PermissionMapper;
import com.yeaile.user.mapper.RoleMapper;
import com.yeaile.user.mapper.UserMapper;
import com.yeaile.user.mapper.UserRoleMapper;
import com.yeaile.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService, UserDetailsService {


    @Resource
    private UserMapper userMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    @Override
    public UserVo register(UserRegDto userRegDto) {
        User user = BeanUtil.copy(userRegDto, User.class);
        // 加密
        user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
        user.setId(IdWorkerUtil.getIdStr());
        user.setUserStatus(UserStatus.NORMAL.getCode());
        userMapper.insert(user);
        Role role = roleMapper.selectByName("ROLE_user");
        if (role==null){
            role = new Role();
            role.setId(IdWorkerUtil.getIdStr());
            role.setRoleName("ROLE_user");
            role.setDescription("基础用户");
            roleMapper.insert(role);
        }
        UserRole userRole = new UserRole();
        userRole.setId(IdWorkerUtil.getIdStr());
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getId());
        userRoleMapper.insert(userRole);
        UserVo userVo = BeanUtil.copy(userMapper.selectById(user), UserVo.class);
        RoleVO roleVO = BeanUtil.copy(role, RoleVO.class);
        userVo.setRoleVOS(new ArrayList<RoleVO>(){{add(roleVO);}});
        return userVo;
    }

    public User getUserByUserName(String userName) {
        User user = this.userMapper.getUserByUserName(userName);

        if (user == null) {
            //抛异常
        }
        return user;
    }

    @Override
    public String login(UserLoginDto user) {

        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
            if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            User byUserName = this.getUserByUserName(user.getUserName());
            token = jwtTokenUtil.generateToken(userDetails.getUsername(),byUserName.getId());
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
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


    @Override
    public UserAndRoleVo selectByName(String username) {
        User user = userMapper.getUserByUserName(username);
        UserAndRoleVo userAndRoleVo = BeanUtil.copy(user, UserAndRoleVo.class);
        List<String> roleList = userRoleMapper.selectByUserId(user.getId());
        List<Role> roles = roleMapper.selectBatchIds(roleList);
        List<RoleVO> roleVOS = BeanUtil.copyList(roles, RoleVO.class);
        userAndRoleVo.setRoles(roleVOS);
        return userAndRoleVo;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAndRoleVo user = this.selectByName(username);

        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在!");
        }
        return user;
    }


    @Override
    public List<MenuNodeVO> getUserMenuByUserId(String userId) {
        List<PermissionVO> menuList = permissionMapper.getUserMenuByUserId(userId);
        ArrayList<MenuNodeVO> menuNodeVOS = new ArrayList<>();
        for (PermissionVO menuNodeVO : menuList) {
            if (menuNodeVO!=null){
                menuNodeVOS.add(new MenuNodeVO(menuNodeVO.getId(),menuNodeVO.getPid(),menuNodeVO.getName(),menuNodeVO.getIcon(),menuNodeVO.getPath(),menuNodeVO.getSort(),menuNodeVO.getCode()));
            }
        }
        List<MenuNodeVO> menuTree = ListToTree(menuNodeVOS);
        return menuTree;
    }


    public static List<MenuNodeVO> ListToTree(ArrayList<MenuNodeVO> list){

        Map<String, MenuNodeVO> mapTmp = new HashMap<>();
        for (MenuNodeVO current : list) {
            mapTmp.put(current.getId(), current);
        }
        System.out.println(mapTmp);
        List<MenuNodeVO> finalList = new ArrayList<>();

        mapTmp.forEach((k, v) -> {
            if("".equals(v.getPid())|| "0".equals(v.getPid())) {
                finalList.add(v);
            } else {
                mapTmp.get(v.getPid()).getChildren().add(v);
            }
        });
        return finalList;
    }
}

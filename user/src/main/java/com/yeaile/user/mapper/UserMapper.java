package com.yeaile.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yeaile.common.domain.user.dto.UserQueryDto;
import com.yeaile.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author sulaymanyf
 * @since 2019-12-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User getUserByUserName(@Param("username") String username);

    IPage<User> userList(@Param("page") Page page, @Param("queryDto") UserQueryDto userQueryDto);

}

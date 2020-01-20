package com.yeaile.user.service;


import com.yeaile.common.domain.user.dto.MenuDTO;
import com.yeaile.common.domain.user.vo.MenuNodeVO;
import com.yeaile.common.domain.user.vo.MenuVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sulaymanyf
 * @since 2020-01-18
 */
public interface IMenuService {

    List<MenuNodeVO> getMenuTree();


    List<MenuVO> getMenuList();

    void AddMenu(MenuDTO menuDTO);

    void DeleteMenu(String id);
}

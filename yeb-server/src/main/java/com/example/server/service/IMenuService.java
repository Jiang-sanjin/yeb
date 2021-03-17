package com.example.server.service;

import com.example.server.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangsanjin
 * @since 2021-02-03
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 根据用户id查询菜单
     * @return
     */
    List<Menu> getMenusByAdminId();

    /**
     * 根据角色获取菜单
     */
    List<Menu> getMenusWithRole();

    /**
     * 查询所菜单
     * @return
     */
    List<Menu> getAllMenus();
}

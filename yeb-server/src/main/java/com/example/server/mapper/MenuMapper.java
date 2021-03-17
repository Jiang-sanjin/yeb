package com.example.server.mapper;

import com.example.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiangsanjin
 * @since 2021-02-03
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据用户id查询菜单
     * @param id
     * @return
     */
    List<Menu> getMenusByAdminId(Integer id);

    /**
     * 根据角色获取菜单
     * @return
     */
    List<Menu> getMenusWithRole();

    /**
     * 查询所菜单
     * @return
     */
    List<Menu> getAllMenus();
}
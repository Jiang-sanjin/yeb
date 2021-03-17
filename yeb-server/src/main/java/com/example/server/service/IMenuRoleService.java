package com.example.server.service;

import com.example.server.pojo.MenuRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangsanjin
 * @since 2021-02-03
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mids);
}

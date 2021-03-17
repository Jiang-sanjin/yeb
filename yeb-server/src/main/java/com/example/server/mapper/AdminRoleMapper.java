package com.example.server.mapper;

import com.example.server.pojo.AdminRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.server.pojo.RespBean;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiangsanjin
 * @since 2021-02-03
 */
@Repository
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 更新操作员的角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer addRole(@PathParam("adminId") Integer adminId,@PathParam("rids") Integer[] rids);
}

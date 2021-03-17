package com.example.server.mapper;

import com.example.server.pojo.MenuRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 批量更新数据
     * @param rid
     * @param mids
     * @return
     */
    Integer insertRecord(@PathParam("rid") Integer rid,@PathParam("mid") Integer[] mids);
}

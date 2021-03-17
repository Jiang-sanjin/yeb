package com.example.server.mapper;

import com.example.server.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
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
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 获取所有操作员
     * @param id
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmins(@PathParam("id") Integer id,@PathParam("keywords") String keywords);
}

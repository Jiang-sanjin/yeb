package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangsanjin
 * @since 2021-02-03
 */
@TableName("t_sys_msg")
@ApiModel(value="SysMsg对象", description="")
public class SysMsg implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "消息id")
    private Integer mid;

    @ApiModelProperty(value = "0表示群发消息")
    private Integer type;

    @ApiModelProperty(value = "这条消息是给谁的")
    private Integer adminid;

    @ApiModelProperty(value = "0 未读 1 已读")
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "SysMsg{" +
            "id=" + id +
            ", mid=" + mid +
            ", type=" + type +
            ", adminid=" + adminid +
            ", state=" + state +
        "}";
    }
}

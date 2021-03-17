package com.example.server.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * 单独定义
 * 用户登录实体类
 * 用于封装  防止数据过多
 */
@Api(value = "AdminLogin对象")
public class AdminLoginParam {

    @ApiModelProperty(value = "用户名",required = true)
    private String username;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    @ApiModelProperty(value = "验证码",required = true)
    private String code;

    public AdminLoginParam() {
    }

    public AdminLoginParam(String username, String password, String code) {
        this.username = username;
        this.password = password;
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "AdminLoginParam{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

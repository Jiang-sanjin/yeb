package com.example.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.server.config.jwt.JwtTokenUtil;
import com.example.server.mapper.AdminRoleMapper;
import com.example.server.mapper.RoleMapper;
import com.example.server.pojo.Admin;
import com.example.server.mapper.AdminMapper;
import com.example.server.pojo.AdminRole;
import com.example.server.pojo.RespBean;
import com.example.server.pojo.Role;
import com.example.server.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangsanjin
 * @since 2021-02-03
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;

    // sercurity的实现方法
    @Autowired
    private UserDetailsService userDetailsService;

    // sercurity的加密
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    // jwt请求头
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    /**
     * 登录之后返回token
     * @param username
     * @param password
     * @param code
     * @param request
     * @return
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {

    /*    //验证验证码
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code)|| !captcha.equalsIgnoreCase(code)){     //equalsIgnoreCase   忽略大小写
            return RespBean.error("验证码填写错误");
        }*/

        //登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);   //获取用户信息

        System.out.println(userDetails.getPassword());
        if ( null==userDetails || !passwordEncoder.matches(password,userDetails.getPassword()) ){
            return RespBean.error("用户名或者密码错误");
        }
        if (!userDetails.isEnabled()){
            return RespBean.error("账号被封禁，请联系管理员");
        }

        //更新security登录用户信息    第一个参放userDetails  第二个参数放凭证（密码） 不放
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        //放在全局
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        HashMap<String, Object> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);   //传递请求投给前端   前端可带着请求头对后端进行访问
        return RespBean.success("登录成功",tokenMap);
    }

    /**
     * 根据用户名获取用户
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        //  sql语句:SELECT id,name,phone,telephone,address,enabled,username,password,userFace,remark FROM t_admin WHERE (username = ? AND enabled = ?)
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq("enabled",true));  //eq是equals的缩写
    }

    /**
     * 根据用户ID查询角色列表
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRolesByAdminId(Integer adminId) {
        return roleMapper.getRolesByAdminId(adminId);
    }

    /**
     * 获取所有操作员
     * @return
     */
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        //获取当前用户操作员 id
        Integer id = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return adminMapper.getAllAdmins(id,keywords);
    }

    /**
     * 更新操作员的角色
     * @param adminId
     * @param rids
     * @return
     */

    @Override
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        Integer result = adminRoleMapper.addRole(adminId, rids);
        if (rids.length == result){
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @Override
    public RespBean updatePassword(String oldPass, String pass, Integer adminId) {
        Admin admin = adminMapper.selectById(adminId);
        // 密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(oldPass,admin.getPassword())){
            admin.setPassword(encoder.encode(pass));
            int i = adminMapper.updateById(admin);
            if (i == 1){
                return RespBean.success("更新成功");
            }
        }
        return RespBean.error("更新失败");
    }

    /*    *
     * 密码加密
     * @param args
     */
 /*   public static void main(String[] args) {
        String pass = "123456";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String passHash = encoder.encode(pass);
        System.out.println(passHash);

        final boolean matches = encoder.matches(pass, passHash);
        System.out.println(matches);
    }*/
}

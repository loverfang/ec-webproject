package com.goodcub.vci.entity;

import lombok.Data;

/**
 * @ClassName SysUserRole
 * @Description 用户角色表
 * @Author Luo.z.x
 * @Date 2019/9/2123:27
 * @Version 1.0
 */
@Data
public class SysUserRole {
    private Integer id;
    private Integer userId;
    private Integer roleId;
}

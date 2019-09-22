package com.goodcub.vci.entity;

import lombok.Data;

/**
 * @ClassName SysUser
 * @Description 系统用户信息
 * @Author Luo.z.x
 * @Date 2019/9/2123:23
 * @Version 1.0
 */
@Data
public class SysUser {
    protected Integer userId;
    protected String  account;
    protected String  password;
    protected Integer status;
    protected String  createTime;
    protected String  lastLoginTime;
    protected String  remark;
    protected String  skin;
}

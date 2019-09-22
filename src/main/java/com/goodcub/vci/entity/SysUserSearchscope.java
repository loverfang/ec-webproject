package com.goodcub.vci.entity;

import lombok.Data;

/**
 * @ClassName SysUserSearchscope
 * @Description 用户查询范围信息表
 * @Author Luo.z.x
 * @Date 2019/9/2123:29
 * @Version 1.0
 */
@Data
public class SysUserSearchscope {
    private Integer id;
    private Integer userId; //用户ID
    private String deptId;  //查询范围(部门)的拼接字符串
}

package com.goodcub.vci.vo.admin;

import com.goodcub.vci.entity.SysUser;
import lombok.Data;

/**
 * @ClassName SysUserVO
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/220:05
 * @Version 1.0
 */
@Data
public class SysUserVO extends SysUser {
    private String empName;
    private String tel;
    private String depId;
}

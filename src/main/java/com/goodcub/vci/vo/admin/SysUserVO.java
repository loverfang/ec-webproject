package com.goodcub.vci.vo.admin;

import com.goodcub.vci.entity.SysUser;
import lombok.Data;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
@Data
public class SysUserVO extends SysUser {
    private String empName;
    private String tel;
    private String deptId;
}

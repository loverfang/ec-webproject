package com.goodcub.vci.vo.admin;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ResetPwdVO
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/411:24
 * @Version 1.0
 */
@Data
public class ResetPwdVO implements Serializable {
    private String password;
    private String newpassword;
    private String confirmpwd;
}

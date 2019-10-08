package com.goodcub.vci.service.admin;

import com.goodcub.vci.entity.SysUser;
import com.goodcub.vci.vo.admin.SysUserVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysUserService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/220:09
 * @Version 1.0
 */
public interface SysUserService {

    /**
     * 根据条件查询账号列表
     * @param params
     * @return
     */
    List<SysUser> queryAccountList(Map<String,Object> params);

    /**
     * 查询账号详情
     * @param userId
     * @return
     */
    SysUserVO queryAccountInfo(Integer userId);

    /**
     * 重置登录密码
     * @param userId
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Integer resetPassword(Integer userId, String oldPassword, String newPassword);
}

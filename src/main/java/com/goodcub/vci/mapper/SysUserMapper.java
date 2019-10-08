package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.SysUser;
import com.goodcub.vci.vo.admin.SysUserVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName SysUserMapper
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/229:15
 * @Version 1.0
 */
public interface SysUserMapper {

    /**
     * 查询账号列表
     * @param params
     * @return
     */
    List<SysUser> queryAccountList(Map<String,Object> params);

    /**
     * 查询账号信息
     * @param userId
     * @return
     */
    SysUserVO queryAccountInfo(Integer userId);

    Integer updateSysuser(SysUser sysUser);
}

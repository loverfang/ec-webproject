package com.goodcub.vci.service.admin.impl;

import com.goodcub.vci.entity.SysUser;
import com.goodcub.vci.mapper.SysUserMapper;
import com.goodcub.vci.service.admin.SysUserService;
import com.goodcub.vci.vo.admin.SysUserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SysUserServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/220:10
 * @Version 1.0
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysUser> queryAccountList(Map<String, Object> params) {
        List<SysUser> sysUserList = sysUserMapper.queryAccountList(params);
        return sysUserList;
    }

    @Override
    public SysUserVO queryAccountInfo(Integer userId) {
        return sysUserMapper.queryAccountInfo(userId);
    }

    @Override
    public Integer resetPassword(Integer userId, String oldPassword, String newPassword) {
        SysUserVO sysUserVO = sysUserMapper.queryAccountInfo(userId);
        if(sysUserVO.getPassword().equals(oldPassword)){
            // 设置新密码
            SysUser sysUser = new SysUser();
            sysUser.setUserId(userId);
            sysUser.setPassword(newPassword);
            return sysUserMapper.updateSysuser(sysUser);
        }else{
            return -1;
        }
    }
}

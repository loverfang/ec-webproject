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
}

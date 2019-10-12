package com.goodcub.vci.service.site.impl;

import com.goodcub.vci.entity.Autologin;
import com.goodcub.vci.mapper.AutoLoginMapper;
import com.goodcub.vci.service.site.AutoLoginFrontService;
import com.goodcub.vci.vo.site.AutologinFrontVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
@Service
public class AutoLoginFrontServiceImpl implements AutoLoginFrontService {

    @Resource
    AutoLoginMapper autoLoginMapper;

    @Override
    public AutologinFrontVO queryAutoLoginByPhone(String phone) {
        return autoLoginMapper.queryAutoLogin(phone);
    }

    @Override
    public Integer saveAutoLogin(Autologin autologin) {
        return autoLoginMapper.insert(autologin);
    }

    @Override
    public Integer updateAutoLogin(Autologin autologin) {
        return autoLoginMapper.update(autologin);
    }

    @Override
    public Integer deleteAutoLoginByUname(String uname) {
        return autoLoginMapper.deleteByUname(uname);
    }

}

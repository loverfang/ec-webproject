package com.goodcub.vci.service.site.impl;

import com.goodcub.vci.mapper.MemberMapper;
import com.goodcub.vci.service.site.MemberFrontService;
import com.goodcub.vci.vo.site.MemberFrontVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
@Service
public class MemberFrontServiceImpl implements MemberFrontService {
    @Resource
    MemberMapper memberMapper;

    @Override
    public MemberFrontVO mlogin(Map<String, Object> param) {
        return memberMapper.mlogin(param);
    }
}

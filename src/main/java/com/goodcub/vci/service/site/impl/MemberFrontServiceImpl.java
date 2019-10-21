package com.goodcub.vci.service.site.impl;

import com.goodcub.vci.entity.MemEmailLogs;
import com.goodcub.vci.entity.Members;
import com.goodcub.vci.mapper.MemberEmailLogsMapper;
import com.goodcub.vci.mapper.MemberMapper;
import com.goodcub.vci.service.site.MemberFrontService;
import com.goodcub.vci.vo.site.MemberFrontVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
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

    @Resource
    MemberEmailLogsMapper memberEmailLogsMapper;

    @Override
    public MemberFrontVO mlogin(Map<String, Object> param) {
        return memberMapper.mlogin(param);
    }

    @Override
    public MemberFrontVO queryMemberViewCount(Long memid) {
        return memberMapper.queryMemberViewCount(memid);
    }

    @Override
    public MemberFrontVO queryFrontMemberByMemId(Long memid) {
        return memberMapper.queryFrontMemberByMemId(memid);
    }

    @Override
    public Integer updateMemberViewCount(Members members) {
        return memberMapper.updateMemberViewCount(members);
    }

    @Override
    public List<Members> queryValidateRegistInfo(Map<String, Object> param) {
        return memberMapper.queryValidateMember(param);
    }

    @Override
    @Transactional
    public Integer insertMemberFront(Members members, MemEmailLogs maillog) {
        //保存会员信息
        memberMapper.insertMembers(members);
        //保存邮件发送日志
        memberEmailLogsMapper.insertEmailLogs(maillog);
        return 1;
    }

    @Override
    public Members queryMemberByEmail(String email) {
        return memberMapper.queryOneByEmail(email);
    }

    @Override
    public Integer updateMemberNewPwd(Members members) {
        return memberMapper.updateMembers(members);
    }
}

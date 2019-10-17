package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.Members;
import com.goodcub.vci.mapper.MemberMapper;
import com.goodcub.vci.service.admin.MemberService;
import com.goodcub.vci.vo.admin.MembersVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName MemberServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/258:41
 * @Version 1.0
 */
@Service
public class MemberServiceImpl implements MemberService {

    @Resource
    MemberMapper memberMapper;

    @Override
    public TableDataInfo queryMembersList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"status asc, regtime desc");
        List<Members> membersList = memberMapper.queryMembersList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<Members> pageInfo = new PageInfo<>(membersList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setTotal(pageInfo.getTotal());

        return tableDataInfo;
    }

    @Override
    public Integer countTotal() {
        return memberMapper.countTotal();
    }

    @Override
    public JsonResult insertMember(Members members) {

        Map<String,Object> params = new HashMap<>();
        params.put("username", members.getUsername());
        params.put("phone", members.getPhone());
        params.put("email", members.getEmail());
        List<Members> membersList = memberMapper.queryValidateMember(params);

        if(membersList!=null && membersList.size() > 0){
            Members tempMember = membersList.get(0);
            if(tempMember.getUsername().equals(members.getUsername())){
                return JsonResult.error("用户名已存在!");
            }
            if(tempMember.getPhone().equals(members.getPhone())){
                return JsonResult.error("手机号码已存在!");
            }
            if(tempMember.getEmail().equals(members.getEmail())){
                return JsonResult.error("邮箱地址已存在!");
            }
        }

        memberMapper.insertMembers(members);
        return JsonResult.success();
    }

    @Override
    public JsonResult updateMember(Members members) {
        memberMapper.updateMembers(members);
        return JsonResult.success();
    }
}

package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.Members;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MemberMapper
 * @Description 会员信息管理
 * @Author Luo.z.x
 * @Date 2019/9/258:32
 * @Version 1.0
 */
public interface MemberMapper {

    List<Members> queryMembersList(Map<String,Object> params);

    Integer usernameIsExist(Map<String,Object> params);

    Integer emailIsExist(Map<String,Object> params);

    Integer phoneIsExist(Map<String,Object> params);

    Integer insertMembers(Members members);

    Integer updateMembers(Members members);

    Members queryOneByMemid(Integer memid);

    Members queryOneByEmail(Integer memid);

    Integer deleteMemberById(Integer memid);

    Integer updatePasswordByEmail(String email);

}

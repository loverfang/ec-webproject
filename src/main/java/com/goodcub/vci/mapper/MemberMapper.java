package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.Members;
import com.goodcub.vci.vo.admin.MembersVO;
import com.goodcub.vci.vo.site.MemberFrontVO;

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

    List<Members> queryMembersList(Map<String, Object> params);

    Integer usernameIsExist(Map<String, Object> params);

    Integer emailIsExist(Map<String, Object> params);

    Integer phoneIsExist(Map<String, Object> params);

    Integer insertMembers(Members members);

    Integer updateMembers(Members members);

    Members queryOneByMemid(Integer memid);

    Members queryOneByEmail(Integer memid);

    Integer deleteMemberById(Integer memid);

    Integer updatePasswordByEmail(String email);

    /**
     * 判断是否存在username或email或phone的用户记录
     *
     * @param params
     * @return
     */
    List<Members> queryValidateMember(Map<String, Object> params);

    /**
     * 前台会员登录
     * @param params
     * @return
     */
    MemberFrontVO mlogin(Map<String,Object> params);

    /**
     * 前台会员自动登录
     * @param phone
     * @return
     */
    MemberFrontVO mAutoLogin(String phone);

}

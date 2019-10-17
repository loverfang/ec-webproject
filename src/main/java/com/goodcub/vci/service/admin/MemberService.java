package com.goodcub.vci.service.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.Members;
import com.goodcub.vci.vo.admin.MembersVO;

import java.util.List;
import java.util.Map;

/**
 * @ClassName MemberService
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/258:39
 * @Version 1.0
 */
public interface MemberService {

    /**
     * 查询会员信息
     * @param params
     * @return
     */
    TableDataInfo queryMembersList(Map<String,Object> params, int pageNum, int pageSize);

    /**
     * 添加会员信息
     * @param members
     * @return
     */
    JsonResult insertMember(Members members);

    /**
     * 添加会员信息
     * @param members
     * @return
     */
    JsonResult updateMember(Members members);

    /**
     * 统计总会员数
     * @return
     */
    Integer countTotal();
}

package com.goodcub.vci.service.site;

import com.goodcub.vci.entity.MemEmailLogs;
import com.goodcub.vci.entity.Members;
import com.goodcub.vci.vo.site.MemberFrontVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
public interface MemberFrontService {

    MemberFrontVO mlogin(Map<String,Object> param);

    MemberFrontVO queryMemberViewCount(Long memid);

    MemberFrontVO queryFrontMemberByMemId(Long memid);

    Integer updateMemberViewCount(Members members);

    List<Members> queryValidateRegistInfo(Map<String,Object> param);

    Integer insertMemberFront(Members members, MemEmailLogs maillog);

    Members queryMemberByEmail(String email);

    Integer updateMemberNewPwd(Members members);

}

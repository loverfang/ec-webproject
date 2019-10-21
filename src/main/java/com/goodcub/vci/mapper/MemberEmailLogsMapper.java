package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.MemEmailLogs;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/21
 * @Version V1.0
 **/
public interface MemberEmailLogsMapper {

    Integer insertEmailLogs(MemEmailLogs memEmailLogs);

    Integer updateEmailLogs(MemEmailLogs memEmailLogs);

    MemEmailLogs registValidateEmail(Long melid);

    MemEmailLogs queryOneById(Long melid);

    MemEmailLogs changePasswordEmail(Long melid);

    MemEmailLogs queryAllEmailLogs(Map<String,Object> param);

}

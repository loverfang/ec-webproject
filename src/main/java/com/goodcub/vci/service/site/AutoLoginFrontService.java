package com.goodcub.vci.service.site;

import com.goodcub.vci.entity.Autologin;
import com.goodcub.vci.vo.site.AutologinFrontVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
public interface AutoLoginFrontService {
    AutologinFrontVO queryAutoLoginByPhone(String phone);
    Integer saveAutoLogin(Autologin autologin);
    Integer updateAutoLogin(Autologin autologin);
    Integer deleteAutoLoginByUname(String uname);
}

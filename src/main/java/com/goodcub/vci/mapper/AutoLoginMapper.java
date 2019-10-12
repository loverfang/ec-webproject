package com.goodcub.vci.mapper;

import com.goodcub.vci.entity.Autologin;
import com.goodcub.vci.vo.site.AutologinFrontVO;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
public interface AutoLoginMapper {

    AutologinFrontVO queryAutoLogin(String username);

    Integer insert(Autologin autologin);

    Integer update(Autologin autologin);

    Integer deleteByUname(String uname);

}

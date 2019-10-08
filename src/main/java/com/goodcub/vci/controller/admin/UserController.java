package com.goodcub.vci.controller.admin;

import com.goodcub.common.GlobalConstant;
import com.goodcub.common.annotation.UserLoginToken;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.service.admin.NewsService;
import com.goodcub.vci.service.admin.SysUserService;
import com.goodcub.vci.vo.admin.ResetPwdVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/411:22
 * @Version 1.0
 */
@Controller
@RequestMapping("api/manage")
public class UserController {

    @Resource
    SysUserService sysUserService;

    @UserLoginToken
    @PostMapping("/resetpwd")
    @ResponseBody
    public JsonResult resetPwd(HttpServletRequest request, @RequestBody ResetPwdVO resetPwdVO){
        if(!resetPwdVO.getConfirmpwd().equals(resetPwdVO.getNewpassword())){
            return JsonResult.error("请确认您的新密码!");
        }

        // 获取Token中含有的用户ID信息
        String userIdStr = (String)request.getAttribute(GlobalConstant.LOGINED_USER_KEY);
        Integer resetResut = sysUserService.resetPassword(Integer.valueOf(userIdStr), resetPwdVO.getPassword(),resetPwdVO.getNewpassword());
        if(resetResut>=0){
            return JsonResult.success();
        }
        return JsonResult.error("原始密码错误!");
    }

}

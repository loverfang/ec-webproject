package com.goodcub.vci.controller.site;

import com.goodcub.common.GlobalConstant;
import com.goodcub.common.enums.StatusEnum;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.common.utils.MD5Util;
import com.goodcub.common.utils.WebUtil;
import com.goodcub.vci.entity.Autologin;
import com.goodcub.vci.service.site.AutoLoginFrontService;
import com.goodcub.vci.service.site.MemberFrontService;
import com.goodcub.vci.vo.site.AutologinFrontVO;
import com.goodcub.vci.vo.site.MemberFrontVO;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
@Controller
@RequestMapping("member")
public class MemberFrontController {

    @Resource
    MemberFrontService memberFrontService;
    @Resource
    AutoLoginFrontService autoLoginFrontService;

    /**
     * 顶部登录操作
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping("/dologin")
    public JsonResult doLogin(HttpServletRequest request, HttpServletResponse response,
        @RequestParam(value = "username", required = true)String username,
        @RequestParam(value = "password", required = true)String password,
        @RequestParam(value = "ifchecked", required = false)String ifchecked){

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("username", username);
        param.put("password", MD5Util.MD5(password));

        MemberFrontVO member = memberFrontService.mlogin(param);
        if(member!=null){

            if( (StatusEnum.NORMAL.equals(member.getStatus()))){

                HttpSession session = request.getSession();
                if("autologin".equals(ifchecked)){

                    //cookie的有效期
                    long validTime = System.currentTimeMillis() + (GlobalConstant.COOKIE_MAX_AGE * 1000);

                    //MD5加密用户详细信息
                    String cookieValueWithMd5 = MD5Util.MD5(member.getPhone()+ ":" +param.get("password")+ ":" + validTime + ":" + GlobalConstant.WEB_KEY);

                    //将要被保存的完整的Cookie值
                    String cookieValue = member.getPhone() + ":" + validTime + ":" + cookieValueWithMd5;

                    //再一次对Cookie的值进行BASE64编码
                    String cookieValueBase64 = new String(Base64.encodeBase64(cookieValue.getBytes()));
                    WebUtil.addCookie(response, GlobalConstant.COOKIE_DOMAIN_NAME, cookieValueBase64 , 60 * 60 * 24 * 10);

                    //防止篡改cookie
                    //判断数据库中有没有这个name登录的记录，有的话就更新它的结束时间和当前sessionID
                    AutologinFrontVO autoMember = autoLoginFrontService.queryAutoLoginByPhone( member.getPhone() );

                    if(autoMember!=null){
                        Autologin autologin = new Autologin();
                        autologin.setAid(autoMember.getAid());
                        autologin.setSessionId(session.getId());
                        autologin.setValidtime(Long.toString(validTime));
                        autoLoginFrontService.updateAutoLogin(autologin);
                    }else{
                        Autologin autoLogin = new Autologin();
                        autoLogin.setUname(member.getPhone());
                        autoLogin.setSessionId(session.getId());
                        autoLogin.setValidtime(Long.toString(validTime));
                        autoLoginFrontService.saveAutoLogin(autoLogin);
                    }
                }

                session.setAttribute("member", member);
                return JsonResult.success("login successed!");
            }else if(StatusEnum.WAITCHECK.equals(member.getStatus())){
                return JsonResult.error("Please activate your account first.");
            }else{
                return JsonResult.error("User status exception, please contact the administrator!");
            }
        }else{
            return JsonResult.error("Username or password error!");
        }
    }

    /**
     * 用户登出方法
     * @return
     * @throws Exception
     */
    @ResponseBody
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception{

        HttpSession session = request.getSession(false);
        if(session == null){
            response.sendRedirect("/");
            return null;
        }else{
            Object tempMem = session.getAttribute("member");
            if(tempMem != null){

                MemberFrontVO member = (MemberFrontVO)tempMem;

                //清除cookie
                WebUtil.clearCookieByName(request, response, "/", GlobalConstant.COOKIE_DOMAIN_NAME, "");

                //清除登录记录
                autoLoginFrontService.deleteAutoLoginByUname(member.getPhone());
                session.removeAttribute("member");
            }
            session.invalidate();
            response.sendRedirect("/");
            return null;
        }

    }



}

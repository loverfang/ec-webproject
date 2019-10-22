package com.goodcub.vci.controller.site;

import com.goodcub.common.DesEncryption;
import com.goodcub.common.GlobalConstant;
import com.goodcub.common.email.EmailSender;
import com.goodcub.common.enums.StatusEnum;
import com.goodcub.common.utils.*;
import com.goodcub.vci.entity.Autologin;
import com.goodcub.vci.entity.MemEmailLogs;
import com.goodcub.vci.entity.Members;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: 会员前台操作方法
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

    @GetMapping("/tologin")
    public String toLogin(){
         return "site/mlogin";
    }

    /**
     * 顶部登录操作
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @PostMapping("/dologin")
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

    /**
     * 进入到注册页面
     * @return
     */
    @GetMapping("/regist")
    public String register(){
        return "site/register";
    }

    /**
     * 注册协议页面
     * @return
     */
    @GetMapping("/terms")
    public String terms(){
        return "site/member/terms-of-use";
    }

    /**
     * 执行会员注册
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/doreg")
    public JsonResult doreg(MemberFrontVO memberFrontVO) throws Exception{
        if(null==(memberFrontVO.getName())||"".equals(memberFrontVO.getName())){
            return JsonResult.error("please input your name!");
        }

        if(null==(memberFrontVO.getJobtitle())||"".equals(memberFrontVO.getJobtitle())){
            return JsonResult.error("please input your jobtitle!");
        }

        if(null==(memberFrontVO.getCompany())||"".equals(memberFrontVO.getCompany())){
            return JsonResult.error("please input your company!");
        }

        if(null==(memberFrontVO.getEmail())||"".equals(memberFrontVO.getEmail())){
            return JsonResult.error("please input your email!");
        }

        if(null==memberFrontVO.getUsername()||"".equals(memberFrontVO.getUsername())){
            return JsonResult.error("please input your userName!");
        }

        if(null==(memberFrontVO.getPassword())||"".equals(memberFrontVO.getPassword())){
            return JsonResult.error("please input your password!");
        }

        if(null==(memberFrontVO.getPhone())||"".equals(memberFrontVO.getPhone())){
            return JsonResult.error("please input your phone!");
        }

        if(null==(memberFrontVO.getVcode())||"".equals(memberFrontVO.getVcode())){
            return JsonResult.error("please input your vcode!");
        }

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("username", memberFrontVO.getUsername());
        param.put("phone",memberFrontVO.getPhone());
        param.put("email",memberFrontVO.getEmail());
        List<Members> memberList = memberFrontService.queryValidateRegistInfo(param);

        if(memberList!=null && memberList.size()>0){
            for(Members members : memberList){
                if(memberFrontVO.getPhone().equals(members.getPhone())){
                    return JsonResult.error("the phone has used!");
                }
                if(memberFrontVO.getEmail().equals(members.getEmail())){
                    return JsonResult.error("the email has used!");
                }
                if(memberFrontVO.getUsername().equals(members.getUsername())){
                    return JsonResult.error("the username has used!");
                }
            }
        }

        //设置会员注册信息
        Members members = new Members();
        Long memId = new IdWorker().nextId();
        members.setMemid(memId);
        members.setUsername(memberFrontVO.getUsername());
        members.setPassword(MD5Util.MD5(memberFrontVO.getPassword()));
        members.setRegtime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss", new Date()));
        members.setStatus(StatusEnum.WAITCHECK);

        members.setName(memberFrontVO.getName());
        members.setJobtitle(memberFrontVO.getJobtitle());
        members.setCompany(memberFrontVO.getCompany());
        members.setEmail(memberFrontVO.getEmail());
        members.setPhone(memberFrontVO.getPhone());
        members.setViewcount(0);
        members.setTotaldays(0);
        members.setAvldays(0);

        members.setTxt1("暂无");
        members.setTxt2("暂无");
        members.setTxt3("暂无");
        members.setTxt4("暂无");
        members.setTxt5("暂无");
        members.setTxt6("暂无");
        members.setTxt7("暂无");
        members.setTxt8("暂无");
        members.setTxt9("暂无");
        members.setTxt10("暂无");
        members.setTxt11("暂无");
        members.setTxt12("暂无");

        String validateCode = WebUtil.getNumStrByLength(6);

        //插入记录
        MemEmailLogs maillog = new MemEmailLogs();
        Long memLogId = new IdWorker().nextId();
        maillog.setMelid(memLogId);
        maillog.setState(StatusEnum.PROCESSING); //设置认证过程正在处理中,认证完成后设置为已处理
        maillog.setMemid(memId);
        maillog.setEmail(memberFrontVO.getEmail());
        maillog.setCode(validateCode);
        maillog.setAddtime(new Date());
        maillog.setStype(0);

        //写入注册信息,邮件发送记录
        Integer registResult = memberFrontService.insertMemberFront(members, maillog);

        //写入数据库后发送邮件进行确认
        if(registResult > 0){

            String hmacStr = "";
            hmacStr += "melid="+memLogId;
            hmacStr += "&memid="+memId;
            hmacStr += "&email="+memberFrontVO.getEmail();
            hmacStr += "&code="+validateCode;

            //加密串 MD5值
            String md5Str = MD5Util.MD5(hmacStr);

            //加密串再次 DES加密
            String veryCode =  DesEncryption.encode(md5Str.getBytes(), "vci123456");

            //入库生成Id用于生成检测支付请求
            String validateUrl = "http://www.vcintegration.com/common/vemail/?id="+memLogId+"&verycode="+veryCode.replace("+", "_");

            //发送邮件
            String mailHtmlText = "<div style=\"width:520px; overflow:hidden; box-shadow: 0px 0px 3px #f1f1f1; -moz-box-shadow: 0px 0px 3px #444; -webkit-box-shadow: 0px 0px 3px #444; margin-top:80px; margin-left:auto; margin-right:auto;   font-family: Arial, Helvetica, sans-serif, Microsoft YaHei; font-size: 12px; color: #666666; \">";
            mailHtmlText += "<div style=\"width:520px; height:15px; background:#49134c; box-shadow: 0px 0px 3px #f1f1f1; -moz-box-shadow: 0px 0px 3px #444; -webkit-box-shadow: 0px 0px 3px #444; display:block\"></div>";
            mailHtmlText += "<div style=\"border:1px solid #949292; padding:30px; font-size:12px; line-height:16px;\">";
            mailHtmlText += "<p align=\"center\" style=\"padding-bottom: 20px;\"><img src=\"http://www.vcintegration.com/images/logo-s.jpg\" width=\"196\" height=\"65\" ></p>";
            mailHtmlText += "<p>Dear "+memberFrontVO.getName()+"<br><br>";

            mailHtmlText += "Thanks for taking your time to register. Please click the link below to complete registration: <br>";
            mailHtmlText += "<span><a href=\""+validateUrl+"\" style=\"color:#10cadb; text-decoration:underline\">";
            mailHtmlText += validateUrl;
            mailHtmlText += "</a></span><br>";
            mailHtmlText += "</p><br> ";
            mailHtmlText += "<p>Best Regards<br>";
            mailHtmlText += "VCIntegration Support Team<br>";
            mailHtmlText += "registration@vcintegration.com";
            mailHtmlText += "</p>";
            mailHtmlText += "</div>";
            mailHtmlText += "</div>";

            if(new EmailSender(GlobalConstant.MAILSERVERCES, GlobalConstant.SERVICESMAILPASSWORD, GlobalConstant.SERVICESMAILADDRESS).send(new String[]{memberFrontVO.getEmail()}, GlobalConstant.MAILTITLE, mailHtmlText, null, "text/html")){
                return JsonResult.success("Congratulations, on your successful registration!");
            }else{
                return JsonResult.error("The registration is successful, but failed to send email, please try again later!");
            }

        }else{
            return JsonResult.error("Sorry, registration failed!");
        }
    }

    /**
     * 忘记密码发送邮件
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/forgetpwd")
    public JsonResult forgetpwd(HttpServletRequest request, String email) throws Exception{

        HttpSession session = request.getSession(false);
        if(session == null){
            return JsonResult.error("Login and then modify the password!");
        }else{
            Members member = memberFrontService.queryMemberByEmail(email);
            //用户已登录
            if(member != null){//邮箱地址存在
                String validateCode = WebUtil.getNumStrByLength(6);
                Long mlid = new IdWorker().nextId();

                //插入记录
                MemEmailLogs maillog = new MemEmailLogs();
                maillog.setMelid(mlid);
                maillog.setState(StatusEnum.PROCESSING); //设置认证过程正在处理中,认证完成后设置为已处理
                maillog.setMemid(member.getMemid());
                maillog.setEmail(member.getEmail());
                maillog.setCode(validateCode);
                maillog.setAddtime(new Date());
                maillog.setStype(1);   //1表示找回密码邮件

                //组织邮件内容
                String hmacStr = "";
                hmacStr += "melid="+mlid;
                hmacStr += "&memid="+member.getMemid();
                hmacStr += "&email="+member.getEmail();
                hmacStr += "&code="+validateCode;

                //加密串 MD5值
                String md5Str = MD5Util.MD5(hmacStr);

                //加密串再次 DES加密
                String veryCode =  DesEncryption.encode(md5Str.getBytes(), "vci123456");

                //入库生成Id用于生成检测支付请求
                String validateUrl = "http://www.vcintegration.com/common/cemail/?id="+mlid+"&verycode="+veryCode.replace("+", "_");

                //发送邮件
                String mailHtmlText = "<div style=\"width:520px; overflow:hidden; box-shadow: 0px 0px 3px #f1f1f1; -moz-box-shadow: 0px 0px 3px #444; -webkit-box-shadow: 0px 0px 3px #444; margin-top:80px; margin-left:auto; margin-right:auto;   font-family: Arial, Helvetica, sans-serif, Microsoft YaHei; font-size: 12px; color: #666666; \">";
                mailHtmlText += "<div style=\"width:520px; height:15px; background:#49134c; box-shadow: 0px 0px 3px #f1f1f1; -moz-box-shadow: 0px 0px 3px #444; -webkit-box-shadow: 0px 0px 3px #444; display:block\"></div>";
                mailHtmlText += "<div style=\"border:1px solid #949292; padding:30px; font-size:12px; line-height:16px;\">";
                mailHtmlText += "<p align=\"center\" style=\"padding-bottom: 20px;\"><img src=\"http://www.vcintegration.com/images/logo-s.jpg\" width=\"196\" height=\"65\" ></p>";
                mailHtmlText += "<p>Dear "+member.getName()+"<br><br>";

                mailHtmlText += "Please click the link below to change your new password: <br>";
                mailHtmlText += "<span><a href=\""+validateUrl+"\" style=\"color:#10cadb; text-decoration:underline\">";
                mailHtmlText += validateUrl;
                mailHtmlText += "</a></span><br>";
                mailHtmlText += "</p><br> ";
                mailHtmlText += "<p>Best Regards<br>";
                mailHtmlText += "VCIntegration Support Team<br>";
                mailHtmlText += "registration@vcintegration.com";
                mailHtmlText += "</p>";
                mailHtmlText += "</div>";
                mailHtmlText += "</div>";

                if(new EmailSender(GlobalConstant.MAILSERVERCES, GlobalConstant.SERVICESMAILPASSWORD, GlobalConstant.SERVICESMAILADDRESS).send(new String[]{member.getEmail()}, GlobalConstant.CHANGE_MAILTITLE, mailHtmlText, null, "text/html")){
                    return JsonResult.success("Message sending success!");
                }else{
                    return JsonResult.error("Message sending failed!");
                }
            }else{
                return JsonResult.error("Invalid email address");
            }
        }
    }

    /**
     * 进入到个人信息页面
     * @return
     * @throws Exception
     */
    @GetMapping("/tocenter")
    public String tocenter(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession(false);
        if(session == null){
            response.sendRedirect("/");
            return null;
        }else{
            Object tempMem = session.getAttribute("member");
            if(tempMem != null){
                MemberFrontVO tempMember = (MemberFrontVO)tempMem;
                MemberFrontVO member = memberFrontService.queryFrontMemberByMemId(tempMember.getMemid()) ;
                session.setAttribute("member", member);
            }
            return "site/member/memberinfo";
        }
    }

    /**
     * 进入修改密码页面
     * @return
     * @throws Exception
     */
    @GetMapping("/tochangepwd")
    public String tochangepwd() throws Exception{
        return "site/member/changepwd";
    }

    /**
     * 用户修改密码
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/dochangepwd")
    public JsonResult dochangepwd(HttpServletRequest request, String oldpwd, String newpwd) throws Exception{

        HttpSession session = request.getSession(false);
        if(session == null){
            return JsonResult.error("Please log in and then operate!");
        }else{
            Object tempMem = session.getAttribute("member");
            if(tempMem != null){
                MemberFrontVO tempMember = (MemberFrontVO)tempMem;
                MemberFrontVO member = memberFrontService.queryFrontMemberByMemId(tempMember.getMemid()) ;
                if(member!=null){

                    if(member.getPassword().equals(MD5Util.MD5(oldpwd))){
                        Members pwdMember = new Members();
                        pwdMember.setPassword(MD5Util.MD5(newpwd));
                        pwdMember.setMemid(member.getMemid());
                        // 设置新密码
                        memberFrontService.updateMemberNewPwd(pwdMember);
                        return JsonResult.success("Success, Please remember your password!");
                    }else{
                        return JsonResult.error("old password error!");
                    }

                }else{
                    return JsonResult.error("No member info!");
                }
            }else{
                return JsonResult.error("Please log in and then operate!");
            }
        }

    }

    /**
     * 重置密码
     * @return
     * @throws Exception
     */
    @ResponseBody
    @PostMapping("/resetpwd")
    public JsonResult resetpwd(HttpServletRequest request, MemberFrontVO memberFrontVO) throws Exception{

        Members tempMemberInfo = memberFrontService.queryMemberByEmail(memberFrontVO.getEmail());
        if(tempMemberInfo == null){
            return JsonResult.error("have no the email");
        }

        //修改密码为新密码
        Members pwdMember = new Members();
        pwdMember.setPassword(MD5Util.MD5( memberFrontVO.getPassword() ));
        pwdMember.setMemid(tempMemberInfo.getMemid());

        Integer updateResult = memberFrontService.updateMemberNewPwd(pwdMember);

        if(updateResult > 0){
            // 清空session重新登录
            HttpSession session = request.getSession(false);
            if(session!=null){
                session.removeAttribute("member");
            }

            return JsonResult.success("password changed!");
        }else{
            return JsonResult.error("Mail address modification failed");
        }

    }

}

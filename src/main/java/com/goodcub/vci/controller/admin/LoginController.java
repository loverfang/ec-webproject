package com.goodcub.vci.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.goodcub.auth.exception.TokenException;
import com.goodcub.auth.exception.TokenExceptionCodeEnum;
import com.goodcub.common.annotation.UserLoginToken;
import com.goodcub.common.jwt.JwtHelper;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.SysUser;
import com.goodcub.vci.service.admin.SysUserService;
import com.goodcub.vci.vo.admin.SysUserVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/19
 * @Version V1.0
 **/
@Controller
@RequestMapping("api/manage")
public class LoginController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping(value = "/login")
    @ResponseBody
    public JsonResult login(@RequestBody SysUserVO sysUserVO, HttpServletRequest request){

        Map<String,Object> param = new HashMap<String, Object>();
        param.put("account", sysUserVO.getAccount());
        List<SysUser> userList = sysUserService.queryAccountList(param);

        if(userList!=null&&userList.size()>0){

            SysUser user = userList.get(0);
            if(user.getStatus().intValue() == -1 || user.getStatus().intValue() == 0){
                return JsonResult.error("账号已删除无法登陆!");
            }

            if(sysUserVO.getPassword().equals(user.getPassword())){
                //session.setAttribute(request, response, SYSUER_KEY, user.getUserId());

                // 查询出该用户拥有的权限，将其放入到session中，以便在拦截器中判断用户是否有操作权限
                //session.setAttribute(request, response, "actionList", (Serializable)sysUserManageService.allActionList(user.getRolesId()));

                System.out.println("登陆者浏览器信息:" + request.getHeader("User-Agent"));
                // 登录成功后讲jwt生成的token返回给登录端
                String jwtToken = JwtHelper.generateJWT(String.valueOf(user.getUserId()), user.getAccount(), request.getHeader("User-Agent"));

                Map<String,Object> data = new HashMap<>();
                data.put("token",jwtToken);
                return JsonResult.success(data);
            }else{
                return JsonResult.error("密码不正确!");
            }
        }else{
            return JsonResult.error("无该用户信息!");
        }
    }

    @PostMapping(value = "/logout")
    @ResponseBody
    public JsonResult logout(){
        return JsonResult.success();
    }

    @GetMapping(value = "/userInfo")
    @UserLoginToken
    @ResponseBody
    public JsonResult userInfo(String token){

        //解密Token由userId加载需要的数据
        String tokenStr = JwtHelper.validateLogin(token);
        if(tokenStr == null){
            throw new TokenException(TokenExceptionCodeEnum.TOKEN_IS_NULL);
        }

        JSONObject jsonObject = JSONObject.parseObject(tokenStr);
        Integer userId = jsonObject.getInteger("userId");
        String userName = jsonObject.getString("userName");

        SysUserVO sysUserVO = sysUserService.queryAccountInfo(userId);

        //查询数据库返回需要的信息
        return JsonResult.success(sysUserVO);
    }


}

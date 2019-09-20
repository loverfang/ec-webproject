package com.goodcub.auth.controller;

import com.goodcub.common.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/19
 * @Version V1.0
 **/
@Controller
@RequestMapping("manage")
public class UserLoginController {

    @GetMapping(value = "/login")
    public String to_login(){
        return "admin/login";
    }

    @PostMapping(value = "/dologin")
    @ResponseBody
    public JsonResult do_login(){
        return JsonResult.success();
    }
}

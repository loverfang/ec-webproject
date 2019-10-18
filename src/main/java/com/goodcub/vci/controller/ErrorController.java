package com.goodcub.vci.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/18
 * @Version V1.0
 **/
@Controller
@RequestMapping("error")
public class ErrorController {
    @GetMapping("/error_401")
    public String error401(){
        return "error/401";
    }
    @GetMapping("/error_404")
    public String error404(){
        return "error/404";
    }
    @GetMapping("/error_500")
    public String error500(){
        return "error/500";
    }
}

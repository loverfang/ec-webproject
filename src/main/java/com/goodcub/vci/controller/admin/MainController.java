package com.goodcub.vci.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/19
 * @Version V1.0
 **/
@Controller
@RequestMapping("manage")
public class MainController {

    @GetMapping({"/","/index"})
    public String to_main(){
        return "admin/index";
    }

}

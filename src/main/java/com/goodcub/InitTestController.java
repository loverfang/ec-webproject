package com.goodcub;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/19
 * @Version V1.0
 **/
@Controller
public class InitTestController {

    @GetMapping(value = "init")
    public String init(){
        return "admin/login";
    }

}

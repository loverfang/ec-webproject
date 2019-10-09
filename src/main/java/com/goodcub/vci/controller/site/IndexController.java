package com.goodcub.vci.controller.site;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/9
 * @Version V1.0
 **/
@Controller
public class IndexController {
    @RequestMapping({"/","/index"})
    public String home(){
        return "site/index";
    }
}

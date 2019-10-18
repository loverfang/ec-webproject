package com.goodcub.vci.controller.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.service.admin.MemberService;
import com.goodcub.vci.service.admin.NewsService;
import com.goodcub.vci.service.admin.VendorService;
import com.goodcub.vci.service.admin.VideosService;
import com.goodcub.vci.service.admin.MainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/19
 * @Version V1.0
 **/
@Controller
@RequestMapping("manage")
public class MainController {

    @GetMapping({"/", "/index"})
    public String to_main(){
        return "admin/index";
    }

}

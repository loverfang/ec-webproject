package com.goodcub.vci.controller.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.service.admin.AdvertiseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
@Controller
@RequestMapping("api/manage")
public class AdvertiseController {

    @Resource
    AdvertiseService advertiseService;

    @GetMapping("/advertiseList")
    @ResponseBody
    public JsonResult advertiseList(){
        return JsonResult.success(advertiseService.allAdvertiseList(null,1,10));
    }
}

package com.goodcub.vci.controller.admin;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.common.utils.JsonResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/9/23
 * @Version V1.0
 **/
@Controller
@RequestMapping("api/manage")
public class AdvertiseController {

    @GetMapping("advertiseList")
    public TableDataInfo advertiseList(){

        return null;
    }
}

package com.goodcub.vci.controller.admin;

import com.goodcub.common.enums.StatusEnum;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.Advertise;
import com.goodcub.vci.service.admin.AdvertiseService;
import com.goodcub.vci.vo.admin.AdvertiseVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

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
    public JsonResult advertiseList(
        @RequestParam(value = "adtitle", required = false)String adtitle,
        @RequestParam(value = "page", required = false)Integer page,
        @RequestParam(value = "limit", required = false)Integer limit){

        Map<String,Object> params = new HashMap<>();
        params.put("adtitle",adtitle);
        return JsonResult.success(advertiseService.queryAdvertiseVoList(params,page,limit));
    }

    @PostMapping("/saveAdvertise")
    @ResponseBody
    public JsonResult saveAdvertise(@RequestBody AdvertiseVO advertiseVO){
        Advertise advertise = new Advertise();
        advertise.setAdtitle(advertiseVO.getAdtitle());
        advertise.setAdurl(advertiseVO.getAdurl());
        advertise.setCoverImg(advertiseVO.getCoverImg());

        advertise.setSindex(1);
        advertise.setStatus(StatusEnum.NORMAL);
        advertise.setAddtime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()));

        advertiseService.saveAdvertise(advertise);
        return JsonResult.success();
    }

    @PostMapping("/updateAdvertise")
    @ResponseBody
    public JsonResult updateAdvertise(@RequestBody AdvertiseVO advertiseVO){
        Advertise advertise = new Advertise();
        advertise.setAdid(advertiseVO.getAdid());
        advertise.setAdtitle(advertiseVO.getAdtitle());
        advertise.setAdurl(advertiseVO.getAdurl());
        advertise.setCoverImg(advertiseVO.getCoverImg());

        advertiseService.updateAdvertise(advertise);
        return JsonResult.success();
    }

    @PostMapping("/updateAdvertiseSindex")
    @ResponseBody
    public JsonResult updateAdvertiseSindex(@RequestBody AdvertiseVO advertiseVO){
        Advertise advertise = new Advertise();
        advertise.setAdid(advertiseVO.getAdid());
        advertise.setSindex(advertiseVO.getSindex());
        advertiseService.updateAdvertise(advertise);
        return JsonResult.success();
    }

    @PostMapping("/updateAdvertiseSatus")
    @ResponseBody
    public JsonResult updateAdvertiseSatus(@RequestBody AdvertiseVO advertiseVO){
        Advertise advertise = new Advertise();
        advertise.setAdid(advertiseVO.getAdid());
        advertise.setStatus(advertiseVO.getStatus());
        advertiseService.updateAdvertise(advertise);
        return JsonResult.success();
    }

    @PostMapping("/deleteAdvertise")
    @ResponseBody
    public JsonResult delete(@RequestBody Map<String,Object> data){
        // 获取传回来的id字符串
        String ids_str = data.get("adids").toString();
        if(ids_str == null){
            return JsonResult.error("请选中需要删除的记录");
        }

        // 通过逗号分割字符串，获得所有的id，在mapper中通过mybatis提供的动态循环遍历并删除数组中对应id的值就行
        String[] idsArr = ids_str.split(",");
        // 根据自己的后台逻辑，调用service的方法，我就不写了

        List<Integer> idList = new ArrayList<>();
        if(idsArr != null && idsArr.length>0){
            for (String id: idsArr){
                if(id!=null) {
                    idList.add(Integer.valueOf(id));
                }
            }
        }

        advertiseService.deleteAdvertise(idList);
        return JsonResult.success();
    }

}

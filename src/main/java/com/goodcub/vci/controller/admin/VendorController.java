package com.goodcub.vci.controller.admin;

import com.goodcub.common.enums.RecomendEnum;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.Vendor;
import com.goodcub.vci.service.admin.VendorService;
import com.goodcub.vci.vo.admin.VendorInfoVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName VendorController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/61:08
 * @Version 1.0
 */
@Controller
@RequestMapping("api/manage")
public class VendorController {
    @Resource
    VendorService vendorService;

    @GetMapping("/vendorList")
    @ResponseBody
    public JsonResult queryNewsList(
            @RequestParam(value = "name", required = false)String name,
            @RequestParam(value = "page", required = false)Integer page,
            @RequestParam(value = "limit", required = false)Integer limit) {

        Map<String, Object> param = new HashMap<>();
        param.put("name", name);
        return JsonResult.success(vendorService.queryVendorList(param, page, limit));
    }

    @PostMapping("/saveVendor")
    @ResponseBody
    public JsonResult saveVendor(@RequestBody VendorInfoVO vendorInfoVO){
        Vendor vendor = new Vendor();
        vendor.setCid(vendorInfoVO.getCid());
        vendor.setName(vendorInfoVO.getName());;
        vendor.setContent(vendorInfoVO.getContent());
        vendor.setCoverImg(vendorInfoVO.getCoverImg());
        vendor.setIntro(vendorInfoVO.getIntro());
        vendor.setDoman(vendorInfoVO.getDoman());
        vendor.setInfo(vendorInfoVO.getInfo());
        vendor.setIntroduction(vendorInfoVO.getIntroduction());
        vendor.setSolutions(vendorInfoVO.getSolutions());
        vendor.setHighlights(vendorInfoVO.getHighlights());
        vendor.setPkeywords(vendorInfoVO.getPkeywords());
        vendor.setPtitle(vendorInfoVO.getPtitle());
        vendor.setPdescription(vendorInfoVO.getPdescription());

        vendor.setSindex(1);
        vendor.setIfindex(RecomendEnum.N);
        vendor.setViewcount(0);
        vendor.setPubtime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss", new Date()));

        vendorService.insertVendor(vendor);
        return JsonResult.success();
    }

    @PostMapping("/updateVendor")
    @ResponseBody
    public JsonResult updateVendor(@RequestBody VendorInfoVO vendorInfoVO){
        Vendor vendor = new Vendor();
        vendor.setPid(vendorInfoVO.getPid());
        vendor.setCid(vendorInfoVO.getCid());
        vendor.setName(vendorInfoVO.getName());;
        vendor.setContent(vendorInfoVO.getContent());
        vendor.setCoverImg(vendorInfoVO.getCoverImg());
        vendor.setIntro(vendorInfoVO.getIntro());
        vendor.setDoman(vendorInfoVO.getDoman());
        vendor.setInfo(vendorInfoVO.getInfo());
        vendor.setIntroduction(vendorInfoVO.getIntroduction());
        vendor.setSolutions(vendorInfoVO.getSolutions());
        vendor.setHighlights(vendorInfoVO.getHighlights());
        vendor.setPkeywords(vendorInfoVO.getPkeywords());
        vendor.setPtitle(vendorInfoVO.getPtitle());
        vendor.setPdescription(vendorInfoVO.getPdescription());

        vendor.setSindex(vendorInfoVO.getSindex());
        vendor.setIfindex(vendorInfoVO.getIfindex());
        vendor.setViewcount(vendorInfoVO.getViewcount());
        vendor.setPubtime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss", new Date()));

        vendorService.updateVendor(vendor);
        return JsonResult.success();
    }

    @PostMapping("/updateVendorSindex")
    @ResponseBody
    public JsonResult updateVendorSindex(@RequestBody VendorInfoVO vendorInfoVO){
        Vendor vendor = new Vendor();
        vendor.setPid(vendorInfoVO.getPid());
        vendor.setSindex(vendorInfoVO.getSindex());
        vendorService.updateVendor(vendor);
        return JsonResult.success();
    }

    @PostMapping("/deleteVendor")
    @ResponseBody
    public JsonResult deleteVendor(@RequestBody Map<String,Object> data){
        // 获取传回来的id字符串
        String ids_str = data.get("pids").toString();
        if(ids_str == null || "".equals(ids_str)){
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

        Map<String,Object> param = new HashMap<>();
        param.put("source","vendor");
        param.put("idList",idList);
        vendorService.deleteVendor(param);
        return JsonResult.success();
    }

    @GetMapping("/vendorInfo")
    @ResponseBody
    public JsonResult queryCategoryInfo(Integer pid){
        return JsonResult.success(vendorService.queryVendorInfoByPid(pid));
    }

}

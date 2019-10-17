package com.goodcub.vci.controller.admin;

import com.goodcub.common.enums.StatusEnum;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.NewsAd;
import com.goodcub.vci.service.admin.NewsAdService;
import com.goodcub.vci.vo.admin.NewsAdVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @ClassName NewsPdfController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/616:21
 * @Version 1.0
 */
@Controller
@RequestMapping("api/manage")
public class NewsAdController {

    @Resource
    NewsAdService newsAdService;

    // 根据ntype加载新闻列表
    @GetMapping("/adList")
    @ResponseBody
    public JsonResult queryPdfList(
            @RequestParam(value = "id", required = false)String id,
            @RequestParam(value = "page", required = false)Integer page,
            @RequestParam(value = "limit", required = false)Integer limit){

        Map<String,Object> param = new HashMap<>();
        param.put("nid",id);
        return JsonResult.success(newsAdService.queryNewsAdList(param, page, limit));
    }


    @PostMapping("/saveAd")
    @ResponseBody
    public JsonResult savePdf(@RequestBody NewsAdVO newsAdVO){
        NewsAd newsAd = new NewsAd();
        newsAd.setNid(newsAdVO.getNid());
        newsAd.setTitle(newsAdVO.getTitle());
        newsAd.setCoverImg(newsAdVO.getCoverImg());
        newsAd.setLink(newsAdVO.getLink());
        newsAd.setState(StatusEnum.NORMAL);
        newsAd.setUptime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()));

        newsAdService.saveNewsAd(newsAd);
        return JsonResult.success();
    }

    @PostMapping("/updateAd")
    @ResponseBody
    public JsonResult updatePdf(@RequestBody NewsAdVO newsAdVO){
        NewsAd newsAd = new NewsAd();
        newsAd.setNid(newsAdVO.getNid());
        newsAd.setTitle(newsAdVO.getTitle());
        newsAd.setCoverImg(newsAdVO.getCoverImg());
        newsAd.setLink(newsAdVO.getLink());

        newsAdService.updateNewsAd(newsAd);
        return JsonResult.success();
    }

    @PostMapping("/deleteAd")
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

        newsAdService.deleteNewsAdByAid(idList);
        return JsonResult.success();
    }

}

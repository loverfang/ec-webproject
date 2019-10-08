package com.goodcub.vci.controller.admin;

import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.News;
import com.goodcub.vci.service.admin.NewsService;
import com.goodcub.vci.vo.admin.SingleNewsVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName SingleNewsController
 * @Description 单篇新闻管理
 * @Author Luo.z.x
 * @Date 2019/10/211:04
 * @Version 1.0
 */
@Controller
@RequestMapping("api/manage")
public class SingleNewsController {
    @Resource
    NewsService newsService;

    // 根据ntype加载单篇新闻
    @GetMapping("/querySignleNews")
    @ResponseBody
    public JsonResult querySignleNews(String ntype){
        return JsonResult.success( newsService.querySingleNews(ntype.toUpperCase()));
    }

    // 修改ntype对应的新闻内容
    @PostMapping("/updateSignleNews")
    @ResponseBody
    public JsonResult updateSignleNews(@RequestBody SingleNewsVO singleNewsVO){
        News news = new News();
        news.setNid(singleNewsVO.getNid());
        news.setNtype(singleNewsVO.getNtype());
        news.setPtitle(singleNewsVO.getPtitle());
        news.setPkeywords(singleNewsVO.getPkeywords());
        news.setPdescription(singleNewsVO.getPdescription());
        news.setContent(singleNewsVO.getContent());
        return JsonResult.success( newsService.updateSingleNews(news));
    }

}

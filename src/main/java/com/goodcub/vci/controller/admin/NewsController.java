package com.goodcub.vci.controller.admin;

import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.News;
import com.goodcub.vci.service.admin.NewsService;
import com.goodcub.vci.vo.admin.SingleNewsVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @ClassName NewsController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/277:32
 * @Version 1.0
 */
@Controller
@RequestMapping("api/manage")
public class NewsController {

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
        return JsonResult.success( newsService.updateNews(news));
    }

    // 根据ntype加载新闻列表
    @GetMapping("/newsList")
    @ResponseBody
    public JsonResult queryNewsList(
        @RequestParam(value = "ntype", required = false)String ntype,
        @RequestParam(value = "title", required = false)String title,
        @RequestParam(value = "page", required = false)Integer page,
        @RequestParam(value = "limit", required = false)Integer limit){

        Map<String,Object> param = new HashMap<>();
        param.put("ntype",ntype);
        param.put("title",title);

        return JsonResult.success(newsService.queryNewsList(param, page, limit));
    }





    // 根据id加载新闻

    // 修改新闻装
}

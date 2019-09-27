package com.goodcub.vci.controller.admin;

import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.News;
import com.goodcub.vci.service.admin.NewsService;
import com.goodcub.vci.vo.admin.SingleNewsVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
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
    public JsonResult querySignleNews(String ntype){
        return JsonResult.success( newsService.querySingleNews(ntype));
    }

    // 修改ntype对应的新闻内容
    @GetMapping("/updateSignleNews")
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

    // 根据id加载新闻

    // 修改新闻装
}

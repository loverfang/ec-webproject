package com.goodcub.vci.controller.site;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName NewsFrontController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/118:11
 * @Version 1.0
 */
@Controller
public class NewsFrontController {

    /**
     * 单篇文章查询
     * @return
     */
    @RequestMapping()
    public String singleNews(){
        return null;
    }

    /**
     * 列表文章查询
     * @return
     */
    public String newsList(){
        return null;
    }

}

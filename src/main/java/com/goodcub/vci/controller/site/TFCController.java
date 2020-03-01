package com.goodcub.vci.controller.site;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.service.site.TfcFrontService;
import com.goodcub.vci.vo.site.TfcTypeFrontVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TFCController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2020/3/110:57
 * @Version 1.0
 */
@Controller
public class TFCController {

    @Resource
    private TfcFrontService tfcFrontService;

    @RequestMapping("/tfcvideos")
    public String tfcVideoList(HttpServletRequest request,
        @RequestParam(value = "typeId", required = false)Integer typeId,
        @RequestParam(value = "title", required = false)Integer title,
        @RequestParam(value = "page", required = false)Integer page,
        @RequestParam(value = "limit", required = false)Integer limit){

        // 类别列表
        List<TfcTypeFrontVO> tfcTypeFrontList = tfcFrontService.queryTfcTypeList();
        request.setAttribute("tfcTypeFrontList", tfcTypeFrontList);

        // 初始化分页默认数据
        if(page == null || "".equals(page)){
            page = 1;
        }

        if(limit == null || "".equals(limit)){
            limit = 6;
        }

        // 视频列表
        Map<String,Object> params = new HashMap<>();
        params.put("typeId", typeId);
        params.put("title", title);
        TableDataInfo tableDataInfo = tfcFrontService.queryTfcFrontVOList(params, page, limit);

        request.setAttribute("data", tableDataInfo);
        request.setAttribute("ntype", "tfcvideos");
        request.setAttribute("typeId", typeId==null?0:typeId);

        return "site/tfcvideolist";
    }
}

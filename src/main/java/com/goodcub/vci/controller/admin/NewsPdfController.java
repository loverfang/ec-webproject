package com.goodcub.vci.controller.admin;

import com.goodcub.common.enums.StatusEnum;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.NewsPdf;
import com.goodcub.vci.service.admin.NewsPdfService;
import com.goodcub.vci.vo.admin.NewsPdfVO;
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
public class NewsPdfController {

    @Resource
    NewsPdfService newsPdfService;

    // 根据ntype加载新闻列表
    @GetMapping("/pdfList")
    @ResponseBody
    public JsonResult queryPdfList(
            @RequestParam(value = "id", required = false)String id,
            @RequestParam(value = "source", required = false)String source,
            @RequestParam(value = "page", required = false)Integer page,
            @RequestParam(value = "limit", required = false)Integer limit){

        Map<String,Object> param = new HashMap<>();
        param.put("nid",id);
        param.put("source",source);
        return JsonResult.success(newsPdfService.queryNewsPdfList(param, page, limit));
    }

    @PostMapping("/savePdf")
    @ResponseBody
    public JsonResult savePdf(@RequestBody NewsPdfVO newsPdfVO){
        NewsPdf newsPdf = new NewsPdf();
        newsPdf.setNid(newsPdfVO.getNid());
        newsPdf.setSource(newsPdfVO.getSource());
        newsPdf.setName(newsPdfVO.getName());
        newsPdf.setCoverImg(newsPdfVO.getCoverImg());
        newsPdf.setPdfPath(newsPdfVO.getPdfPath());
        newsPdf.setPdfname(newsPdfVO.getPdfname());
        newsPdf.setIntor(newsPdfVO.getIntor());
        newsPdf.setPsize(newsPdfVO.getPsize());

        // 设置默认值
        newsPdf.setIsdisplay("Y");
        newsPdf.setIsupfile("Y");
        newsPdf.setState(StatusEnum.NORMAL);
        newsPdf.setSindex(1);
        newsPdf.setDowncount(0);
        newsPdf.setAddtime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()));
        newsPdfService.saveNewsPdf(newsPdf);

        return JsonResult.success();
    }

    @PostMapping("/updatePdf")
    @ResponseBody
    public JsonResult updatePdf(@RequestBody NewsPdfVO newsPdfVO){
        NewsPdf newsPdf = new NewsPdf();
        // 设置需要更新的ID
        newsPdf.setPid(newsPdfVO.getPid());

        // 更新基本信息
        newsPdf.setName(newsPdfVO.getName());
        newsPdf.setIntor(newsPdfVO.getIntor());

        // 更新封面图片
        newsPdf.setCoverImg(newsPdfVO.getCoverImg());

        // 更新PDF文件信息
        newsPdf.setPdfPath(newsPdfVO.getPdfPath());
        newsPdf.setPdfname(newsPdfVO.getPdfname());
        newsPdf.setPsize(newsPdfVO.getPsize());

        newsPdfService.updateNewsPdf(newsPdf);
        return JsonResult.success();
    }

    @PostMapping("/updatePdfSindex")
    @ResponseBody
    public JsonResult updatePdfSindex(@RequestBody NewsPdfVO newsPdfVO){
        NewsPdf newsPdf = new NewsPdf();
        newsPdf.setPid(newsPdfVO.getPid());
        newsPdf.setSindex(newsPdfVO.getSindex());
        newsPdfService.updateNewsPdf(newsPdf);

        return JsonResult.success();
    }


    @PostMapping("/deletePdf")
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

        newsPdfService.deleteNewsPdfByPid(idList);
        return JsonResult.success();
    }

}

package com.goodcub.vci.controller.admin;

import com.goodcub.common.enums.StatusEnum;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.TfcType;
import com.goodcub.vci.service.admin.TfcTypeService;
import com.goodcub.vci.vo.admin.TfcTypeVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TfcTypeController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/516:39
 * @Version 1.0
 */
@Controller
@RequestMapping("api/manage")
public class TfcTypeController {

    @Resource
    TfcTypeService tfcTypeService;

    // 根据cname加载Catory类别列表
    @GetMapping("/tfcTypeList")
    @ResponseBody
    public JsonResult queryCategoryList(@RequestParam(value = "typeName", required = false)String typeName,
        @RequestParam(value = "page", required = false)Integer page,
        @RequestParam(value = "limit", required = false)Integer limit){

        Map<String,Object> param = new HashMap<>();
        param.put("typeName",typeName);
        return JsonResult.success(tfcTypeService.queryTfcTypeList(param, page, limit));
    }

    @PostMapping("/saveTfcType")
    @ResponseBody
    public JsonResult saveCategory(@RequestBody TfcTypeVO tfcTypeVO){
        TfcType tfcType = new TfcType();
        tfcType.setTypeName(tfcTypeVO.getTypeName());
        tfcType.setTstate(StatusEnum.NORMAL);
        tfcType.setSindex(1);
        tfcType.setMemo(tfcTypeVO.getMemo());
        tfcType.setAddTime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()));
        tfcTypeService.insertTfcType(tfcType);
        return JsonResult.success();
    }

    @PostMapping("/updateTfcType")
    @ResponseBody
    public JsonResult updateCategory(@RequestBody TfcTypeVO tfcTypeVO){
        TfcType tfcType = new TfcType();
        tfcType.setTypeId(tfcTypeVO.getTypeId());
        tfcType.setTypeName(tfcTypeVO.getTypeName());
        tfcType.setMemo(tfcTypeVO.getMemo());
        tfcTypeService.updateTfcType(tfcType);
        return JsonResult.success();
    }

    @PostMapping("/updateTfcTypeStatus")
    @ResponseBody
    public JsonResult updateStatus(@RequestBody TfcTypeVO tfcTypeVO){
        TfcType tfcType = new TfcType();
        tfcType.setTypeId(tfcTypeVO.getTypeId());
        tfcType.setTstate(tfcTypeVO.getTstate());
        tfcTypeService.updateTfcType(tfcType);
        return JsonResult.success();
    }

    @PostMapping("/updateTfcTypeSindex")
    @ResponseBody
    public JsonResult updateSindex(@RequestBody TfcTypeVO tfcTypeVO){
        TfcType tfcType = new TfcType();
        tfcType.setTypeId(tfcTypeVO.getTypeId());
        tfcType.setSindex(tfcTypeVO.getSindex());
        tfcTypeService.updateTfcType(tfcType);

        tfcTypeService.updateTfcType(tfcType);
        return JsonResult.success();
    }

    @GetMapping("/queryTfcTypeOptions")
    @ResponseBody
    public JsonResult queryOptions(){
        return JsonResult.success(tfcTypeService.queryTfcTypeOptions());
    }

}

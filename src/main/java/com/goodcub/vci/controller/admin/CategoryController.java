package com.goodcub.vci.controller.admin;

import com.goodcub.common.enums.StatusEnum;
import com.goodcub.common.utils.DateUtil;
import com.goodcub.common.utils.JsonResult;
import com.goodcub.vci.entity.Category;
import com.goodcub.vci.service.admin.CategoryService;
import com.goodcub.vci.vo.admin.CategoryVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CategoryController
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/516:39
 * @Version 1.0
 */
@Controller
@RequestMapping("api/manage")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    // 根据cname加载Catory类别列表
    @GetMapping("/categoryList")
    @ResponseBody
    public JsonResult queryCategoryList(@RequestParam(value = "cname", required = false)String cname,
        @RequestParam(value = "page", required = false)Integer page,
        @RequestParam(value = "limit", required = false)Integer limit){

        Map<String,Object> param = new HashMap<>();
        param.put("cname",cname);
        return JsonResult.success(categoryService.queryCategoryList(param, page, limit));
    }

    @PostMapping("/saveCategory")
    @ResponseBody
    public JsonResult saveCategory(@RequestBody CategoryVO categoryVO){
        Category category = new Category();
        category.setPid(categoryVO.getPid());
        category.setCname(categoryVO.getCname());
        category.setMemo(categoryVO.getMemo());
        category.setCstate(StatusEnum.NORMAL);
        category.setSindex(1);
        category.setPubtime(DateUtil.parseDateToStr("yyyy-MM-dd HH:mm:ss",new Date()));
        categoryService.insertCategory(category);
        return JsonResult.success();
    }

    @PostMapping("/updateCategory")
    @ResponseBody
    public JsonResult updateCategory(@RequestBody CategoryVO categoryVO){
        Category category = new Category();
        category.setCid(categoryVO.getCid());
        category.setCname(categoryVO.getCname());
        category.setMemo(categoryVO.getMemo());
        categoryService.updateCategory(category);
        return JsonResult.success();
    }

    @PostMapping("/updateCategoryStatus")
    @ResponseBody
    public JsonResult updateStatus(@RequestBody CategoryVO categoryVO){
        Category category = new Category();
        category.setCid(categoryVO.getCid());
        category.setCstate(categoryVO.getCstate());
        categoryService.updateCategory(category);
        return JsonResult.success();
    }

    @PostMapping("/updateCategorySindex")
    @ResponseBody
    public JsonResult updateSindex(@RequestBody CategoryVO categoryVO){
        Category category = new Category();
        category.setCid(categoryVO.getCid());
        category.setSindex(categoryVO.getSindex());

        categoryService.updateCategory(category);
        return JsonResult.success();
    }

    @GetMapping("/queryOptions")
    @ResponseBody
    public JsonResult queryOptions(){
        return JsonResult.success(categoryService.queryCategoryOptions());
    }


}

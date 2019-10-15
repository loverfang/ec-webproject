package com.goodcub.vci.controller.site;

import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.service.site.CategoryFrontService;
import com.goodcub.vci.service.site.NewsPdfFrontService;
import com.goodcub.vci.service.site.VendorFrontService;
import com.goodcub.vci.vo.site.CategoryFrontVO;
import com.goodcub.vci.vo.site.CategoryListFrontVO;
import com.goodcub.vci.vo.site.VendorFrontVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
@Controller
public class VendorFrontController {

    @Resource
    CategoryFrontService categoryFrontService;
    @Resource
    VendorFrontService vendorFrontService;
    @Resource
    NewsPdfFrontService newsPdfFrontService;

    @GetMapping({"/vendors","/vendors_search/{cid}/"})
    public String vendorAllList(HttpServletRequest request,
        @PathVariable("cid") Integer cid,
        @RequestParam(value = "name", required = false)String name,
        @RequestParam(value = "page", required = false)Integer page,
        @RequestParam(value = "limit", required = false)Integer limit){

        Map<String,Object> catoryParam = new HashMap<String, Object>();
        catoryParam.put("pid", 1);
        catoryParam.put("cstate", "NORMAL");

        // 供应商列表类别,用于页面下拉
        List<CategoryListFrontVO> categoryList = categoryFrontService.queryCategoryFrontList(catoryParam);
        request.setAttribute("cid", null);
        request.setAttribute("categoryList", categoryList);

        Map<String,Object> param = new HashMap<String, Object>();
        param.put("cid", null);
        param.put("name", null);

        // 初始化分页默认数据
        if(page == null || "".equals(page)){
            page = 1;
        }

        if(limit == null || "".equals(limit)){
            limit = 10;
        }

        // 分页查询出指定类别下的
        TableDataInfo tableDataInfo = vendorFrontService.queryVendorFrontList(param, page, limit);

        request.setAttribute("data", tableDataInfo);
        return "site/vendorlist";
    }

//    @GetMapping("/vendors/{cid}/")
//    public String vendorQueryList(HttpServletRequest request,
//        @PathVariable("cid") Integer cid,
//        @RequestParam(value = "name", required = false)String name,
//        @RequestParam(value = "page", required = false)Integer page,
//        @RequestParam(value = "limit", required = false)Integer limit){
//
//        Map<String,Object> catoryParam = new HashMap<String, Object>();
//        catoryParam.put("pid", 1);
//        catoryParam.put("cstate", "NORMAL");
//
//        // 供应商列表类别,用于页面下拉
//        List<CategoryListFrontVO> categoryList = categoryFrontService.queryCategoryFrontList(catoryParam);
//        request.setAttribute("cid", cid);
//        request.setAttribute("categoryList", categoryList);
//
//        Map<String,Object> param = new HashMap<String, Object>();
//        param.put("cid", cid);
//        param.put("name", name);
//
//        // 初始化分页默认数据
//        if(page == null || "".equals(page)){
//            page = 1;
//        }
//
//        if(limit == null || "".equals(limit)){
//            limit = 10;
//        }
//
//        // 分页查询出指定类别下的
//        TableDataInfo tableDataInfo = vendorFrontService.queryVendorFrontList(param, page, limit);
//
//        request.setAttribute("data", tableDataInfo);
//        return "site/vendorlist";
//    }


    @GetMapping("/vendors/{pid}.html")
    public String detail(HttpServletRequest request, @PathVariable("pid") Integer pid) throws Exception{

        // 查询指定ID的产品(Vendor)详情
        VendorFrontVO vendorFrontVO = vendorFrontService.queryVendorFrontInfo(pid);

        //if (vendorFrontVO == null){
            // 抛出没有找到该文章的异常
        //}
        request.setAttribute("vendor", vendorFrontVO);


        // 得到指定产品的Category类别信息
        CategoryFrontVO currentCategory = categoryFrontService.queryCategoryFrontInfo(vendorFrontVO.getCid());
        request.setAttribute("currentCategory", currentCategory);


        // 供应商列表类别,用于页面下拉
        Map<String,Object> catoryParam = new HashMap<String, Object>();
        catoryParam.put("pid", 1);
        catoryParam.put("cstate", "NORMAL");
        List<CategoryListFrontVO> categoryList = categoryFrontService.queryCategoryFrontList(catoryParam);
        request.setAttribute("cid", vendorFrontVO.getCid());
        request.setAttribute("categoryList", categoryList);


        // 获取供应商下的PDF文件信息
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("nid", pid);
        param.put("source", "vendor");
        // Vendor只取它下的第一页的第一条记录
        TableDataInfo pdfList = newsPdfFrontService.queryNewsPdfFrontList(param,1,1);
        request.setAttribute("pdfList", pdfList.getItems());
        return "site/vendor_context";

    }

}

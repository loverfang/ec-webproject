package com.goodcub.vci.controller.site;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/12
 * @Version V1.0
 **/
@Controller
public class VendorFrontController {

    @GetMapping("/vendors")
    public String vendorList()throws Exception{
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("cid", cid);
        param.put("name", name);


        Map<String,Object> catoryParam = new HashMap<String, Object>();
        catoryParam.put("pid", 1);


        //供应商列表类别
        List<Category> categoryList = CommonDAO.commonQueryForList("category.getAll", catoryParam);
        request.setAttribute("categoryList", categoryList);


        //查询传入的cid和它的所有子类别ID的产品列表
        commonSearchList("products.frontAll", param, 3);

        return "VENDORSLIST";
    }


    public String detail() throws Exception{

        //查询单个产品
        Products products = (Products) CommonDAO.commonQueryForObject("products.getOne", pid);
        request.setAttribute("product",products);

        Category currentCategory = (Category) CommonDAO.commonQueryForObject("category.getOne", products.getCid());
        request.setAttribute("currentCategory", currentCategory);

        Map<String,Object> catoryParam = new HashMap<String, Object>();
        catoryParam.put("pid", 1);

        //供应商列表类别
        List<Category> categoryList = CommonDAO.commonQueryForList("category.getAll", catoryParam);
        request.setAttribute("categoryList", categoryList);

        Map<String,Object> param = new HashMap<String,Object>();

        param.put("nid", pid);
        param.put("source", "vender");

        List<NewsPdf> pdfList = CommonDAO.commonQueryForList("news_pdf.getAll", param, 0, 1);

        request.setAttribute("pdfList", pdfList);

        return "VENDORSDETAIL";
    }

}

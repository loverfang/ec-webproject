package com.goodcub.vci.service.site.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.mapper.NewsPdfMapper;
import com.goodcub.vci.service.site.NewsPdfFrontService;
import com.goodcub.vci.vo.site.NewsPdfFrontVO;
import com.goodcub.vci.vo.site.NewsPdfListFrontVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NewsPdfFrontServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/1323:51
 * @Version 1.0
 */
@Service
public class NewsPdfFrontServiceImpl implements NewsPdfFrontService {

    @Resource
    NewsPdfMapper newsPdfMapper;

    @Override
    public TableDataInfo queryNewsPdfFrontList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize,"addtime desc");
        List<NewsPdfListFrontVO> vendorList = newsPdfMapper.queryNewsPdfFrontList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<NewsPdfListFrontVO> pageInfo = new PageInfo<>(vendorList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setTotal(pageInfo.getTotal());
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setPageNum(pageInfo.getPageNum());
        tableDataInfo.setPageSize(pageInfo.getPageSize());
        tableDataInfo.setPages(pageInfo.getPages());

        return tableDataInfo;
    }

    @Override
    public NewsPdfFrontVO queryNewsPdfFrontInfo(Integer pid) {
        return newsPdfMapper.queryNewsPdfFrontInfo(pid);
    }
}

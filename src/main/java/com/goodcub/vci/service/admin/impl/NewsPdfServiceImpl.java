package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.Members;
import com.goodcub.vci.entity.NewsPdf;
import com.goodcub.vci.mapper.NewsPdfMapper;
import com.goodcub.vci.service.admin.NewsPdfService;
import com.goodcub.vci.vo.admin.NewsPdfVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NewsPdfServiceImpl
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/616:27
 * @Version 1.0
 */
@Service
public class NewsPdfServiceImpl implements NewsPdfService {

    @Resource
    NewsPdfMapper newsPdfMapper;

    @Override
    public TableDataInfo queryNewsPdfList(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize,"sindex asc ,addtime desc");
        List<NewsPdfVO> newsPdfList = newsPdfMapper.queryNewsPdfList(params);

        // 需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        PageInfo<NewsPdfVO> pageInfo = new PageInfo<>(newsPdfList, pageSize);

        TableDataInfo tableDataInfo = new TableDataInfo();
        tableDataInfo.setItems(pageInfo.getList());
        tableDataInfo.setTotal(pageInfo.getTotal());
        return tableDataInfo;
    }

    @Override
    public Integer saveNewsPdf(NewsPdf newsPdf) {
        return newsPdfMapper.saveNewsPdf(newsPdf);
    }

    @Override
    public Integer updateNewsPdf(NewsPdf newsPdf) {
        return newsPdfMapper.updateNewsPdf(newsPdf);
    }

    @Override
    public Integer deleteNewsPdf(Map<String, Object> param) {
        return newsPdfMapper.deleteNewsPdfByNid(param);
    }

    @Override
    public Integer deleteNewsPdfByPid(List<Integer> idList) {
        return newsPdfMapper.deleteNewsPdfByPid(idList);
    }
}

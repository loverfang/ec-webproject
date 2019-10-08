package com.goodcub.vci.service.admin.impl;

import com.github.pagehelper.PageHelper;
import com.goodcub.common.page.TableDataInfo;
import com.goodcub.vci.entity.NewsPdf;
import com.goodcub.vci.mapper.NewsPdfMapper;
import com.goodcub.vci.service.admin.NewsPdfService;
import com.goodcub.vci.vo.admin.NewsPdfVO;
import com.goodcub.vci.vo.admin.NewsPhotoVO;
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

        TableDataInfo TableDataInfo = new TableDataInfo();
        TableDataInfo.setTotal(((List) newsPdfList).size());
        TableDataInfo.setItems(newsPdfList);
        return TableDataInfo;
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
        return newsPdfMapper.deleteNewsPdf(param);
    }

    @Override
    public Integer deleteNewsPdfByPid(List<Integer> idList) {
        return newsPdfMapper.deleteNewsPdfByPid(idList);
    }
}

package com.goodcub.vci.service.site.impl;

import com.goodcub.vci.mapper.VciFilesMapper;
import com.goodcub.vci.service.site.VciFilesFrontService;
import com.goodcub.vci.vo.site.VciFileFrontVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/21
 * @Version V1.0
 **/
@Service
public class VciFilesFrontServiceImpl implements VciFilesFrontService {

    @Resource
    VciFilesMapper vciFilesMapper;

    @Override
    public VciFileFrontVO queryVciFile(Long fid) {
        return vciFilesMapper.queryVciFileByFid(fid);
    }
}

package com.goodcub.vci.service.site;

import com.goodcub.vci.vo.site.NewsPhotoListFrontVO;

import java.util.List;
import java.util.Map;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/15
 * @Version V1.0
 **/
public interface NewsPhotoFrontService {
    List<NewsPhotoListFrontVO> queryNewsPhotoFrontList(Long nid);
}

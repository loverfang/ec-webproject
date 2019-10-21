package com.goodcub.vci.service.site;

import com.goodcub.vci.vo.site.NewsAdListFrontVO;
import com.goodcub.vci.vo.site.NewsPhotoListFrontVO;

import java.util.List;

/**
 * @Author Luo.z.x
 * @Description: TODO
 * @Date 2019/10/15
 * @Version V1.0
 **/
public interface NewsAdFrontService {
    List<NewsAdListFrontVO> queryNewsAdFrontList(Long nid);
}

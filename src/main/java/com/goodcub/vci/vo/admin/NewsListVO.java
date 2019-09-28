package com.goodcub.vci.vo.admin;

import com.goodcub.vci.entity.News;
import lombok.Data;

/**
 * @ClassName NewsListVO
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/2818:25
 * @Version 1.0
 */
@Data
public class NewsListVO extends News {

    // 覆盖数据库中默认属性值,用空格取代没有值的情况
    @Override
    public String getCoverImg(){
        String coverImg = "";
        if(super.getCoverImg() != null){
            coverImg = super.getCoverImg();
        }
        return coverImg;
    }


}

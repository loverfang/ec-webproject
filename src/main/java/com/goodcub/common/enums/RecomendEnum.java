package com.goodcub.common.enums;

/**
 * @ClassName RecomendEnum
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/10/29:52
 * @Version 1.0
 */
public enum RecomendEnum {
    FAILER(0,"不推荐"),
    NORMAL(1,"推荐"),
    N(2,"不推荐"),
    Y(3,"推荐");

    private Integer code;
    private String msg;

    // 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
    private RecomendEnum(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    //不需要定义set方法，防止修改
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

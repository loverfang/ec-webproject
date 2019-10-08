package com.goodcub.common.enums;

/**
 * @ClassName status
 * @Description TODO
 * @Author Luo.z.x
 * @Date 2019/9/257:53
 * @Version 1.0
 */

public enum StatusEnum {
    FAILER(-1,"失败"),
    WAITCHECK(0,"待审核"),
    NORMAL(1,"正常"),
    LOCKED(2,"已删除");

    private Integer code;
    private String msg;

    // 构造方法，注意：构造方法不能为public，因为enum并不可以被实例化
    private StatusEnum(Integer code,String msg) {
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
package com.goodcub.common.utils;

import lombok.Data;

@Data
public class BaseResult{
    /**操作标记(1:成功, 0:失败)**/
    private Integer flag;
    /**具体错误原因代码**/
    private Integer code;
    /**具体错误提示信息**/
    private String message;
    /**返回数据**/
    private Object data;
}

package com.goodcub.common.utils;

public class JsonResult extends BaseResult{

    /**
     * 当正确时返回的值R
     * @param data
     * @return
     */
    public static JsonResult success(Object data){
        JsonResult result = new JsonResult();
        result.setFlag(1);
        result.setCode(200);
        result.setMessage("OK");
        result.setResultObj(data);
        return result;
    }

    public static JsonResult success(){
        return success(null);
    }


    public static JsonResult error(){
        JsonResult result = new JsonResult();
        result.setFlag(0);
        result.setMessage("Error");

        return result;
    }

    /**
     * 只有错误信息
     * @param msg
     * @return
     */
    public static JsonResult error(String msg){
        JsonResult result = new JsonResult();
        result.setFlag(0);
        result.setMessage(msg);

        return result;
    }

    /**
     * 有特定的错误代码和对应的错误信息
     * @param code
     * @param msg
     * @return
     */
    public static JsonResult error(Integer code, String msg){
        JsonResult result = new JsonResult();
        result.setFlag(0);
        result.setCode(code);
        result.setMessage(msg);

        return result;
    }
}

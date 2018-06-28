package com.ssm.promotion.core.util;

import com.ssm.promotion.core.common.Result;

/** 
 * @author llp
 */  
public class ResultUtil {  
    /**
     * 成功,有返回值
     * @param object
     * @return
     */
    public static Result<Object> success(Object object){  
        Result<Object> result = new Result();  
        result.setResultCode(200);
        result.setMessage("SUCCESS");  
        result.setData(object);  
        return result;  
    }
    
    /**
     * 成功,没有返回值
     * @param object
     * @return
     */
    public static Result<Object> success(){  
        return success(null);  
    } 
    
    /**
     * 失败
     * @param object
     * @return
     */
    public static Result<?> error(Integer code , String errorMsg){  
        Result<?> result = new Result();  
        result.setResultCode(code);  
        result.setMessage(errorMsg);  
        return result;  
    }     
}  
package com.ssm.promotion.core.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.util.HttpRequestUtil;
import com.ssm.promotion.exception.BusinessException;
import com.ssm.promotion.exception.BusinessExceptionController;

@Controller
@RequestMapping("/httpDemo")
public class HttpDemo extends BusinessExceptionController{
	
	protected static Logger logger = LoggerFactory.getLogger(HttpDemo.class);
	
    /**
     * 请求数据集合
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<JSONObject> getList(HttpServletResponse response){
//    	if(true) {
//    		throw new BusinessException(); // 测试异常处理
//    	}
        //demo:代理访问
        String url = "http://play.taihe.com/data/user/info";
    	logger.info("getList : " + url);
        Map<String,String> createMap = new HashMap<String,String>();  
        createMap.put("_","1530021617704");
        String str=HttpRequestUtil.sendGet(url,createMap);
        JSONObject json = new JSONObject();
		json = JSON.parseObject(str);

    	Result<JSONObject> result = new Result<JSONObject>();
    	result.setResultCode(-1);
    	result.setMessage("success");
    	result.setData(json);
        return result;
    }
}

package com.ssm.promotion.core.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ssm.promotion.core.util.HttpRequestUtil;
import com.ssm.promotion.core.util.ResponseUtil;

@Controller
@RequestMapping("/httpDemo")
public class HttpDemo {

    /**
     * 查找相应的数据集合
     *
     * @param page
     * @param rows
     * @param article
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getList(HttpServletResponse response) throws Exception {

        //demo:代理访问
        String url = "http://play.taihe.com/data/user/info";
        Map<String,String> createMap = new HashMap<String,String>();  
        createMap.put("_","1530021617704");
        String str=HttpRequestUtil.sendGet(url,createMap);
        ResponseUtil.write(response, str);
        System.out.println(str);
        return null;
    }
}

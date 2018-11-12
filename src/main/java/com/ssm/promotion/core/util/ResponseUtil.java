package com.ssm.promotion.core.util;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import com.ssm.promotion.core.entity.ResultBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author llp
 * @project_name java-demo
 * @date 2018-08-08
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        response.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }

    /**
     * 转换为JSONObject结构
     * @return
     */
    public static JSONObject toJsonObject(ResultBean<JSONArray> resultBean){
    	JSONObject result = new JSONObject();
    	result.put("code", resultBean.getCode());
    	result.put("msg", resultBean.getMsg());
    	result.put("data", resultBean.getData());
    	return result;
    }

    /**
     * 设置response header
     */
    public static void setDownResponeHeader(HttpServletResponse response, String title) {
        try {
            response.reset();
            response.setContentType("application/msexcel");
            
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("Transfer-Encoding", "chunked"); // 当不确定消息长度的时候，可以通过chunk机制来处理这种情况
            // response.setContentType("text/html; charset=UTF-8"); //设置编码字符
            response.addHeader("Content-disposition", "attachment;fileName=" + URLEncoder.encode(title, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}

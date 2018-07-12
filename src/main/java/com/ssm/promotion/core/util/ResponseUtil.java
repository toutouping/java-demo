package com.ssm.promotion.core.util;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * @author llp
 * @project_name java-demo
 * @date 2017-3-1
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
     * 设置response header
     */
    public static void setDownResponeHeader(HttpServletResponse response, String title) {
        try {
            response.reset();

            /* application/octet-stream 强制浏览器打开save as对话框来保存文件
              application/x-msdownload 不同的浏览器有不同的处理方式
              application/force-download 设置强制下载不打开
             */
            response.setContentType("application/x-msdownload");
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("Transfer-Encoding", "chunked"); // 当不确定消息长度的时候，可以通过chunk机制来处理这种情况
            // response.setContentType("text/html; charset=UTF-8"); //设置编码字符
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(title, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

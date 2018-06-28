package com.ssm.promotion.core.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author llp
 * @project_name http-request
 * @date 2017-3-1
 */
public class ResponseUtil {

    public static void write(HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}

package com.ssm.promotion.core.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.net.URLDecoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.TableStyle;
import com.deepoove.poi.util.BytePictureUtils;
import com.ssm.promotion.core.util.HttpRequestUtil;
import com.ssm.promotion.core.util.ResponseUtil;
import com.ssm.promotion.core.word.DetailData;
import com.ssm.promotion.core.word.DetailTablePolicy;

@Controller
@RequestMapping("/httpDemo")
public class HttpDemo{

    private static final Logger log = Logger.getLogger(HttpDemo.class);// 日志文件
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

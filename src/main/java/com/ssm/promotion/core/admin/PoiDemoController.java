package com.ssm.promotion.core.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
import com.ssm.promotion.core.util.ResponseUtil;
import com.ssm.promotion.core.word.DetailData;
import com.ssm.promotion.core.word.DetailTablePolicy;

@Controller
@RequestMapping("/poiDownload")
public class PoiDemoController{

    private static final Logger log = Logger.getLogger(PoiDemoController.class);// 日志文件
    
    @RequestMapping(value = "/downloadWord", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public void downloadWord(@RequestParam(value = "user", required = false) String user, HttpServletRequest request, HttpServletResponse response) throws IOException{
    	XWPFTemplate template = null;
        ServletOutputStream out = null;
        BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        if(user == null || user.isEmpty()) {
        	user = "瓶子君";
        }
        
        try {
            // long contentLength = bis.available(); // 获取流的最大字符数
			ResponseUtil.setDownResponeHeader(response, getFileName("product") + ".docx"); // 设置响应头
        	
        	template = generateWord(user);
        	
	        template.write(os);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
	        out = response.getOutputStream();
	        bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
			bos.flush();  
			bos.close();
		} catch (IOException e) {
			log.info(e.getMessage());
		} finally {
		    if(os != null) os.close();
		    if(out != null) out.close();
		    if (bis != null) bis.close();
		    if(template != null) template.close(); // 删除临时文件
		}
    }
    
    @RequestMapping(value = "/downloadZip", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public void downloadZip(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	ArrayList<XWPFTemplate> templates = new ArrayList<XWPFTemplate>();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
			ResponseUtil.setDownResponeHeader(response, getFileName("product") + ".zip"); // 设置响应头
        	
	        templates.add(generateWord("瓶子君"));
	        templates.add(generateWord("瓶子君"));
	        
	        zipFile(response, templates, "product", ".docx");
		} catch (IOException e) {
			log.info(e.getMessage());
		} finally {
		    if(os != null) os.close();
		}
    }

	/**
	 * 压缩文件
	 * @param response
	 * @param templates
	 * @param fileName
	 * @param type
	 */
    public static void zipFile(HttpServletResponse response, ArrayList<XWPFTemplate> templates, String fileName, String type) {
        BufferedInputStream bis = null;
        ZipOutputStream zos = null;
    	try {
	        zos = new ZipOutputStream(response.getOutputStream()); 
	        for (int i = 0; i < templates.size(); i++) {
	        	XWPFTemplate temp = null;
	        	temp = templates.get(i);
	        	ByteArrayOutputStream tempos = new ByteArrayOutputStream();
				temp.write(tempos);
	        	InputStream is = new ByteArrayInputStream(tempos.toByteArray());
		        bis = new BufferedInputStream(is);
	            long contentLength = bis.available(); // 获取流的最大字符数
		        zos.putNextEntry(new ZipEntry(fileName + System.currentTimeMillis() + type));
		        int bytesRead = 0;
		        byte[] buff = new byte[1024];
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					zos.write(buff, 0, bytesRead);
				}
	            zos.flush();
				is.close();
				temp.close();
	        }
			zos.closeEntry();
		} catch (IOException e) {
			log.info(e.getMessage());
		} finally {
		    if(zos != null)
				try {
					zos.close();
				} catch (IOException e) {
					log.info(e.getMessage());
				}
		}
    }

    /**
     * 获取文件名字
     * @param name
     * @return
     */
    public static String getFileName(String name){
    	// 文件名获取
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = name + format.format(date);
		return fileName;
    }
    
    /**
     * 获取word文档
     * @return
     */
    public XWPFTemplate generateWord (String user) throws FileNotFoundException {
	    TableStyle rowStyle = new TableStyle();
        rowStyle = new TableStyle();
        rowStyle.setAlign(STJc.CENTER);
        
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("name", new TextRenderData(user));
		datas.put("code", "1165 6234 1505 0023 26");
		datas.put("major", "计算机科学与技术");
		datas.put("year", 4);
		datas.put("school", "广东石油化工学院");
		datas.put("enterDate", "2011年09月01日");
        datas.put("descTitle", "注意事项：");
        datas.put("desc1", "备案表是依据《高等学校学生学籍学历电子注册办法》(教学[2014]11号)对学历证书电子注册复核备案的结果。");
        datas.put("desc5", "报告在线验证有效期由报告权属人设置（1~6个月），其在报告验证到期前可再次延长验证有效期。");
		
		DetailData detailTable = new DetailData();
        RowRenderData good = RowRenderData.build("4", "墙纸", "书房+卧室", "1500");
        // good.setStyle(rowStyle);
        List<RowRenderData> goods = Arrays.asList(good, good, good);
        detailTable.setGoods(goods);
        
        RowRenderData  labor1 = RowRenderData.build("油漆工", "1", "200", "400");
        RowRenderData  labor2 = RowRenderData.build("油漆工", "2", "200", "400");
        RowRenderData  labor3 = RowRenderData.build("油漆工", "3", "200", "400");
        RowRenderData  labor4 = RowRenderData.build("油漆工", "4", "200", "400");
        
        // labor.setStyle(rowStyle);
        List<RowRenderData> labors = Arrays.asList(labor1, labor2, labor3, labor4);
        detailTable.setLabors(labors);
        datas.put("table", detailTable);
        
		// 本地图片byte数据
		datas.put("local", new PictureRenderData(110, 180, ".png", BytePictureUtils.getUrlByteArray("https://avatars3.githubusercontent.com/u/1394854")));
		
        Configure config = Configure.newBuilder().customPolicy("table", new DetailTablePolicy()).build();
		
		XWPFTemplate template  = null;
		
	    try {			
	        String path = Thread.currentThread().getContextClassLoader().getResource("product.docx").getPath();
			path = URLDecoder.decode(path, "UTF-8");
			template = XWPFTemplate.compile(path, config).render(datas);
			return template;
		} catch (UnsupportedEncodingException e1) {
			log.info(e1.getMessage());
		}
	    return null;
    }
}

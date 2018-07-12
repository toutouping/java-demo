package com.ssm.promotion.core.admin;

import com.deepoove.poi.XWPFTemplate;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.promotion.core.common.Result;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.Article;
import com.ssm.promotion.core.entity.NoticeExport;
import com.ssm.promotion.core.util.ResponseUtil;
import com.ssm.promotion.docx4j.utils.Programatically;

import org.apache.log4j.Logger;
import org.docx4j.convert.in.xhtml.XHTMLImporter;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/export")
public class ExportController{

    private static final Logger log = Logger.getLogger(ExportController.class);// 日志文件
    
    @RequestMapping(value = "/exportNoticeWord", method = RequestMethod.GET)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public void exportNoticeWord(@RequestParam(value = "noticeExportStr", required = false) String noticeExportStr, HttpServletRequest request, HttpServletResponse response){
		ObjectMapper mapper=new ObjectMapper();
		NoticeExport noticeExport = null;
    	Result<?> result = null;
		ServletOutputStream out = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		ByteArrayOutputStream os = null;
		WordprocessingMLPackage wordMLPackage = null;
		try {
			if(noticeExportStr == null ) {
				ResponseUtil.write(response, ResultGenerator.genFailResult("下载内容不存在。"));
			}
			// noticeExportStr = URLDecoder.decode(URLDecoder.decode(noticeExportStr, "UTF-8"), "UTF-8");
			noticeExport = mapper.readValue(replaceCharacter(noticeExportStr), NoticeExport.class);
			if(noticeExport != null) {
				// POI 处理 模板
				String path = Thread.currentThread().getContextClassLoader().getResource("通知书template.docx").getPath();
				Map<String, Object> datas = new HashMap<String, Object>();
				datas.put("headerContent",  noticeExport.getHeaderContent());
				datas.put("contactPhone", noticeExport.getContactPhone());
				datas.put("contactUser", noticeExport.getContactUser());
				datas.put("currentYear", noticeExport.getCurrentYear());
				datas.put("dateString", noticeExport.getDateString());
				datas.put("versionNo", noticeExport.getVersionNo());
				XWPFTemplate template = XWPFTemplate.compile(URLDecoder.decode(path, "UTF-8")).render(datas);
		        ByteArrayOutputStream temOs = new ByteArrayOutputStream();
		        template.write(temOs);
				byte[] contentTemp = temOs.toByteArray();
				InputStream isTemp = new ByteArrayInputStream(contentTemp);
				
				// DOCX4J 处理富文本
				os = new ByteArrayOutputStream();
				ResponseUtil.setDownResponeHeader(response, getFileName("通知书") + ".docx"); // 设置响应头
				HashMap<String, String> mappings = new HashMap<String, String>(); // 设置替换值
				mappings.put("HTML_CONTENT", noticeExport.getDetailContent());
				wordMLPackage = generateWordOutput(wordMLPackage, isTemp, mappings);
				wordMLPackage.save(os);
				
				// 输出流
				byte[] content = os.toByteArray();
				InputStream is = new ByteArrayInputStream(content);
		        out = response.getOutputStream();
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[512];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
				bos.flush();
			}
		} catch (IOException e) {
			log.info(e.getMessage());
		} catch (Exception e) {
			log.info("ExportController downloadWord " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(bos != null) bos.close();
				if(os != null) os.close();
				if(out != null) out.close();
				if (bis != null) bis.close();
			} catch (IOException e) {
				log.info("ExportController downloadWord " + e.getMessage());
			}
		}
    }

    @RequestMapping(value = "/exportNoticeWord", method = RequestMethod.POST)
    public void exportNoticeWord(NoticeExport noticeExport, HttpServletResponse response) throws Exception {
		ObjectMapper mapper=new ObjectMapper();
    	Result<?> result = null;
		ServletOutputStream out = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		ByteArrayOutputStream os = null;
		WordprocessingMLPackage wordMLPackage = null;
		try {
			if(noticeExport != null) {
				// POI 处理 模板
				String path = Thread.currentThread().getContextClassLoader().getResource("总行信息技术部系统维护通知书.docx").getPath();
				Map<String, Object> datas = new HashMap<String, Object>();
				datas.put("headerContent",  noticeExport.getHeaderContent());
				datas.put("contactPhone", noticeExport.getContactPhone());
				datas.put("contactUser", noticeExport.getContactUser());
				datas.put("currentYear", noticeExport.getCurrentYear());
				datas.put("dateString", noticeExport.getDateString());
				datas.put("versionNo", noticeExport.getVersionNo());
				XWPFTemplate template = XWPFTemplate.compile(URLDecoder.decode(path, "UTF-8")).render(datas);
		        ByteArrayOutputStream temOs = new ByteArrayOutputStream();
		        template.write(temOs);
				byte[] contentTemp = temOs.toByteArray();
				InputStream isTemp = new ByteArrayInputStream(contentTemp);
				
				// DOCX4J 处理富文本
				os = new ByteArrayOutputStream();
				ResponseUtil.setDownResponeHeader(response, getFileName("总行信息技术部系统维护通知书") + ".docx"); // 设置响应头
				HashMap<String, String> mappings = new HashMap<String, String>(); // 设置替换值
				mappings.put("HTML_CONTENT", noticeExport.getDetailContent());
				wordMLPackage = generateWordOutput(wordMLPackage, isTemp, mappings);
				wordMLPackage.save(os);
				
				// 输出流
				byte[] content = os.toByteArray();
				InputStream is = new ByteArrayInputStream(content);
		        out = response.getOutputStream();
				bis = new BufferedInputStream(is);
				bos = new BufferedOutputStream(out);
				byte[] buff = new byte[512];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
				bos.flush();
			}
		} catch (IOException e) {
			log.info(e.getMessage());
		} catch (Exception e) {
			log.info("ExportController downloadWord " + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(bos != null) bos.close();
				if(os != null) os.close();
				if(out != null) out.close();
				if (bis != null) bis.close();
			} catch (IOException e) {
				log.info("ExportController downloadWord " + e.getMessage());
			}
		}
    }
    
    /**
     * 
     * @param str
     * @return
     */
    public static String replaceCharacter(String str) {
	  return str.replaceAll("\\$\\{\\{_25\\}\\}", "%")
	    	    .replaceAll("\\$\\{\\{_26\\}\\}", "&")
			    .replaceAll("\\$\\{\\{_22\\}\\}", "\\\\\"")
	    	    .replaceAll("\\$\\{\\{_2B\\}\\}", "+")
	    	    .replaceAll("\\$\\{\\{_20\\}\\}", " ")
	    	    .replaceAll("\\$\\{\\{_2F}}", "%")
	    	    .replaceAll("\\$\\{\\{_3F\\}\\}", "?")
	    	    .replaceAll("\\$\\{\\{_23\\}\\}", "#")
	    	    .replaceAll("\\$\\{\\{_3D\\}\\}", "=");
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
	 * 根据word模板生成输出流
	 * @param templatefile
	 * @param mappings
	 * @return
	 */
	private WordprocessingMLPackage generateWordOutput(WordprocessingMLPackage wordMLPackage,InputStream is,HashMap<String, String> mappings) {
        XHTMLImporter xHTMLImporter= null;
	    Class<?> xhtmlImporterClass = null;
		try {			
			xhtmlImporterClass = Class.forName("org.docx4j.convert.in.xhtml.XHTMLImporterImpl");
	        Constructor<?> ctor;
			ctor = xhtmlImporterClass.getConstructor(WordprocessingMLPackage.class);
			wordMLPackage = WordprocessingMLPackage.load(is);
			xHTMLImporter = (XHTMLImporter) ctor.newInstance(wordMLPackage);
			Iterator iterator = mappings.entrySet().iterator();
			while (iterator.hasNext()) {
			    Map.Entry mapEntry = (Map.Entry) iterator.next();
			    String xhtml = "<div>" + mapEntry.getValue().toString() + "</div>"; 
			    int locationOfItem =  Programatically.findPlaceHolder(mapEntry.getKey().toString(), wordMLPackage);
			    if (locationOfItem > 0) {
			        wordMLPackage.getMainDocumentPart().getContent().addAll(locationOfItem , xHTMLImporter.convert(xhtml, null)  );   
			    }
			}
		} catch (InvalidFormatException e) {
			log.info(e.getMessage());
		} catch (InstantiationException e) {
			log.info(e.getMessage());
		} catch (IllegalAccessException e) {
			log.info(e.getMessage());
		} catch (InvocationTargetException e) {
			log.info(e.getMessage());
		} catch (NoSuchMethodException e) {
			log.info(e.getMessage());
		} catch (ClassNotFoundException e) {
			log.info(e.getMessage());
		}  catch (Docx4JException e) {
			log.info(e.getMessage());
		}
		return wordMLPackage;
	}
    
}
package com.ssm.promotion.core.admin;

import com.alibaba.fastjson.JSON;
import com.aspose.words.Document;
import com.aspose.words.OoxmlCompliance;
import com.aspose.words.OoxmlSaveOptions;
import com.aspose.words.SaveFormat;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.DocxRenderData;
import com.ssm.promotion.core.common.ResultGenerator;
import com.ssm.promotion.core.entity.NoticeExport;
import com.ssm.promotion.core.entity.ResultBean;
import com.ssm.promotion.core.service.NoticeExportService;
import com.ssm.promotion.core.util.ExcelReader;
import com.ssm.promotion.core.util.ResponseUtil;
import com.ssm.promotion.core.word.XWPFWordUtil;

import net.sf.json.JSONArray;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/export")
public class ExportController{

    private static final Logger log = Logger.getLogger(ExportController.class);// 日志文件

    @Resource
    private NoticeExportService noticeExportService;
    
    /**
    * 通知书 ---word导出
    * @param noticeExportStr
    * @param response
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/exportNoticeWord", method = RequestMethod.POST)
    public void exportNoticeWord(String noticeExportStr, HttpServletResponse response){
    	NoticeExport noticeExport = null;
		ServletOutputStream out = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		ByteArrayOutputStream os = null;
		try {
			 if(noticeExportStr == null ) {
				 ResponseUtil.write(response, ResultGenerator.genFailResult("下载内容不存在。"));
				 return;
			 }
			 // noticeExportStr = URLDecoder.decode(URLDecoder.decode(noticeExportStr, "UTF-8"), "UTF-8");
			 noticeExportStr = URLDecoder.decode(noticeExportStr, "UTF-8");
			 // noticeExport = JSON.parseObject(Base64.decodeBase64(noticeExportStr), NoticeExport.class);
			 noticeExport = JSON.parseObject(replaceCharacter(noticeExportStr), NoticeExport.class);
			if(noticeExport != null) {
				// 将信息记录入数据库
				if(noticeExportService.insertWord(noticeExport) > 0) {
					log.info("exportNoticeWord insert word success");
				} else {
					 ResponseUtil.write(response, ResultGenerator.genFailResult("下载内容不存在。"));
					 return;
				}

				ResponseUtil.setDownResponeHeader(response, getFileName("通知书") + ".docx"); // 设置响应头
				
				// POI 处理 模板
				String path = Thread.currentThread().getContextClassLoader().getResource("通知书.docx").getPath();
				Map<String, Object> datas = new HashMap<String, Object>();
				datas.put("headerContent",  unEmpty(noticeExport.getHeaderContent()));
				datas.put("contactPhone", unEmpty(noticeExport.getContactPhone()));
				datas.put("contactUser", unEmpty(noticeExport.getContactUser()));
				datas.put("currentYear", unEmpty(noticeExport.getCurrentYear()));
				datas.put("dateString", unEmpty(noticeExport.getDateString()));
				datas.put("versionNo", unEmpty(noticeExport.getVersionNo()));

				// DOCX4J 处理富文本
			    /*os = new ByteArrayOutputStream();
				ResponseUtil.setDownResponeHeader(response, getFileName("通知书") + ".docx"); // 设置响应头
				HashMap<String, String> mappings = new HashMap<String, String>(); // 设置替换值
				mappings.put("HTML_CONTENT", noticeExport.getDetailContent());
				wordMLPackage = generateWordOutput(wordMLPackage, isTemp, mappings);
				wordMLPackage.save(os);*/
				
				 // html转换为docx文件
		         InputStream tempIs = new ByteArrayInputStream((unEmpty(noticeExport.getDetailContent())).getBytes("UTF-8"));
		         Document doc = new Document(tempIs);
		         OoxmlSaveOptions options = new OoxmlSaveOptions();
		         options.setDefaultTemplate(doc.getAttachedTemplate());
		         options.setSaveFormat(SaveFormat.DOCX);
		         options.setCompliance(OoxmlCompliance.ECMA_376_2006);
		         
		         // 创建文件的方式
		         String temPath = this.getClass().getResource("/").getPath();
                 doc.save(temPath + "temp.docx", options);
                 datas.put("detailContent", new DocxRenderData(new File(temPath + "temp.docx")));
                 

 				XWPFTemplate template = XWPFTemplate.compile(URLDecoder.decode(path, "UTF-8")).render(datas);

                // 删除OoxmlSaveOptions生成色水印
                XWPFWordUtil xwpfUtil = new XWPFWordUtil();
                XWPFParagraph para = xwpfUtil.searchParagraph(template.getXWPFDocument(), "Evaluation Only. Created with Aspose.Words. Copyright 2003-2017 Aspose Pty Ltd.");
                if(para !=null) {para.removeRun(0);}

				 // 写入输出流
 		        os = new ByteArrayOutputStream();
 		        template.write(os);
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
     * @Title: impExcel 
     * @Description: 批量导入客户信息
     * @param @param request
     * @param @param response
     * @param @return
     * @return String
     * @throws
     */
    @RequestMapping(value = "/impExcel", method = RequestMethod.POST)
    @ResponseBody
    public void impExcel(HttpServletRequest httpRequest,HttpServletResponse response){
    	if (! (httpRequest instanceof MultipartHttpServletRequest)) {
    		return;
    	}
    	MultipartHttpServletRequest request = (MultipartHttpServletRequest) httpRequest;
        MultipartFile file = request.getFile("file");
        ExcelReader er = new ExcelReader();
        int count =0;
        String returnMsg = "";
        int index = 1;
        List<Map<Integer, String>> list;
		try {
			list = er.readExcelContentByList(file.getInputStream());
	        count = list.size();
	        
	        for(Map<Integer,String> map : list){
	            
	            if(map.get(0)==null || "".equals(map.get(0))){
	                returnMsg += "第"+index+"行：【客户简称(必填)】列不能为空;";
	            } else if(map.get(1)==null || "".equals(map.get(1))){
	                returnMsg += "第"+index+"行：【客户全称(必填)】列不能为空;";
	            } else {
	            	returnMsg +="上传成功";
	            }
	        }
	
	        ResultBean<JSONArray> bean = new ResultBean();
	        bean.setCode(0);
	        bean.setMsg(returnMsg);
	
			ResponseUtil.write(response, ResponseUtil.toJsonObject(bean));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
    * 表格内容导出
    * @param exportTableListDemo
    * @param response
    * @return
     * @throws IOException 
     * @throws Exception
    */
    @RequestMapping(value = "/exportTableListDemo", method = RequestMethod.POST)
    public void exportTableListDemo(HttpServletResponse response) throws IOException{
		
    	String sheetName = "用车统计表单";
		String fileName = "用车申请统计表单";
		int columnNumber = 3;
		int[] columnWidth = { 10, 20, 30 };
		String[][] dataList = { { "001", "2015-01-01", "IT" },
				{ "002", "2015-01-02", "市场部" }, { "003", "2015-01-03", "测试" } };
		String[] columnName = { "单号", "申请时间", "申请部门" };
		try {
			ExportWithResponse(sheetName, fileName, columnNumber, columnWidth, columnName, dataList, response);
		} catch (Exception e) {
			log.info("ExportController exportTableListDemo " + e.getMessage());
		}
    }
    
    /**
     * ExportWithResponse
     * @param sheetName
     * @param fileName
     * @param columnNumber
     * @param columnWidth
     * @param columnName
     * @param dataList
     * @param response
     * @throws Exception
     */
	public void ExportWithResponse(String sheetName, String fileName, int columnNumber, int[] columnWidth,
			String[] columnName, String[][] dataList, HttpServletResponse response) throws Exception {
		
		if (columnNumber == columnWidth.length && columnWidth.length == columnName.length) {
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			// sheet.setDefaultColumnWidth(15); //统一设置列宽
			for (int i = 0; i < columnNumber; i++) 
			{
				for (int j = 0; j <= i; j++) 
				{
					if (i == j) 
					{
						sheet.setColumnWidth(i, columnWidth[j] * 256); // 单独设置每列的宽
					}
				}
			}
 
			// 创建第1行 也就是表头
			HSSFRow row = sheet.createRow((int) 0);
			row.setHeightInPoints(37);// 设置表头高度
 
			HSSFCellStyle style = createTitleStyle(wb);
 
			// 第四.一步，创建表头的列
			for (int i = 0; i < columnNumber; i++) 
			{
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(columnName[i]);
				cell.setCellStyle(style);
			}
 
			// 第五步，创建单元格，并设置值
			for (int i = 0; i < dataList.length; i++) 
			{
				row = sheet.createRow((int) i + 1);
 
				HSSFCellStyle style1 = getContentStyle(wb);
				HSSFCell datacell = null;
				for (int j = 0; j < columnNumber; j++) 
				{
					datacell = row.createCell(j);
					datacell.setCellValue(dataList[i][j]);
					datacell.setCellStyle(style1);
				}
			}
 
			OutputStream out = response.getOutputStream();
			// ResponseUtil.setDownResponeHeader(response, getFileName("aaa") + ".xls"); // 设置响应头
	        response.reset();
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.addHeader("filename", URLEncoder.encode(getFileName(fileName) + ".xls", "UTF-8"));
	        response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(getFileName(fileName) + ".xls", "UTF-8"));//文件名这里可以改
	        response.setContentType("application/msexcel");
	        out = response.getOutputStream();			
			try {
				wb.write(out);// 将数据写出去
				log.info("ExportController ExportWithResponse 导出" + fileName + "成功！");
			} catch (Exception e) {
				log.info("ExportController ExportWithResponse " + e.getMessage());
			} finally {
				if(out != null) out.close();
			}
		} else {
			log.info("ExportController 列数目长度名称三个数组长度要一致。");
		}
	}

	/**
	 * ExportNoResponse
	 * @param sheetName
	 * @param fileName
	 * @param columnNumber
	 * @param columnWidth
	 * @param columnName
	 * @param dataList
	 * @throws Exception
	 */
	public void ExportNoResponse(String sheetName, String fileName, int columnNumber, int[] columnWidth,
			String[] columnName, String[][] dataList) throws Exception {
		
		if (columnNumber == columnWidth.length && columnWidth.length == columnName.length) {
			// 第一步，创建一个webbook，对应一个Excel文件
			HSSFWorkbook wb = new HSSFWorkbook();
			// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
			HSSFSheet sheet = wb.createSheet(sheetName);
			
			// sheet.setDefaultColumnWidth(15); //统一设置列宽
			for (int i = 0; i < columnNumber; i++) 
			{
				for (int j = 0; j <= i; j++) 
				{
					if (i == j) 
					{
						sheet.setColumnWidth(i, columnWidth[j] * 256); // 单独设置每列的宽
					}
				}
			}
			
			// 创建第1行 也就是表头
			HSSFRow row = sheet.createRow((int) 0);
			row.setHeightInPoints(37);// 设置表头高度
			HSSFCellStyle style0 = createTitleStyle(wb);
			// 第四.一步，创建表头的列
			for (int i = 0; i < columnNumber; i++) 
			{
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(columnName[i]);
				cell.setCellStyle(style0);
			}
 
			// 第五步，创建单元格，并设置值
			for (int i = 0; i < dataList.length; i++) 
			{
				row = sheet.createRow((int) i + 1);
				
				// 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中
				HSSFCellStyle style2 = getContentStyle(wb);
				HSSFCell datacell = null;
				for (int j = 0; j < columnNumber; j++) 
				{
					datacell = row.createCell(j);
					datacell.setCellValue(dataList[i][j]);
					datacell.setCellStyle(style2);
				}
			}
 
			// 第六步，将文件存到指定位置
			try {
				FileOutputStream fout = new FileOutputStream("D:students.xls");
				wb.write(fout);
				String str = "导出" + fileName + "成功！";
				System.out.println(str);
				fout.close();
			} catch (Exception e) {
				e.printStackTrace();
				String str1 = "导出" + fileName + "失败！";
				System.out.println(str1);
			}
		} else {
			System.out.println("列数目长度名称三个数组长度要一致");
		}
	}

	/**
	 * 为数据内容设置特点新单元格样式2 自动换行 上下居中左右也居中 getContentStyle
	 * @param wb
	 * @return
	 */
	private HSSFCellStyle getContentStyle(HSSFWorkbook wb) {
		HSSFCellStyle style1 = wb.createCellStyle();
		style1.setWrapText(true);// 设置自动换行
		style1.setAlignment(HorizontalAlignment.CENTER);
		style1.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
 
		// 设置边框
		style1.setBottomBorderColor(HSSFColorPredefined.BLACK.getIndex());
		style1.setBorderBottom(BorderStyle.THIN);
		style1.setBorderLeft(BorderStyle.THIN);
		style1.setBorderRight(BorderStyle.THIN);
		style1.setBorderTop(BorderStyle.THIN);
		return style1;
	}

	/**
	 * createTitleStyle
	 * 创建表头单元格样式 以及表头的字体样式
	 * @param wb
	 * @return
	 */
	private HSSFCellStyle createTitleStyle(HSSFWorkbook wb) {
		HSSFCellStyle style = wb.createCellStyle();
		style.setWrapText(true);// 设置自动换行
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
 
		style.setFillBackgroundColor(IndexedColors.YELLOW.index);
		style.setBottomBorderColor(HSSFColorPredefined.BLACK.getIndex());
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		
		HSSFFont headerFont = (HSSFFont) wb.createFont(); // 创建字体样式
		headerFont.setBold(true);
		headerFont.setFontName("黑体"); // 设置字体类型
		headerFont.setFontHeightInPoints((short) 10); // 设置字体大小
		style.setFont(headerFont); // 为标题样式设置字体样式
		return style;
	}
	
    /**
     * 
     * @param str
     * @return
     */
    public String replaceCharacter(String str) {
	   return str.replaceAll("\\$\\{\\{_25\\}\\}", "%")
	          .replaceAll("\\$\\{\\{_26\\}\\}", "&")
	          .replaceAll("\\$\\{\\{_22\\}\\}", "\"")
	          .replaceAll("\\$\\{\\{_2B\\}\\}", "+")
	          .replaceAll("\\$\\{\\{_20\\}\\}", " ")
	          .replaceAll("\\$\\{\\{_2F}}", "%")
	          .replaceAll("\\$\\{\\{_3F\\}\\}", "?")
	          .replaceAll("\\$\\{\\{_23\\}\\}", "#")
	          .replaceAll("\\$\\{\\{_3D\\}\\}", "=");
    }
    
    /**
     * 防止为null
     * @return
     */
    public String unEmpty(String str){
    	if(str != null){
    		return str;
    	}else{
    		return "";
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
}
package com.ssm.promotion.test.word;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.deepoove.poi.data.style.TableStyle;
import com.deepoove.poi.util.BytePictureUtils;
import com.ssm.promotion.core.word.DetailData;
import com.ssm.promotion.core.word.DetailTablePolicy;
import com.ssm.promotion.core.word.XWPFWordUtil;

public class ExportToWord {  
	
	public static XWPFWordUtil xwpfHelper = new XWPFWordUtil();
    
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void textExport () {
		
	    TableStyle rowStyle = new TableStyle();
        rowStyle = new TableStyle();
        rowStyle.setAlign(STJc.CENTER);
        
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("name", new TextRenderData("瓶子君"));
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

		//核心API采用了极简设计，只需要一行代码
		FileOutputStream out;
		try {
			XWPFTemplate template = XWPFTemplate.compile("src/template/product.docx", config).render(datas);
			out = new FileOutputStream("src/output/out_product.docx");
			template.write(out);
			out.flush();
			out.close();
			template.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

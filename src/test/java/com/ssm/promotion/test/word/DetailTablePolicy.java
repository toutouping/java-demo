package com.ssm.promotion.test.word;

import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.MiniTableRenderPolicy;
import com.ssm.promotion.core.word.DetailData;
import com.ssm.promotion.core.word.XWPFWordUtil;

/**
 * 付款通知书 明细表格的自定义渲染策略<br/>
 * 1. 填充货品数据 <br/>
 * 2. 填充人工费数据 <br/>
 * 
 * @author Sayi
 * @version
 */
public class DetailTablePolicy extends DynamicTableRenderPolicy{
	
	private static Logger logger = LoggerFactory.getLogger(DetailTablePolicy.class);
	
	public static XWPFWordUtil xwpfHelper = new XWPFWordUtil();
	
	// 货品填充数据所在行数
	int goodsStartRow = 8;
	// 人工费填充数据所在行数
	int laborsStartRow = 9;

	@Override
	public void render(XWPFTable table, Object data) {
		if (null == data)
			return;
		DetailData detailData = (DetailData) data;
		List<RowRenderData> labors = detailData.getLabors();

		if (null != labors) {
			XWPFTableRow laborRow = table.getRow(laborsStartRow);
			// 循环插入行
			for (int i = 0; i < labors.size(); i++) {
				XWPFTableRow newTable = table.insertNewTableRow(laborsStartRow + i + 1); 
				xwpfHelper.copyTableRow(newTable, laborRow);
				MiniTableRenderPolicy.renderRow(table, laborsStartRow + i + 1, labors.get(i));
			}
		}
		table.removeRow(laborsStartRow);
		
		List<RowRenderData> goods = detailData.getGoods(); 
		if (null != goods) { 
			XWPFTableRow goodRow = table.getRow(goodsStartRow);
			
			for(int i = 0; i < goods.size(); i++) {
				XWPFTableRow newTable = table.insertNewTableRow(goodsStartRow + i + 1); 
				xwpfHelper.copyTableRow(newTable, goodRow);
				MiniTableRenderPolicy.renderRow(table, goodsStartRow + i + 1, goods.get(i));
			} 
		}
		table.removeRow(goodsStartRow);
	}


}
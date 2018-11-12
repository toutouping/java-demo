package com.ssm.promotion.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelReader {
	/**
     * 读取Excel数据内容
     * @param InputStream
     * @return List<Map<String, String>>  Map的key是列Id(0代表第一列)，值是具体内容
     */
    public List<Map<Integer, String>> readExcelContentByList(InputStream is) {
        
        List<Map<Integer, String>> list = new ArrayList<Map<Integer,String>>();
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
        HSSFRow row = null;
        
        try {
            wb = new HSSFWorkbook(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int c = 0;
            Map<Integer,String> map = new HashMap<Integer, String>();
            
            while (c < colNum) {
            	Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                    	map.put(c, cell.getStringCellValue());
                    }
                } else if (c == 1) {
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        map.put(c, cell.getStringCellValue());
                    }  
                } else if (c == 2) {
                    if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        map.put(c, new DecimalFormat("0").format(cell.getNumericCellValue()));
                    }  
                }
                c++;
            }
            list.add(map);
        }
        return list;
    }
}

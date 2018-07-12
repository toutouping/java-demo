package com.ssm.promotion.core.word;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.Borders;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.PositionInParagraph;
import org.apache.poi.xwpf.usermodel.TextAlignment;
import org.apache.poi.xwpf.usermodel.TextSegement;
import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPicture;
import org.apache.poi.xwpf.usermodel.XWPFRelation;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFStyle;
import org.apache.poi.xwpf.usermodel.XWPFStyles;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.impl.xb.xmlschema.SpaceAttribute;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBackground;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBorder;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDecimalNumber;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFldChar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTFonts;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHeight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHighlight;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHpsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTHyperlink;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTInd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTMarkupRange;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageSz;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTParaRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSignedTwipsMeasure;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSpacing;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTString;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTStyle;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTbl;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblBorders;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGrid;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblGridCol;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTextScale;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTrPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTVerticalJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STEm;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STFldCharType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHdrFtr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHeightRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STHighlightColor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STLineSpacingRule;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STOnOff;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STPageOrientation;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STStyleType;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STUnderline;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalAlignRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STVerticalJc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* docx文档（word 2007） 处理类
* @author llp
*/
public class XWPFWordUtil {
	
	private static Logger logger = LoggerFactory.getLogger(XWPFWordUtil.class);
	
	/**
	* 打开word文档
	* @param path
	* docx文档的路径
	* @return
	* @throws IOException
	*/
	public XWPFDocument openDoc(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		return new XWPFDocument(is);
	}
	/**
	* 保存文档
	* @param document
	* doc对象
	* @param savePath
	* docx文档的路径
	* @throws IOException
	*/
	public void saveDocument(XWPFDocument document, String savePath)throws IOException {
		FileOutputStream fos = new FileOutputStream(savePath);
		document.write(fos);
		fos.close();
	}
	
	/**
	 * 
	 * @param fileName
	 * @param imageFile
	 * @param htmFile
	 * @throws IOException
	 */
	public static void word07ToHtml(String fileName ,String imageFile , String htmFile) throws IOException{
       File f = new File(fileName);
       if (!f.exists()) {
           System.out.println("sorry file does not exists");
       }else{
           if (f.getName().endsWith(".docx")|| f.getName().endsWith(".DOCX") || f.getName().endsWith(".doc")) {
               //1:加载文档到XWPFDocument
               InputStream in = new FileInputStream(f);
               XWPFDocument document = new XWPFDocument(in);
               //2：加载图片到指定文件夹
               File imgFile = new File(imageFile);
               XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imgFile));
               options.setExtractor(new FileImageExtractor(imgFile));
               
               //3：转换XWPFDocument to XHTML 
               OutputStream out = new FileOutputStream(new File(htmFile));  
               XHTMLConverter.getInstance().convert(document, out, options);  
           }else{
                 System.out.println("Enter only MS Office 2007+ files");           
           }           
       }           
   }

	/**
	 * 
	 * 复制RUN，从source到target
	 * @param target
	 * @param source
	 * 
	 */
	public void copyRun(XWPFRun target, XWPFRun source) {
		// 设置run属性
		target.getCTR().setRPr(source.getCTR().getRPr());
		// 设置文本
		target.setText(source.text());
		// 处理图片
		List<XWPFPicture> pictures = source.getEmbeddedPictures();

		for (XWPFPicture picture : pictures) {
			try {
				copyPicture(target, picture);
			} catch (InvalidFormatException e) {
				logger.error("copyRun", e);
			} catch (IOException e) {
				logger.error("copyRun", e);
			}
		}
	}

	/**
	 * 
	 * 复制图片到target
	 * @param target
	 * @param picture
	 * @throws IOException
	 * @throws InvalidFormatException
	 * 
	 */
	public void copyPicture(XWPFRun target, XWPFPicture picture)throws IOException, InvalidFormatException {

		String filename = picture.getPictureData().getFileName();
		InputStream pictureData = new ByteArrayInputStream(picture
				.getPictureData().getData());
		int pictureType = picture.getPictureData().getPictureType();
		int width = (int) picture.getCTPicture().getSpPr().getXfrm().getExt()
				.getCx();

		int height = (int) picture.getCTPicture().getSpPr().getXfrm().getExt()
				.getCy();

		// target.addBreak();
		target.addPicture(pictureData, pictureType, filename, width, height);
		// target.addBreak(BreakType.PAGE);
	}

	/**
	 * 复制段落，从source到target
	 * @param target
	 * @param source
	 * 
	 */
	public void copyParagraph(XWPFParagraph target, XWPFParagraph source) {

		// 设置段落样式
		target.getCTP().setPPr(source.getCTP().getPPr());

		// 移除所有的run
		for (int pos = target.getRuns().size() - 1; pos >= 0; pos--) {
			target.removeRun(pos);
		}

		// copy 新的run
		for (XWPFRun s : source.getRuns()) {
			XWPFRun targetrun = target.createRun();
			copyRun(targetrun, s);
		}

	}

	/**
	 * 复制单元格，从source到target
	 * @param target
	 * @param source
	 * 
	 */
	public void copyTableCell(XWPFTableCell target, XWPFTableCell source) {
		// 列属性
		if (source.getCTTc() != null) {
			target.getCTTc().setTcPr(source.getCTTc().getTcPr());
		}
		// 删除段落
		for (int pos = 0; pos < target.getParagraphs().size(); pos++) {
			target.removeParagraph(pos);
		}
		// 添加段落
		for (XWPFParagraph sp : source.getParagraphs()) {
			XWPFParagraph targetP = target.addParagraph();
			copyParagraph(targetP, sp);
		}
	}

	/**
	 * 
	 * 复制行，从source到target
	 * @param target
	 * @param source
	 * 
	 */
	public void copyTableRow(XWPFTableRow target, XWPFTableRow source) {
		// 复制样式
		if (source.getCtRow() != null) {
			target.getCtRow().setTrPr(source.getCtRow().getTrPr());
		}
		// 复制单元格
		for (int i = 0; i < source.getTableCells().size(); i++) {
			XWPFTableCell cell1 = target.getCell(i);
			XWPFTableCell cell2 = source.getCell(i);
			if (cell1 == null) {
				cell1 = target.addNewTableCell();
			}
			copyTableCell(cell1, cell2);
		}
	}
	  /**
	   * 复制表，从source到target
	   * @param target
	   * @param source
	   */
	  public void copyTable(XWPFTable target, XWPFTable source) {
	    // 表格属性
	    target.getCTTbl().setTblPr(source.getCTTbl().getTblPr());
	    
	    // 复制行
	    for (int i = 0; i < source.getRows().size(); i++) {
	      XWPFTableRow row1 = target.getRow(i);
	      XWPFTableRow row2 = source.getRow(i);
	      if (row1 == null) {
	        target.addRow(row2);
	      } else {
	        copyTableRow(row1, row2);
	      }
	    }
	  }
	
	  /**
	   * 复制表，从source到target
	   * @param target
	   * @param startRow
	   * @param source
	   */
	  public void copyTable(XWPFTable target, int startRow, XWPFTable source) {
	    // 表格属性
	    target.getCTTbl().setTblPr(source.getCTTbl().getTblPr());

	    // 复制行
	    for (int i = 0; i < source.getRows().size(); i++) {
	      XWPFTableRow row1 = target.getRow(startRow + i);
	      XWPFTableRow row2 = source.getRow(i);
	      if (row1 == null) {
	        target.addRow(row2);
	      } else {
	        copyTableRow(row1, row2);
	      }
	    }
	  }
	  
	  /**
	   * 用表来替换原段落，从source到target
	   * @param doc
	   * @param target
	   * @param source
	   */
	  public void replaceParaWithTable(XWPFDocument doc, XWPFParagraph target, XWPFTable source) {

	    XmlCursor cursor = target.getCTP().newCursor();// this is the key!
	    XWPFTable t = doc.insertNewTbl(cursor);
	    copyTable(t, source);
	    doc.removeBodyElement(doc.getPosOfParagraph(target));
	  }

	 

	  /**
	   * 用一段新文本替换target段落
	   * @param target
	   * @param source
	   */
	  public void replaceParaWithText(XWPFParagraph target, String newText) {
	    List<XWPFRun> runs = target.getRuns();
	    for (int pos = runs.size() - 1; pos > 0; pos--) {
	      target.removeRun(pos);
	    }

	    // 可能有问题
	    XWPFRun run = runs.get(0);
	    // 设置文本
	    run.setText(newText, 0);
	  }

	 

	  /**
	   * 在文档最后创建一个表格
	   * @param doc
	   * @param rows
	   * @param cols
	   * @return
	   */
	  public XWPFTable createTable(XWPFDocument doc, int rows, int cols) {
	    return doc.createTable(rows, cols);
	  }

	 

	  /**
	   * 在target元素之后插入source
	   * @param doc 被插入的文档
	   * @param target
	   * @param source
	   */
	  public void insertAfter(XWPFDocument doc, IBodyElement target, IBodyElement source) {

	    int pos = getPosOfBodyElement(doc, target);
	    logger.info("insertAfter pos:" + pos);

	    List<IBodyElement> body = doc.getBodyElements();
	    if (pos < 0 || pos > body.size()) {
	      return;
	    }

	    IBodyElement afterTarget = body.get(pos + 1);
	    insertBefore(doc, afterTarget, source);
	  }
	  /**

	   * 获取body元素在doc文档中的位置

	   * 

	   * @param doc

	   * @param needle

	   * @return 位置，从0开始，不存在则返回-1

	   */

	  public int getPosOfBodyElement(XWPFDocument doc, IBodyElement needle) {

	    BodyElementType type = needle.getElementType();

	    IBodyElement current;

	    List<IBodyElement> bodyElements = doc.getBodyElements();

	    for (int i = 0; i < bodyElements.size(); i++) {

	      current = bodyElements.get(i);

	      if (current.getElementType() == type) {

	        if (current.equals(needle)) {

	          return i;

	        }

	      }

	    }

	    return -1;

	  }

	 

	  /**

	   * 在target元素之前插入source

	   * 

	   * @param doc

	   * @param target

	   * @param source

	   */

	  public void insertBefore(XWPFDocument doc, IBodyElement target,
	      IBodyElement source) {
	    if (target.getElementType() == BodyElementType.PARAGRAPH) {
	      XmlCursor cursor = ((XWPFParagraph) target).getCTP().newCursor();
	      insert(doc, source, cursor);
	    } else if (target.getElementType() == BodyElementType.TABLE) {
	      XmlCursor cursor = ((XWPFTable) target).getCTTbl().newCursor();
	      insert(doc, source, cursor);
	    }
	  }

	  private void insert(XWPFDocument doc, IBodyElement source, XmlCursor cursor) {
	    if (source.getElementType() == BodyElementType.PARAGRAPH) {
	      XWPFParagraph p = doc.insertNewParagraph(cursor);
	      copyParagraph(p, (XWPFParagraph) source);
	    } else if (source.getElementType() == BodyElementType.TABLE) {
	      XWPFTable t = doc.insertNewTbl(cursor);
	      copyTable(t, (XWPFTable) source);
	    }
	  }

	  /**
	   * 在target元素之前插入一组source
	   * @param doc
	   * @param target
	   * @param sources
	   */
	  public void insertAllBefore(XWPFDocument doc, IBodyElement target,
	      List<IBodyElement> sources) {

	    for (IBodyElement source : sources) {
	      insertBefore(doc, target, source);
	    }
	  }

	  /**
	   * 在target元素之后插入一组source
	   * @param doc
	   * @param target
	   * @param sources
	   */
	  public void insertAllAfter(XWPFDocument doc, IBodyElement target, List<IBodyElement> sources) {
	    for (int i = sources.size() - 1; i >= 0; i--) {
	      insertAfter(doc, target, sources.get(i));
	    }
	  }

	  private boolean search(XWPFParagraph para, String searched) {
	    TextSegement found = para.searchText(searched, new PositionInParagraph());
	    if (found != null) {
	      return true;
	    }
	    return false;
	  }
	  
	  /**
	   * 查找文档中包含text文本的段落，不查找表
	   * @param doc
	   * @param text
	   * @return 返回第一个遇到的段落
	   */
	  public XWPFParagraph searchParagraph(XWPFDocument doc, String text) {
	    Iterator<XWPFParagraph> iterator = doc.getParagraphsIterator();

	    XWPFParagraph para;
	    while (iterator.hasNext()) {
	      para = iterator.next();
	      if (search(para, text)) {
	        logger.info("paragraph found,contain:" + text);
	        return para;
	      }
	    }
	    return null;
	  }

	 

	  /**
	   * 查找文档中包含text文本的body元素，先查找表若未找到再查段落
	   * @param doc
	   * @param text
	   * @return 返回第一个遇到的body元素
	   */
	  public IBodyElement searchIBodyElement(XWPFDocument doc, String text) {
	    // 先查表
	    IBodyElement element = searchTable(doc, text);
	    // 再查段落
	    if (element == null) {
	      element = searchParagraph(doc, text);
	    }
	    return element;

	  }

	  /**
	   * 查找文档中包含text文本的body元素位置，先查找表若未找到再查段落
	   * @param doc
	   * @param text
	   * @return 返回第一个遇到的body元素位置，从0开始，不存在则返回-1
	   */
	  public int searchPosIBodyElement(XWPFDocument doc, String text) {
	    // 先查表
	    int pos = searchPosTable(doc, text);

	    // 再查段落
	    if (pos < 0) {
	      pos = searchPosParagraph(doc, text);
	    }
	    return pos;
	  }
	  
	  /**
	   * 查找文档中包含text的段落位置
	   * @param doc
	   * @param text
	   * @return 返回第一个遇到的段落位置，从0开始，不存在则返回-1
	   */
	  public int searchPosParagraph(XWPFDocument doc, String text) {
	    XWPFParagraph para = searchParagraph(doc, text);
	    if (para != null) {
	      return doc.getPosOfParagraph(para);
	    } else {
	      return -1;
	    }
	  }

	  /**
	   * 查找文档中包含text的表格位置
	   * @param doc
	   * @param text
	   * @return 返回第一个遇到的表格位置，从0开始，不存在则返回-1
	   */
	  public int searchPosTable(XWPFDocument doc, String text) {
	    XWPFTable table = searchTable(doc, text);
	    if (table != null) {
	      return doc.getPosOfTable(table);
	    } else {
	      return -1;
	    }
	  }

	  /**
	   * 查找文档中包含text的表格
	   * @param doc
	   * @param text
	   * @return 返回第一个遇到的表格，从0开始，不存在则返回-1
	   */
	  public XWPFTable searchTable(XWPFDocument doc, String text) {
	    // 获取文档中所有的表格
	    Iterator<XWPFTable> iterator = doc.getTablesIterator();

	    XWPFTable table;
	    while (iterator.hasNext()) {
	      table = iterator.next();
	      // 获取表格对应的行
	      List<XWPFTableRow> rows = table.getRows();
	      for (int j = 0; j < rows.size(); j++) {
	        XWPFTableRow row = rows.get(j);
	        // 获取行对应的单元格
	        List<XWPFTableCell> cells = row.getTableCells();
	        for (int k = 0; k < cells.size(); k++) {
	          XWPFTableCell cell = cells.get(k);
	          if (StringUtils.contains(cell.getText(), text)) {
	            logger.info("table found,contain:" + text);
	            return table;
	          }
	        }
	      }
	    }
	    logger.info("table no found,contain:" + text);
	    return null;
	  }
	  
	  /**
	   * 复制表格，从source到target
	   * @param doc
	   * @param target 表格的唯一关键文本
	   * @param source 被拷贝的表格对象
	   */
	  public void copyTable(XWPFDocument doc, String target, XWPFTable source) {
	    XWPFTable t = searchTable(doc, target);
	    if (t != null) {
	      copyTable(t, source);
	    }
	  }

	  /**
	   * 复制表格，从source到target
	   * @param to 目标文档
	   * @param target 表格的唯一关键文本
	   * @param from 源文档
	   * @param source 被拷贝的表格的唯一关键文本
	   */
	  public void copyTable(XWPFDocument to, String target, XWPFDocument from,
	      String source) {

	    XWPFTable t = searchTable(to, target);
	    if (t != null) {
	      XWPFTable s = searchTable(from, source);
	      if (s != null) {
	        copyTable(t, s);
	      }
	    }
	  }

	  public void copyTable(XWPFDocument to, String target, int startPos, XWPFDocument from, String source) {
	    XWPFTable t = searchTable(to, target);
	    if (t != null) {
	      XWPFTable s = searchTable(from, source);
	      if (s != null) {
	        copyTable(t, startPos, s);
	      }
	    }
	  }

	  public void mergeOne(XWPFDocument to, String target, XWPFDocument from,
	      String source) {
	    IBodyElement t = searchIBodyElement(to, target);
	    IBodyElement s = searchIBodyElement(from, source);
	    insertAfter(to, t, s);
	  }

	  /**
	   * 合并文档，将源文档中的一组body元素插入到目前文档的target元素后面
	   * @param to
	   * @param target
	   * @param from
	   * @param startElement
	   * @param endElement
	   */
	  public void merge(XWPFDocument to, IBodyElement target, XWPFDocument from,
	      IBodyElement startElement, IBodyElement endElement) {
		  
	    List<IBodyElement> body = from.getBodyElements();
	    List<IBodyElement> fromBody = new ArrayList<IBodyElement>();
	    int startPos = getPosOfBodyElement(from, startElement) + 1;
	    int endPos = getPosOfBodyElement(from, endElement) - 1;

	    logger.info("merge startPos:" + startPos);
	    logger.info("merge endPos:" + endPos);
	    for (int i = startPos; i <= endPos; i++) {
	      fromBody.add(body.get(i));
	    }
	    insertAllAfter(to, target, fromBody);
	  }
	  
	  /**
	   * 合并文档，将源文档中的一组body元素插入到目前文档的target元素后面
	   * @param to
	   * @param t
	   * @param from
	   * @param start
	   * @param end
	   */
	  public void merge(XWPFDocument to, String t, XWPFDocument from,
	      String start, String end) {

	    IBodyElement target = searchIBodyElement(to, t);
	    IBodyElement startElement = searchIBodyElement(from, start);
	    IBodyElement endElement = searchIBodyElement(from, end);
	    merge(to, target, from, startElement, endElement);
	  }

	  /*------------------------------------Word 插入书签---------------------------------------------------  */
	  /**

	   * @Description: 添加书签

	   */

	  public void addParagraphContentBookmarkBasicStyle(XWPFParagraph p,

	      String content, BigInteger markId, String bookMarkName,

	      boolean isInsert, boolean isNewLine, String fontFamily,

	      String fontSize, String colorVal, boolean isBlod,

	      boolean isUnderLine, String underLineColor,

	      STUnderline.Enum underStyle, boolean isItalic, boolean isStrike) {

	    CTBookmark bookStart = p.getCTP().addNewBookmarkStart();

	    bookStart.setId(markId);

	    bookStart.setName(bookMarkName);

	 

	    XWPFRun pRun = getOrAddParagraphFirstRun(p, isInsert, isNewLine);

	    setParagraphRunFontInfo(p, pRun, content, fontFamily, fontSize);

	    setParagraphTextStyleInfo(p, pRun, colorVal, isBlod, isUnderLine,

	        underLineColor, underStyle, isItalic, isStrike, false, false,

	        false, false, false, false, false, null, false, null, false,

	        null, null, null, 0, 0, 0);

	    CTMarkupRange bookEnd = p.getCTP().addNewBookmarkEnd();

	    bookEnd.setId(markId);

	 

	  }
	  
	  /**

	   * @Description: 添加书签

	   */

	  public void addParagraphContentBookmark(XWPFParagraph p, String content,

	      BigInteger markId, String bookMarkName, boolean isInsert,

	      boolean isNewLine, String fontFamily, String fontSize,

	      String colorVal, boolean isBlod, boolean isUnderLine,

	      String underLineColor, STUnderline.Enum underStyle,

	      boolean isItalic, boolean isStrike, boolean isDStrike,

	      boolean isShadow, boolean isVanish, boolean isEmboss,

	      boolean isImprint, boolean isOutline, boolean isEm,

	      STEm.Enum emType, boolean isHightLight,

	      STHighlightColor.Enum hightStyle, boolean isShd,

	      STShd.Enum shdStyle, String shdColor, VerticalAlign verticalAlign,

	      int position, int spacingValue, int indent) {

	    CTBookmark bookStart = p.getCTP().addNewBookmarkStart();

	    bookStart.setId(markId);

	    bookStart.setName(bookMarkName);

	 

	    XWPFRun pRun = getOrAddParagraphFirstRun(p, isInsert, isNewLine);

	    setParagraphRunFontInfo(p, pRun, content, fontFamily, fontSize);

	    setParagraphTextStyleInfo(p, pRun, colorVal, isBlod, isUnderLine,

	        underLineColor, underStyle, isItalic, isStrike, isDStrike,

	        isShadow, isVanish, isEmboss, isImprint, isOutline, isEm,

	        emType, isHightLight, hightStyle, isShd, shdStyle, shdColor,

	        verticalAlign, position, spacingValue, indent);

	 

	    CTMarkupRange bookEnd = p.getCTP().addNewBookmarkEnd();

	    bookEnd.setId(markId);

	  }
	  
	  /*------------------------------------Word 插入超链接---------------------------------------------------  */

	  /**

	   * @Description: 默认的超链接样式

	   */

	  public void addParagraphTextHyperlinkBasicStyle(XWPFParagraph paragraph,

	      String url, String text, String fontFamily, String fontSize,

	      String colorVal, boolean isBlod, boolean isItalic, boolean isStrike) {

	    addParagraphTextHyperlink(paragraph, url, text, fontFamily, fontSize,

	        colorVal, isBlod, true, "0000FF", STUnderline.SINGLE, isItalic,

	        isStrike, false, false, false, false, false, false, false,

	        null, false, null, false, null, null, null, 0, 0, 0);

	  }

	 

	  /**

	   * @Description: 设置超链接样式

	   */

	  public void addParagraphTextHyperlink(XWPFParagraph paragraph, String url,

	      String text, String fontFamily, String fontSize, String colorVal,

	      boolean isBlod, boolean isUnderLine, String underLineColor,

	      STUnderline.Enum underStyle, boolean isItalic, boolean isStrike,

	      boolean isDStrike, boolean isShadow, boolean isVanish,

	      boolean isEmboss, boolean isImprint, boolean isOutline,

	      boolean isEm, STEm.Enum emType, boolean isHightLight,

	      STHighlightColor.Enum hightStyle, boolean isShd,

	      STShd.Enum shdStyle, String shdColor,

	      STVerticalAlignRun.Enum verticalAlign, int position,

	      int spacingValue, int indent) {

	    // Add the link as External relationship

	    String id = paragraph.getDocument().getPackagePart()
	    		.addExternalRelationship(url, XWPFRelation.HYPERLINK.getRelation()).getId();

	    // Append the link and bind it to the relationship

	    CTHyperlink cLink = paragraph.getCTP().addNewHyperlink();

	    cLink.setId(id);

	 

	    // Create the linked text

	    CTText ctText = CTText.Factory.newInstance();

	    ctText.setStringValue(text);

	    CTR ctr = CTR.Factory.newInstance();

	    CTRPr rpr = ctr.addNewRPr();

	 

	    if (StringUtils.isNotBlank(fontFamily)) {

	      // 设置字体

	      CTFonts fonts = rpr.isSetRFonts() ? rpr.getRFonts() : rpr

	          .addNewRFonts();

	      fonts.setAscii(fontFamily);

	      fonts.setEastAsia(fontFamily);

	      fonts.setHAnsi(fontFamily);

	    }

	    if (StringUtils.isNotBlank(fontSize)) {

	      // 设置字体大小

	      CTHpsMeasure sz = rpr.isSetSz() ? rpr.getSz() : rpr.addNewSz();

	      sz.setVal(new BigInteger(fontSize));

	 

	      CTHpsMeasure szCs = rpr.isSetSzCs() ? rpr.getSzCs() : rpr

	          .addNewSzCs();

	      szCs.setVal(new BigInteger(fontSize));

	    }

	    // 设置超链接样式

	    // 字体颜色

	    if (StringUtils.isNotBlank(colorVal)) {

	      CTColor color = CTColor.Factory.newInstance();

	      color.setVal(colorVal);

	      rpr.setColor(color);

	    }

	    // 加粗

	    if (isBlod) {

	      CTOnOff bCtOnOff = rpr.addNewB();

	      bCtOnOff.setVal(STOnOff.TRUE);

	    }

	    // 下划线

	    if (isUnderLine) {

	      CTUnderline udLine = rpr.addNewU();

	      udLine.setVal(underStyle);

	      udLine.setColor(underLineColor);

	    }

	    // 倾斜

	    if (isItalic) {

	      CTOnOff iCtOnOff = rpr.addNewI();

	      iCtOnOff.setVal(STOnOff.TRUE);

	    }

	    // 删除线

	    if (isStrike) {

	      CTOnOff sCtOnOff = rpr.addNewStrike();

	      sCtOnOff.setVal(STOnOff.TRUE);

	    }

	    // 双删除线

	    if (isDStrike) {

	      CTOnOff dsCtOnOff = rpr.addNewDstrike();

	      dsCtOnOff.setVal(STOnOff.TRUE);

	    }

	    // 阴影

	    if (isShadow) {

	      CTOnOff shadowCtOnOff = rpr.addNewShadow();

	      shadowCtOnOff.setVal(STOnOff.TRUE);

	    }

	    // 隐藏

	    if (isVanish) {

	      CTOnOff vanishCtOnOff = rpr.addNewVanish();

	      vanishCtOnOff.setVal(STOnOff.TRUE);

	    }

	    // 阳文

	    if (isEmboss) {

	      CTOnOff embossCtOnOff = rpr.addNewEmboss();

	      embossCtOnOff.setVal(STOnOff.TRUE);

	    }

	    // 阴文

	    if (isImprint) {

	      CTOnOff isImprintCtOnOff = rpr.addNewImprint();

	      isImprintCtOnOff.setVal(STOnOff.TRUE);

	    }

	    // 空心

	    if (isOutline) {

	      CTOnOff isOutlineCtOnOff = rpr.addNewOutline();

	      isOutlineCtOnOff.setVal(STOnOff.TRUE);

	    }

	    // 着重号

	    if (isEm) {

	      CTEm em = rpr.addNewEm();

	      em.setVal(emType);

	    }

	    // 突出显示文本

	    if (isHightLight) {

	      if (hightStyle != null) {

	        CTHighlight hightLight = rpr.addNewHighlight();

	        hightLight.setVal(hightStyle);

	      }

	    }

	    if (isShd) {

	      // 设置底纹

	      CTShd shd = rpr.addNewShd();

	      if (shdStyle != null) {

	        shd.setVal(shdStyle);

	      }

	      if (shdColor != null) {

	        shd.setColor(shdColor);

	      }

	    }

	    // 上标下标

	    if (verticalAlign != null) {

	      rpr.addNewVertAlign().setVal(verticalAlign);

	    }

	    // 设置文本位置

	    rpr.addNewPosition().setVal(new BigInteger(String.valueOf(position)));

	    if (spacingValue != 0) {

	      // 设置字符间距信息

	      CTSignedTwipsMeasure ctSTwipsMeasure = rpr.addNewSpacing();

	      ctSTwipsMeasure

	          .setVal(new BigInteger(String.valueOf(spacingValue)));

	    }

	    // 设置字符间距缩进

	    if (indent > 0) {

	      CTTextScale paramCTTextScale = rpr.addNewW();

	      paramCTTextScale.setVal(indent);

	    }

	    ctr.setTArray(new CTText[] { ctText });

	    cLink.setRArray(new CTR[] { ctr });

	  }
	  /*------------------------------------Word 页眉页脚相关---------------------------------------------------  */
	  
	  /**

	   * @Description: 页脚:显示页码信息

	   */

	  public void simpleNumberFooter(XWPFDocument document) throws Exception {

	    CTP ctp = CTP.Factory.newInstance();

	    XWPFParagraph codePara = new XWPFParagraph(ctp, document);

	    XWPFRun r1 = codePara.createRun();

	    r1.setText("第");

	    setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

	 

	    r1 = codePara.createRun();

	    CTFldChar fldChar = r1.getCTR().addNewFldChar();

	    fldChar.setFldCharType(STFldCharType.BEGIN);

	 

	    r1 = codePara.createRun();

	    CTText ctText = r1.getCTR().addNewInstrText();

	    ctText.setStringValue("PAGE  \\* MERGEFORMAT");

	    ctText.setSpace(SpaceAttribute.Space.PRESERVE);

	    setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

	 

	    fldChar = r1.getCTR().addNewFldChar();

	    fldChar.setFldCharType(STFldCharType.END);

	 

	    r1 = codePara.createRun();

	    r1.setText("页 总共");

	    setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

	 

	    r1 = codePara.createRun();

	    fldChar = r1.getCTR().addNewFldChar();

	    fldChar.setFldCharType(STFldCharType.BEGIN);

	 

	    r1 = codePara.createRun();

	    ctText = r1.getCTR().addNewInstrText();

	    ctText.setStringValue("NUMPAGES  \\* MERGEFORMAT ");

	    ctText.setSpace(SpaceAttribute.Space.PRESERVE);

	    setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

	 

	    fldChar = r1.getCTR().addNewFldChar();

	    fldChar.setFldCharType(STFldCharType.END);

	 

	    r1 = codePara.createRun();

	    r1.setText("页");

	    setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

	 

	    setParagraphAlignInfo(codePara, ParagraphAlignment.CENTER,

	        TextAlignment.CENTER);

	    codePara.setBorderTop(Borders.THICK);

	    XWPFParagraph[] newparagraphs = new XWPFParagraph[1];

	    newparagraphs[0] = codePara;

	    CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();

	    XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy(document, sectPr);

	    headerFooterPolicy.createFooter(STHdrFtr.DEFAULT, newparagraphs);

	  }

	  /**
	   * @Description: 页眉:显示时间信息
	   */
	  public void simpleDateHeader(XWPFDocument document) throws Exception {

	    CTP ctp = CTP.Factory.newInstance();

	    XWPFParagraph codePara = new XWPFParagraph(ctp, document);

	    XWPFRun r1 = codePara.createRun();

	    CTFldChar fldChar = r1.getCTR().addNewFldChar();

	    fldChar.setFldCharType(STFldCharType.BEGIN);
	    r1 = codePara.createRun();
	    CTText ctText = r1.getCTR().addNewInstrText();
	    ctText.setStringValue("TIME \\@ \"EEEE\"");
	    ctText.setSpace(SpaceAttribute.Space.PRESERVE);
	    setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");
	    fldChar = r1.getCTR().addNewFldChar();
	    fldChar.setFldCharType(STFldCharType.END);
	    r1 = codePara.createRun();
	    r1.setText("年");
	    setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");
	    r1 = codePara.createRun();
	    fldChar = r1.getCTR().addNewFldChar();
	    fldChar.setFldCharType(STFldCharType.BEGIN);
	    r1 = codePara.createRun();
	    ctText = r1.getCTR().addNewInstrText();
	    ctText.setStringValue("TIME \\@ \"O\"");
	    ctText.setSpace(SpaceAttribute.Space.PRESERVE);
	    setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");

	    fldChar = r1.getCTR().addNewFldChar();
	    fldChar.setFldCharType(STFldCharType.END);
	    r1 = codePara.createRun();
	    r1.setText("月");
	    r1.setFontSize(11);
	    setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");
	    r1 = codePara.createRun();
	    fldChar = r1.getCTR().addNewFldChar();
	    fldChar.setFldCharType(STFldCharType.BEGIN);

	 

	    r1 = codePara.createRun();
	    ctText = r1.getCTR().addNewInstrText();
	    ctText.setStringValue("TIME \\@ \"A\"");
	    ctText.setSpace(SpaceAttribute.Space.PRESERVE);
	    setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");
	    fldChar = r1.getCTR().addNewFldChar();
	    fldChar.setFldCharType(STFldCharType.END);
	    r1 = codePara.createRun();
	    r1.setText("日");
	    r1.setFontSize(11);
	    setParagraphRunFontInfo(codePara, r1, null, "微软雅黑", "22");
	    setParagraphAlignInfo(codePara, ParagraphAlignment.CENTER,TextAlignment.CENTER);
	    codePara.setBorderBottom(Borders.THICK);

	    XWPFParagraph[] newparagraphs = new XWPFParagraph[1];
	    newparagraphs[0] = codePara;
	    CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
	    XWPFHeaderFooterPolicy headerFooterPolicy = new XWPFHeaderFooterPolicy( document, sectPr);
	    headerFooterPolicy.createHeader(STHdrFtr.DEFAULT, newparagraphs);
	  }

	 

	  /*------------------------------------Word 段落相关---------------------------------------------------  */

	  /**
	   * @Description: 得到段落CTPPr
	   */
	  public CTPPr getParagraphCTPPr(XWPFParagraph p) {

	    CTPPr pPPr = null;
	    if (p.getCTP() != null) {
	      if (p.getCTP().getPPr() != null) {
	        pPPr = p.getCTP().getPPr();
	      } else {
	        pPPr = p.getCTP().addNewPPr();
	      }
	    }
	    return pPPr;
	  }

	 

	  /**
	   * @Description: 得到XWPFRun的CTRPr
	   */
	  public CTRPr getRunCTRPr(XWPFParagraph p, XWPFRun pRun) {

	    CTRPr pRpr = null;
	    if (pRun.getCTR() != null) {
	      pRpr = pRun.getCTR().getRPr();
	      if (pRpr == null) {
	        pRpr = pRun.getCTR().addNewRPr();
	      }
	    } else {
	      pRpr = p.getCTP().addNewR().addNewRPr();
	    }
	    return pRpr;
	  }

	  public XWPFRun getOrAddParagraphFirstRun(XWPFParagraph p, boolean isInsert,
	      boolean isNewLine) {

	    XWPFRun pRun = null;
	    if (isInsert) {
	      pRun = p.createRun();
	    } else {
	      if (p.getRuns() != null && p.getRuns().size() > 0) {
	        pRun = p.getRuns().get(0);
	      } else {
	        pRun = p.createRun();
	      }
	    }

	    if (isNewLine) {
	      // 换行
	      pRun.addBreak();
	    }
	    return pRun;
	  }
	 

	  public void setParagraphTextFontInfo(XWPFParagraph p, boolean isInsert,
	      boolean isNewLine, String content, String fontFamily, String fontSize) {

	    XWPFRun pRun = getOrAddParagraphFirstRun(p, isInsert, isNewLine);
	    setParagraphRunFontInfo(p, pRun, content, fontFamily, fontSize);
	  }

	 

	  /**
	   * @Description 设置字体信息
	   */
	  public void setParagraphRunFontInfo(XWPFParagraph p, XWPFRun pRun,
	      String content, String fontFamily, String fontSize) {

	    CTRPr pRpr = getRunCTRPr(p, pRun);
	    if (StringUtils.isNotBlank(content)) {
	      pRun.setText(content);
	    }

	    // 设置字体
	    CTFonts fonts = pRpr.isSetRFonts() ? pRpr.getRFonts() : pRpr.addNewRFonts();
	    fonts.setAscii(fontFamily);
	    fonts.setEastAsia(fontFamily);
	    fonts.setHAnsi(fontFamily);

	    // 设置字体大小
	    CTHpsMeasure sz = pRpr.isSetSz() ? pRpr.getSz() : pRpr.addNewSz();
	    sz.setVal(new BigInteger(fontSize));
	    CTHpsMeasure szCs = pRpr.isSetSzCs() ? pRpr.getSzCs() : pRpr.addNewSzCs();
	    szCs.setVal(new BigInteger(fontSize));
	  }

	 

	  /**
	   * @Description: 设置段落基本样式
	   */
	  public void setParagraphTextBasicStyleInfo(XWPFParagraph p, XWPFRun pRun,

	      String colorVal, boolean isBlod, boolean isUnderLine,

	      String underLineColor, STUnderline.Enum underStyle,

	      boolean isItalic, boolean isStrike, boolean isHightLight,

	      STHighlightColor.Enum hightStyle, boolean isShd,

	      STShd.Enum shdStyle, String shdColor) {

	      setParagraphTextStyleInfo(p, pRun, colorVal, isBlod, isUnderLine,

	        underLineColor, underStyle, isItalic, isStrike, false, false,

	        false, false, false, false, false, null, isHightLight,

	        hightStyle, isShd, shdStyle, shdColor, null, 0, 0, 0);

	  }

	 

	  /**
	   * @Description: 设置段落文本样式(高亮与底纹显示效果不同)设置字符间距信息(CTSignedTwipsMeasure)
	   * @param verticalAlign : SUPERSCRIPT上标 SUBSCRIPT下标
	   * @param position :字符间距位置：>0提升 <0降低=磅值*2 如3磅=6
	   * @param spacingValue :字符间距间距 >0加宽 <0紧缩 =磅值*20 如2磅=40
	   * @param indent :字符间距缩进 <100 缩
	   */
	  public void setParagraphTextSimpleStyleInfo(XWPFParagraph p, XWPFRun pRun,

	      String colorVal, boolean isBlod, boolean isUnderLine,

	      String underLineColor, STUnderline.Enum underStyle,

	      boolean isItalic, boolean isStrike, boolean isHightLight,

	      STHighlightColor.Enum hightStyle, boolean isShd,

	      STShd.Enum shdStyle, String shdColor, VerticalAlign verticalAlign,

	      int position, int spacingValue, int indent) {

	    setParagraphTextStyleInfo(p, pRun, colorVal, isBlod, isUnderLine,

	        underLineColor, underStyle, isItalic, isStrike, false, false,

	        false, false, false, false, false, null, isHightLight,

	        hightStyle, isShd, shdStyle, shdColor, verticalAlign, position,

	        spacingValue, indent);

	  }

	 

	  /**
	   * @Description: 设置段落文本样式(高亮与底纹显示效果不同)设置字符间距信息(CTSignedTwipsMeasure)
	   * @param verticalAlign : SUPERSCRIPT上标 SUBSCRIPT下标
	   * @param position :字符间距位置：>0提升 <0降低=磅值*2 如3磅=6
	   * @param spacingValue :字符间距间距 >0加宽 <0紧缩 =磅值*20 如2磅=40
	   * @param indent :字符间距缩进 <100 缩
	   */

	  public void setParagraphTextStyleInfo(XWPFParagraph p, XWPFRun pRun,

	      String colorVal, boolean isBlod, boolean isUnderLine,

	      String underLineColor, STUnderline.Enum underStyle,

	      boolean isItalic, boolean isStrike, boolean isDStrike,

	      boolean isShadow, boolean isVanish, boolean isEmboss,

	      boolean isImprint, boolean isOutline, boolean isEm,

	      STEm.Enum emType, boolean isHightLight,

	      STHighlightColor.Enum hightStyle, boolean isShd,

	      STShd.Enum shdStyle, String shdColor, VerticalAlign verticalAlign,

	      int position, int spacingValue, int indent) {

	    if (pRun == null) {
	      return;
	    }

	    CTRPr pRpr = getRunCTRPr(p, pRun);
	    if (colorVal != null) {
	      pRun.setColor(colorVal);
	    }

	    // 设置字体样式
	    // 加粗
	    if (isBlod) {
	      pRun.setBold(isBlod);
	    }

	    // 倾斜
	    if (isItalic) {
	      pRun.setItalic(isItalic);
	    }

	    // 删除线
	    if (isStrike) {
	      pRun.setStrikeThrough(isStrike);
	    }

	    // 双删除线
	    if (isDStrike) {
	      CTOnOff dsCtOnOff = pRpr.isSetDstrike() ? pRpr.getDstrike() : pRpr.addNewDstrike();
	      dsCtOnOff.setVal(STOnOff.TRUE);
	    }

	    // 阴影
	    if (isShadow) {
	      CTOnOff shadowCtOnOff = pRpr.isSetShadow() ? pRpr.getShadow() : pRpr.addNewShadow();
	      shadowCtOnOff.setVal(STOnOff.TRUE);
	    }

	    // 隐藏
	    if (isVanish) {
	      CTOnOff vanishCtOnOff = pRpr.isSetVanish() ? pRpr.getVanish()
	          : pRpr.addNewVanish();
	      vanishCtOnOff.setVal(STOnOff.TRUE);
	    }

	    // 阳文
	    if (isEmboss) {
	      CTOnOff embossCtOnOff = pRpr.isSetEmboss() ? pRpr.getEmboss()
	          : pRpr.addNewEmboss();
	      embossCtOnOff.setVal(STOnOff.TRUE);
	    }

	    // 阴文
	    if (isImprint) {
	      CTOnOff isImprintCtOnOff = pRpr.isSetImprint() ? pRpr.getImprint()
	          : pRpr.addNewImprint();
	      isImprintCtOnOff.setVal(STOnOff.TRUE);
	    }

	    // 空心

	    if (isOutline) {
	      CTOnOff isOutlineCtOnOff = pRpr.isSetOutline() ? pRpr.getOutline()
	          : pRpr.addNewOutline();
	      isOutlineCtOnOff.setVal(STOnOff.TRUE);
	    }

	    // 着重号

	    if (isEm) {
	      CTEm em = pRpr.isSetEm() ? pRpr.getEm() : pRpr.addNewEm();
	      em.setVal(emType);
	    }

	    // 设置下划线样式
	    if (isUnderLine) {
	      CTUnderline u = pRpr.isSetU() ? pRpr.getU() : pRpr.addNewU();
	      if (underStyle != null) {
	        u.setVal(underStyle);
	      }

	      if (underLineColor != null) {
	        u.setColor(underLineColor);
	      }
	    }

	    // 设置突出显示文本
	    if (isHightLight) {
	      if (hightStyle != null) {
	        CTHighlight hightLight = pRpr.isSetHighlight() ? pRpr
	            .getHighlight() : pRpr.addNewHighlight();
	        hightLight.setVal(hightStyle);
	      }
	    }

	    if (isShd) {
	      // 设置底纹
	      CTShd shd = pRpr.isSetShd() ? pRpr.getShd() : pRpr.addNewShd();
	      if (shdStyle != null) {
	        shd.setVal(shdStyle);
	      }

	      if (shdColor != null) {
	        shd.setColor(shdColor);
	      }
	    }

	    // 上标下标
	    if (verticalAlign != null) {
	      pRun.setSubscript(verticalAlign);
	    }

	    // 设置文本位置
	    pRun.setTextPosition(position);

	    if (spacingValue > 0) {
	      // 设置字符间距信息
	      CTSignedTwipsMeasure ctSTwipsMeasure = pRpr.isSetSpacing() ? pRpr
	          .getSpacing() : pRpr.addNewSpacing();
	      ctSTwipsMeasure.setVal(new BigInteger(String.valueOf(spacingValue)));
	    }

	    if (indent > 0) {
	      CTTextScale paramCTTextScale = pRpr.isSetW() ? pRpr.getW() : pRpr.addNewW();
	      paramCTTextScale.setVal(indent);
	    }
	  }

	 

	  /**
	   * @Description: 设置段落底纹(对整段文字起作用)
	   */
	  public void setParagraphShdStyle(XWPFParagraph p, boolean isShd,
	      STShd.Enum shdStyle, String shdColor) {

	    CTPPr pPpr = getParagraphCTPPr(p);

	    CTShd shd = pPpr.isSetShd() ? pPpr.getShd() : pPpr.addNewShd();

	    if (shdStyle != null) {
	      shd.setVal(shdStyle);
	    }

	    if (shdColor != null) {
	      shd.setColor(shdColor);
	    }
	  }

	  /**
	   * @Description: 设置段落间距信息,一行=100 一磅=20
	   */
	  public void setParagraphSpacingInfo(XWPFParagraph p, boolean isSpace,
	      String before, String after, String beforeLines, String afterLines,
	      boolean isLine, String line, STLineSpacingRule.Enum lineValue) {

	    CTPPr pPPr = getParagraphCTPPr(p);
	    CTSpacing pSpacing = pPPr.getSpacing() != null ? pPPr.getSpacing()
	        : pPPr.addNewSpacing();

	    if (isSpace) {
	      // 段前磅数
	      if (before != null) {
	        pSpacing.setBefore(new BigInteger(before));
	      }

	      // 段后磅数
	      if (after != null) {
	        pSpacing.setAfter(new BigInteger(after));
	      }

	      // 段前行数
	      if (beforeLines != null) {
	        pSpacing.setBeforeLines(new BigInteger(beforeLines));
	      }

	      // 段后行数
	      if (afterLines != null) {
	        pSpacing.setAfterLines(new BigInteger(afterLines));
	      }
	    }

	    // 间距
	    if (isLine) {
	      if (line != null) {
	        pSpacing.setLine(new BigInteger(line));
	      }

	      if (lineValue != null) {
	        pSpacing.setLineRule(lineValue);
	      }
	    }
	  }

	 

	  // 设置段落缩进信息 1厘米≈567
	  public void setParagraphIndInfo(XWPFParagraph p, String firstLine,
	      String firstLineChar, String hanging, String hangingChar,
	      String right, String rigthChar, String left, String leftChar) {

	    CTPPr pPPr = getParagraphCTPPr(p);

	    CTInd pInd = pPPr.getInd() != null ? pPPr.getInd() : pPPr.addNewInd();

	    if (firstLine != null) {

	      pInd.setFirstLine(new BigInteger(firstLine));

	    }

	    if (firstLineChar != null) {

	      pInd.setFirstLineChars(new BigInteger(firstLineChar));

	    }

	    if (hanging != null) {

	      pInd.setHanging(new BigInteger(hanging));

	    }

	    if (hangingChar != null) {

	      pInd.setHangingChars(new BigInteger(hangingChar));

	    }

	    if (left != null) {

	      pInd.setLeft(new BigInteger(left));

	    }

	    if (leftChar != null) {

	      pInd.setLeftChars(new BigInteger(leftChar));

	    }

	    if (right != null) {

	      pInd.setRight(new BigInteger(right));

	    }

	    if (rigthChar != null) {

	      pInd.setRightChars(new BigInteger(rigthChar));

	    }

	  }

	 

	  // 设置段落边框

	  public void setParagraphBorders(XWPFParagraph p, Borders lborder,

	      Borders tBorders, Borders rBorders, Borders bBorders,

	      Borders btborders) {

	    if (lborder != null) {

	      p.setBorderLeft(lborder);

	    }

	    if (tBorders != null) {

	      p.setBorderTop(tBorders);

	    }

	    if (rBorders != null) {

	      p.setBorderRight(rBorders);

	    }

	    if (bBorders != null) {

	      p.setBorderBottom(bBorders);

	    }

	    if (btborders != null) {

	      p.setBorderBetween(btborders);

	    }

	  }

	 

	  /**
	   * @Description: 设置段落对齐
	   */
	  public void setParagraphAlignInfo(XWPFParagraph p,
	      ParagraphAlignment pAlign, TextAlignment valign) {

	    if (pAlign != null) {
	      p.setAlignment(pAlign);
	    }

	    if (valign != null) {
	      p.setVerticalAlignment(valign);
	    }

	  }
	  /**
	   * 增加自定义标题样式。这里用的是stackoverflow的源码
	   * 
	   * @param docxDocument 目标文档
	   * @param strStyleId 样式名称
	   * @param headingLevel 样式级别
	   */
	  private static void addCustomHeadingStyle(XWPFDocument docxDocument, String strStyleId, int headingLevel) {

	      CTStyle ctStyle = CTStyle.Factory.newInstance();
	      ctStyle.setStyleId(strStyleId);

	      CTString styleName = CTString.Factory.newInstance();
	      styleName.setVal(strStyleId);
	      ctStyle.setName(styleName);

	      CTDecimalNumber indentNumber = CTDecimalNumber.Factory.newInstance();
	      indentNumber.setVal(BigInteger.valueOf(headingLevel));

	      // lower number > style is more prominent in the formats bar
	      ctStyle.setUiPriority(indentNumber);

	      CTOnOff onoffnull = CTOnOff.Factory.newInstance();
	      ctStyle.setUnhideWhenUsed(onoffnull);

	      // style shows up in the formats bar
	      ctStyle.setQFormat(onoffnull);

	      // style defines a heading of the given level
	      CTPPr ppr = CTPPr.Factory.newInstance();
	      ppr.setOutlineLvl(indentNumber);
	      ctStyle.setPPr(ppr);

	      XWPFStyle style = new XWPFStyle(ctStyle);

	      // is a null op if already defined
	      XWPFStyles styles = docxDocument.createStyles();

	      style.setType(STStyleType.PARAGRAPH);
	      styles.addStyle(style);

	  }
	  
	  /*------------------------------------Word 表格相关---------------------------------------------------  */

	  /**

	   * @Description:删除指定位置的表格,被删除表格后的索引位置--

	   */

	  public void deleteTableByIndex(XWPFDocument xdoc, int pos) {

	    Iterator<IBodyElement> bodyElement = xdoc.getBodyElementsIterator();

	    int eIndex = 0, tableIndex = -1;

	    while (bodyElement.hasNext()) {

	      IBodyElement element = bodyElement.next();

	      BodyElementType elementType = element.getElementType();

	      if (elementType == BodyElementType.TABLE) {

	        tableIndex++;

	        if (tableIndex == pos) {

	          break;

	        }

	      }

	      eIndex++;

	    }

	    xdoc.removeBodyElement(eIndex);

	  }

	 

	  public XWPFTable getTableByIndex(XWPFDocument xdoc, int index) {

	    List<XWPFTable> tablesList = getAllTable(xdoc);

	    if (tablesList == null || index < 0 || index > tablesList.size()) {

	      return null;

	    }

	    return tablesList.get(index);

	  }

	 

	  public List<XWPFTable> getAllTable(XWPFDocument xdoc) {

	    return xdoc.getTables();

	  }

	 

	  /**

	   * @Description: 得到表格内容(第一次跨行单元格视为一个，第二次跳过跨行合并的单元格)

	   */

	  public List<List<String>> getTableRContent(XWPFTable table) {

	    List<List<String>> tableContentList = new ArrayList<List<String>>();

	    for (int rowIndex = 0, rowLen = table.getNumberOfRows(); rowIndex < rowLen; rowIndex++) {

	      XWPFTableRow row = table.getRow(rowIndex);

	      List<String> cellContentList = new ArrayList<String>();

	      for (int colIndex = 0, colLen = row.getTableCells().size(); colIndex < colLen; colIndex++) {

	        XWPFTableCell cell = row.getCell(colIndex);

	        CTTc ctTc = cell.getCTTc();

	        if (ctTc.isSetTcPr()) {

	          CTTcPr tcPr = ctTc.getTcPr();

	          if (tcPr.isSetHMerge()) {

	            CTHMerge hMerge = tcPr.getHMerge();

	            if (STMerge.RESTART.equals(hMerge.getVal())) {

	              cellContentList.add(getTableCellContent(cell));

	            }

	          } else if (tcPr.isSetVMerge()) {

	            CTVMerge vMerge = tcPr.getVMerge();

	            if (STMerge.RESTART.equals(vMerge.getVal())) {

	              cellContentList.add(getTableCellContent(cell));

	            }

	          } else {

	            cellContentList.add(getTableCellContent(cell));

	          }

	        }

	      }

	      tableContentList.add(cellContentList);

	    }

	    return tableContentList;

	  }

	 

	  /**

	   * @Description: 得到表格内容,合并后的单元格视为一个单元格

	   */

	  public List<List<String>> getTableContent(XWPFTable table) {

	    List<List<String>> tableContentList = new ArrayList<List<String>>();

	    for (int rowIndex = 0, rowLen = table.getNumberOfRows(); rowIndex < rowLen; rowIndex++) {

	      XWPFTableRow row = table.getRow(rowIndex);

	      List<String> cellContentList = new ArrayList<String>();

	      for (int colIndex = 0, colLen = row.getTableCells().size(); colIndex < colLen; colIndex++) {

	        XWPFTableCell cell = row.getCell(colIndex);

	        cellContentList.add(getTableCellContent(cell));

	      }

	      tableContentList.add(cellContentList);

	    }

	    return tableContentList;

	  }

	 

	  public String getTableCellContent(XWPFTableCell cell) {

	    StringBuffer sb = new StringBuffer();

	    List<XWPFParagraph> cellPList = cell.getParagraphs();

	    if (cellPList != null && cellPList.size() > 0) {

	      for (XWPFParagraph xwpfPr : cellPList) {

	        List<XWPFRun> runs = xwpfPr.getRuns();

	        if (runs != null && runs.size() > 0) {

	          for (XWPFRun xwpfRun : runs) {

	            sb.append(xwpfRun.getText(0));

	          }

	        }

	      }

	    }

	    return sb.toString();

	  }

	 

	  /**

	   * @Description: 创建表格,创建后表格至少有1行1列,设置列宽

	   */

	  public XWPFTable createTable(XWPFDocument xdoc, int rowSize, int cellSize,

	      boolean isSetColWidth, int[] colWidths) {

	    XWPFTable table = xdoc.createTable(rowSize, cellSize);

	    if (isSetColWidth) {

	      CTTbl ttbl = table.getCTTbl();

	      CTTblGrid tblGrid = ttbl.addNewTblGrid();

	      for (int j = 0, len = Math.min(cellSize, colWidths.length); j < len; j++) {

	        CTTblGridCol gridCol = tblGrid.addNewGridCol();

	        gridCol.setW(new BigInteger(String.valueOf(colWidths[j])));

	      }

	    }

	    return table;

	  }

	 

	  /**

	   * @Description: 设置表格总宽度与水平对齐方式

	   */

	  public void setTableWidthAndHAlign(XWPFTable table, String width,

	      STJc.Enum enumValue) {

	    CTTblPr tblPr = getTableCTTblPr(table);

	    // 表格宽度

	    CTTblWidth tblWidth = tblPr.isSetTblW() ? tblPr.getTblW() : tblPr

	        .addNewTblW();

	    if (enumValue != null) {

	      CTJc cTJc = tblPr.addNewJc();

	      cTJc.setVal(enumValue);

	    }

	    // 设置宽度

	    tblWidth.setW(new BigInteger(width));

	    tblWidth.setType(STTblWidth.DXA);

	  }

	 

	  /**

	   * @Description: 得到Table的CTTblPr,不存在则新建

	   */

	  public CTTblPr getTableCTTblPr(XWPFTable table) {

	    CTTbl ttbl = table.getCTTbl();

	    // 表格属性

	    CTTblPr tblPr = ttbl.getTblPr() == null ? ttbl.addNewTblPr() : ttbl

	        .getTblPr();

	    return tblPr;

	  }

	 

	  /**

	   * @Description: 得到Table的边框,不存在则新建

	   */

	  public CTTblBorders getTableBorders(XWPFTable table) {

	    CTTblPr tblPr = getTableCTTblPr(table);

	    CTTblBorders tblBorders = tblPr.isSetTblBorders() ? tblPr

	        .getTblBorders() : tblPr.addNewTblBorders();

	    return tblBorders;

	  }

	 

	  /**

	   * @Description: 设置表格边框样式

	   */

	  public void setTableBorders(XWPFTable table, CTBorder left, CTBorder top,

	      CTBorder right, CTBorder bottom) {

	    CTTblBorders tblBorders = getTableBorders(table);

	    if (left != null) {

	      tblBorders.setLeft(left);

	    }

	    if (top != null) {

	      tblBorders.setTop(top);

	    }

	    if (right != null) {

	      tblBorders.setRight(right);

	    }

	    if (bottom != null) {

	      tblBorders.setBottom(bottom);

	 

	    }

	  }

	 

	  /**

	   * @Description: 表格指定位置插入一行, index为新增行所在的行位置(不能大于表行数)

	   */

	  public void insertTableRowAtIndex(XWPFTable table, int index) {

	    XWPFTableRow firstRow = table.getRow(0);

	    XWPFTableRow row = table.insertNewTableRow(index);

	    if (row == null) {

	      return;

	    }

	    CTTbl ctTbl = table.getCTTbl();

	    CTTblGrid tblGrid = ctTbl.getTblGrid();

	    int cellSize = 0;

	    boolean isAdd = false;

	    if (tblGrid != null) {

	      List<CTTblGridCol> gridColList = tblGrid.getGridColList();

	      if (gridColList != null && gridColList.size() > 0) {

	        isAdd = true;

	        for (CTTblGridCol ctlCol : gridColList) {

	          // 新增单元格

	          XWPFTableCell cell = row.addNewTableCell();

	          // 设置行的高度

	          // row.setHeight(500);

	          setCellWidthAndVAlign(cell, ctlCol.getW().toString(),

	              STTblWidth.DXA, null);

	        }

	      }

	    }

	    // 大部分都不会走到这一步

	    if (!isAdd) {

	      cellSize = getCellSizeWithMergeNum(firstRow);

	      for (int i = 0; i < cellSize; i++) {

	        row.addNewTableCell();

	      }

	    }

	  }

	 

	  /**

	   * @Description: 删除表一行

	   */

	  public void deleteTableRow(XWPFTable table, int index) {

	    table.removeRow(index);

	  }

	 

	  /**

	   * @Description: 统计列数(包括合并的列数)

	   */

	  public int getCellSizeWithMergeNum(XWPFTableRow row) {

	    List<XWPFTableCell> firstRowCellList = row.getTableCells();

	    int cellSize = firstRowCellList.size();

	    for (XWPFTableCell xwpfTableCell : firstRowCellList) {

	      CTTc ctTc = xwpfTableCell.getCTTc();

	      if (ctTc.isSetTcPr()) {

	        CTTcPr tcPr = ctTc.getTcPr();

	        if (tcPr.isSetGridSpan()) {

	          CTDecimalNumber gridSpan = tcPr.getGridSpan();

	          cellSize += gridSpan.getVal().intValue() - 1;

	        }

	      }

	    }

	    return cellSize;

	  }

	 

	  /**

	   * @Description: 得到CTTrPr,不存在则新建

	   */

	  public CTTrPr getRowCTTrPr(XWPFTableRow row) {

	    CTRow ctRow = row.getCtRow();

	    // 行属性

	    CTTrPr trPr = ctRow.isSetTrPr() ? ctRow.getTrPr() : ctRow.addNewTrPr();

	    return trPr;

	  }

	 

	  /**

	   * @Description: 设置行高

	   */

	  public void setRowHeight(XWPFTableRow row, String hight,

	      STHeightRule.Enum heigthEnum) {

	    CTTrPr trPr = getRowCTTrPr(row);

	    CTHeight trHeight;

	    if (trPr.getTrHeightList() != null && trPr.getTrHeightList().size() > 0) {

	      trHeight = trPr.getTrHeightList().get(0);

	    } else {

	      trHeight = trPr.addNewTrHeight();

	    }

	    trHeight.setVal(new BigInteger(hight));

	    if (heigthEnum != null) {

	      trHeight.setHRule(heigthEnum);

	    }

	  }

	 

	  /**

	   * @Description: 隐藏行

	   */

	  public void setRowHidden(XWPFTableRow row, boolean hidden) {

	    CTTrPr trPr = getRowCTTrPr(row);

	    CTOnOff hiddenValue;

	    if (trPr.getHiddenList() != null && trPr.getHiddenList().size() > 0) {

	      hiddenValue = trPr.getHiddenList().get(0);

	    } else {

	      hiddenValue = trPr.addNewHidden();

	    }

	    if (hidden) {

	      hiddenValue.setVal(STOnOff.TRUE);

	    } else {

	      hiddenValue.setVal(STOnOff.FALSE);

	    }

	    setRowAllCellHidden(row, hidden);

	  }

	 

	  public void setRowAllCellHidden(XWPFTableRow row, boolean isVanish) {

	    for (int colIndex = 0, colLen = row.getTableCells().size(); colIndex < colLen; colIndex++) {

	      XWPFTableCell cell = row.getCell(colIndex);

	      setCellHidden(cell, isVanish);

	    }

	  }

	 

	  /**

	   * @Description: 设置单元格内容

	   */

	  public void setCellNewContent(XWPFTable table, int rowIndex, int col,

	      String content) {

	    XWPFTableCell cell = table.getRow(rowIndex).getCell(col);

	    XWPFParagraph p = getCellFirstParagraph(cell);

	    List<XWPFRun> cellRunList = p.getRuns();

	    if (cellRunList == null || cellRunList.size() == 0) {

	      return;

	    }

	    for (int i = cellRunList.size() - 1; i >= 1; i--) {

	      p.removeRun(i);

	    }

	    XWPFRun run = cellRunList.get(0);

	    run.setText(content);

	  }

	 

	  /**

	   * @Description: 删除单元格内容

	   */

	  public void deleteCellContent(XWPFTable table, int rowIndex, int col) {

	    XWPFTableCell cell = table.getRow(rowIndex).getCell(col);

	    XWPFParagraph p = getCellFirstParagraph(cell);

	    List<XWPFRun> cellRunList = p.getRuns();

	    if (cellRunList == null || cellRunList.size() == 0) {

	      return;

	    }

	    for (int i = cellRunList.size() - 1; i >= 0; i--) {

	      p.removeRun(i);

	    }

	  }

	 

	  /**

	   * @Description: 隐藏单元格内容

	   */

	  public void setHiddenCellContent(XWPFTable table, int rowIndex, int col) {

	    XWPFTableCell cell = table.getRow(rowIndex).getCell(col);

	    setCellHidden(cell, true);

	  }

	 

	  public void setCellHidden(XWPFTableCell cell, boolean isVanish) {

	    XWPFParagraph p = getCellFirstParagraph(cell);

	    CTPPr pPPr = getParagraphCTPPr(p);

	    CTParaRPr paRpr = pPPr.isSetRPr() ? pPPr.getRPr() : pPPr.addNewRPr();

	    CTOnOff vanishCtOnOff = paRpr.isSetVanish() ? paRpr.getVanish() : paRpr

	        .addNewVanish();

	    if (isVanish) {

	      vanishCtOnOff.setVal(STOnOff.TRUE);

	    } else {

	      vanishCtOnOff.setVal(STOnOff.FALSE);

	    }

	    List<XWPFRun> cellRunList = p.getRuns();

	    if (cellRunList == null || cellRunList.size() == 0) {

	      return;

	    }

	    for (XWPFRun xwpfRun : cellRunList) {

	      CTRPr pRpr = getRunCTRPr(p, xwpfRun);

	      vanishCtOnOff = pRpr.isSetVanish() ? pRpr.getVanish() : pRpr

	          .addNewVanish();

	      if (isVanish) {

	        vanishCtOnOff.setVal(STOnOff.TRUE);

	      } else {

	        vanishCtOnOff.setVal(STOnOff.FALSE);

	      }

	    }

	  }

	 

	  /**

	   * 

	   * @Description: 得到Cell的CTTcPr,不存在则新建

	   */

	  public CTTcPr getCellCTTcPr(XWPFTableCell cell) {

	    CTTc cttc = cell.getCTTc();

	    // 单元格属性

	    CTTcPr tcPr = cttc.isSetTcPr() ? cttc.getTcPr() : cttc.addNewTcPr();

	    return tcPr;

	  }

	 

	  /**

	   * @Description: 设置垂直对齐方式

	   */

	  public void setCellVAlign(XWPFTableCell cell, STVerticalJc.Enum vAlign) {

	    setCellWidthAndVAlign(cell, null, null, vAlign);

	  }

	 

	  /**

	   * @Description: 设置列宽和垂直对齐方式

	   */

	  public void setCellWidthAndVAlign(XWPFTableCell cell, String width,

	      STTblWidth.Enum typeEnum, STVerticalJc.Enum vAlign) {

	    CTTcPr tcPr = getCellCTTcPr(cell);

	    CTTblWidth tcw = tcPr.isSetTcW() ? tcPr.getTcW() : tcPr.addNewTcW();

	    if (width != null) {

	      tcw.setW(new BigInteger(width));

	    }

	    if (typeEnum != null) {

	      tcw.setType(typeEnum);

	    }

	    if (vAlign != null) {

	      CTVerticalJc vJc = tcPr.isSetVAlign() ? tcPr.getVAlign() : tcPr

	          .addNewVAlign();

	      vJc.setVal(vAlign);

	    }

	  }

	 

	  /**

	   * @Description: 得到单元格第一个Paragraph

	   */

	  public XWPFParagraph getCellFirstParagraph(XWPFTableCell cell) {

	    XWPFParagraph p;

	    if (cell.getParagraphs() != null && cell.getParagraphs().size() > 0) {

	      p = cell.getParagraphs().get(0);

	    } else {

	      p = cell.addParagraph();

	    }

	    return p;

	  }

	 

	  /**

	   * @Description: 跨列合并

	   */

	  public void mergeCellsHorizontal(XWPFTable table, int row, int fromCell,

	      int toCell) {

	    for (int cellIndex = fromCell; cellIndex <= toCell; cellIndex++) {

	      XWPFTableCell cell = table.getRow(row).getCell(cellIndex);

	      if (cellIndex == fromCell) {

	        // The first merged cell is set with RESTART merge value

	        getCellCTTcPr(cell).addNewHMerge().setVal(STMerge.RESTART);

	      } else {

	        // Cells which join (merge) the first one,are set with CONTINUE

	        getCellCTTcPr(cell).addNewHMerge().setVal(STMerge.CONTINUE);

	      }

	    }

	  }

	 

	  /**

	   * @Description: 跨行合并

	   * @see http://stackoverflow.com/questions/24907541/row-span-with-xwpftable

	   */

	  public void mergeCellsVertically(XWPFTable table, int col, int fromRow,

	      int toRow) {

	    for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {

	      XWPFTableCell cell = table.getRow(rowIndex).getCell(col);

	      if (rowIndex == fromRow) {

	        // The first merged cell is set with RESTART merge value

	        getCellCTTcPr(cell).addNewVMerge().setVal(STMerge.RESTART);

	      } else {

	        // Cells which join (merge) the first one,are set with CONTINUE

	        getCellCTTcPr(cell).addNewVMerge().setVal(STMerge.CONTINUE);

	      }

	    }

	  }

	 

	  /*------------------------------------Word 文档信息---------------------------------------------------  */

	  /**

	   * @Description: 设置页面背景色

	   */

	  public void setDocumentbackground(XWPFDocument document, String bgColor) {

	    CTBackground bg = document.getDocument().isSetBackground() ? document

	        .getDocument().getBackground() : document.getDocument()

	        .addNewBackground();

	    bg.setColor(bgColor);

	  }

	 

	  public CTSectPr getDocumentCTSectPr(XWPFDocument document) {

	    CTSectPr sectPr = document.getDocument().getBody().isSetSectPr() ? document

	        .getDocument().getBody().getSectPr()

	        : document.getDocument().getBody().addNewSectPr();

	    return sectPr;

	  }

	 

	  /**

	   * @Description: 页面Break

	   */

	  public void addNewPageBreak(XWPFDocument document, BreakType breakType) {

	    XWPFParagraph xp = document.createParagraph();

	    xp.createRun().addBreak(breakType);

	  }

	 

	  /**

	   * @Description: 设置页面边框

	   */

	  public void setPgBorders(XWPFDocument document, CTBorder top,

	      CTBorder right, CTBorder bottom, CTBorder left) {

	    CTSectPr sectPr = getDocumentCTSectPr(document);

	    CTPageBorders pd = sectPr.isSetPgBorders() ? sectPr.getPgBorders()

	        : sectPr.addNewPgBorders();

	    /*

	     * CTBorder bb = pd.addNewBottom(); bb.setVal(STBorder.SINGLE);

	     * bb.setSz(new BigInteger("4")); bb.setSpace(new BigInteger("24"));

	     * bb.setColor("FBB61F");

	     */

	    if (top != null) {

	      pd.setTop(top);

	    }

	    if (right != null) {

	      pd.setRight(right);

	    }

	    if (bottom != null) {

	      pd.setBottom(bottom);

	    }

	    if (left != null) {

	      pd.setLeft(left);

	    }

	  }

	 

	  /**
	   * @Description: 设置页面大小及纸张方向 landscape横向
	   */
	  public void setDocumentSize(XWPFDocument document, String width,
	      String height, STPageOrientation.Enum stValue) {

	    CTSectPr sectPr = getDocumentCTSectPr(document);
	    CTPageSz pgsz = sectPr.isSetPgSz() ? sectPr.getPgSz() : sectPr
	        .addNewPgSz();
	    pgsz.setH(new BigInteger(height));
	    pgsz.setW(new BigInteger(width));
	    pgsz.setOrient(stValue);

	  }
	 
	  
	  /**
	   * @Description: 设置页边距 (word中1厘米约等于567)
	   */
	  public void setDocumentMargin(XWPFDocument document, String left,
	      String top, String right, String bottom) {

	    CTSectPr sectPr = getDocumentCTSectPr(document);

	    CTPageMar ctpagemar = sectPr.addNewPgMar();

	    if (StringUtils.isNotBlank(left)) {
	      ctpagemar.setLeft(new BigInteger(left));
	    }

	    if (StringUtils.isNotBlank(top)) {
	      ctpagemar.setTop(new BigInteger(top));
	    }

	    if (StringUtils.isNotBlank(right)) {
	      ctpagemar.setRight(new BigInteger(right));
	    }

	    if (StringUtils.isNotBlank(bottom)) {
	      ctpagemar.setBottom(new BigInteger(bottom));
	    }
	  }
}

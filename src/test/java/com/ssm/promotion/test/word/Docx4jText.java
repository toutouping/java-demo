package com.ssm.promotion.test.word;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.docx4j.convert.in.xhtml.FormattingOption;
import org.docx4j.convert.in.xhtml.XHTMLImporter;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidFormatException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.docx4j.services.client.Converter;
import org.docx4j.services.client.ConverterHttp;
import org.docx4j.services.client.Format;
import org.docx4j.wml.RFonts;
import org.docx4j.Docx4jProperties;
import org.docx4j.convert.in.word2003xml.Word2003XmlConverter;
import org.junit.Test;

import com.deepoove.poi.XWPFTemplate;
import com.ssm.promotion.docx4j.utils.Programatically;

public class Docx4jText {
	private final static String SRC_RESOURCES = "src/template/";
	private final static String OUT_RESOURCES = "src/output/";

	@Test
	public void textExport () throws Exception {
        
        WordprocessingMLPackage wordMLPackage = null; // WordprocessingMLPackage.createPackage();
        XHTMLImporter xHTMLImporter= null;
	    Class<?> xhtmlImporterClass = null;
		try {
			Map<String, Object> datas = new HashMap<String, Object>();
			datas.put("html", "http://www.deepoove.com");
			XWPFTemplate template = XWPFTemplate.compile(SRC_RESOURCES + "template.docx").render(datas);
	        ByteArrayOutputStream os = new ByteArrayOutputStream();
	        template.write(os);
			byte[] content = os.toByteArray();
			InputStream is = new ByteArrayInputStream(content);
			
			xhtmlImporterClass = Class.forName("org.docx4j.convert.in.xhtml.XHTMLImporterImpl");
	        Constructor<?> ctor;
			ctor = xhtmlImporterClass.getConstructor(WordprocessingMLPackage.class);
			wordMLPackage = WordprocessingMLPackage.load(is);

	       /* RFonts rfonts = Context.getWmlObjectFactory().createRFonts();
	        rfonts.setAscii("FangSong");
	        XHTMLImporterImpl.addFontMapping("仿宋", rfonts);
	        wordMLPackage.getMainDocumentPart().getPropertyResolver()
	                .getDocumentDefaultRPr().setRFonts(rfonts);*/

			xHTMLImporter = (XHTMLImporter) ctor.newInstance(wordMLPackage);
			
			HashMap<String, String> mappings = new HashMap<String, String>();
			mappings.put("HTML_A", "<div style='font-size:14pt;'><p><span style=\"font-size: 10.5pt;\">%</span></p><p></p></div>");
			Iterator iterator = mappings.entrySet().iterator();
			while (iterator.hasNext()) {
			    Map.Entry mapEntry = (Map.Entry) iterator.next();
			    String xhtml = "<div style=\"font-family:FangSong, Helvetica, sans-serif;min-height: 100px;text-align: left;\">" + mapEntry.getValue().toString() + "</div>"; 
			    int locationOfItem =  Programatically.findPlaceHolder(mapEntry.getKey().toString(), wordMLPackage);
			    if (locationOfItem > 0) {
			        wordMLPackage.getMainDocumentPart().getContent().addAll(locationOfItem , xHTMLImporter.convert(xhtml, null)  );   
			    }
			}
			/*ByteArrayOutputStream baos = new ByteArrayOutputStream();
			// wordMLPackage.save(new File(OUT_RESOURCES + "out.doc") );
			
			String URL = Docx4jProperties.getProperty("com.plutext.converter.URL", "http://converter-eval.plutext.com:80/v1/00000000-0000-0000-0000-000000000000/convert");
			Converter converter = new ConverterHttp(URL);
			converter.convert(baos.toByteArray(), Format.DOCX, Format.DOC, outputStream);*/
			OutputStream outputStream = new  FileOutputStream(OUT_RESOURCES + "out.docx");
			
			Programatically.replacePlaceholder(wordMLPackage, "你好呀", "HTML_B");
			wordMLPackage.save(outputStream);
				
		} catch (InvalidFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (Docx4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void convertHtmlToDocx() throws Exception{
        //convert back to docx 

		String inputfilepath = SRC_RESOURCES + "template.docx";

        String stringFromFile = FileUtils.readFileToString(new File(inputfilepath), "UTF-8");

        String unescaped = stringFromFile;
        if (stringFromFile.contains("&lt;/") ) {
            unescaped = StringEscapeUtils.unescapeHtml(stringFromFile);         
        }      

        System.out.println("Unescaped: " + unescaped);

        // Setup font mapping
        RFonts rfonts = Context.getWmlObjectFactory().createRFonts();
        rfonts.setAscii("FangSong");
        XHTMLImporterImpl.addFontMapping("仿宋", rfonts);

        // Create an empty docx package
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

        Mapper fontMapper = new IdentityPlusMapper(); 
        fontMapper.put("仿宋",PhysicalFonts.get("FangSong"));
        wordMLPackage.setFontMapper(fontMapper);
        
        NumberingDefinitionsPart ndp = new NumberingDefinitionsPart();
        wordMLPackage.getMainDocumentPart().addTargetPart(ndp);
        ndp.unmarshalDefaultNumbering();        

        // Convert the XHTML, and add it into the empty docx we made
        XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
        XHTMLImporter.setTableFormatting(FormattingOption.IGNORE_CLASS);
        XHTMLImporter.setParagraphFormatting(FormattingOption.IGNORE_CLASS);
        XHTMLImporter.setHyperlinkStyle("Hyperlink");
        

		HashMap<String, String> mappings = new HashMap<String, String>();
		mappings.put("{{html_content}}", "<p ><span style=\"FONT-FAMILY: '仿宋';font-size: 10.5pt;\">尊敬的领导：</span><br></br></p><p>     你好！</p><p>    我是。。。。</p><p><br></br></p><p><span style=\"font-weight: bold;\">一、开启工作状态</span></p><p>    阿萨德发看见爱上对方<br></br></p><p><span style=\"font-weight: bold;\">二、开启发展</span></p><p>    阿斯顿风口浪尖阿萨德发</p><p><br></br></p><p>                                                                                    阿萨德了房间卡代理费</p>");
		Iterator iterator = mappings.entrySet().iterator();
		while (iterator.hasNext()) {
		    Map.Entry mapEntry = (Map.Entry) iterator.next();
		    String xhtml = "<div>" + mapEntry.getValue().toString() + "</div>"; 
		    int locationOfItem =  Programatically.findPlaceHolder(mapEntry.getKey().toString(), wordMLPackage);
		    if (locationOfItem > 0) {
		        wordMLPackage.getMainDocumentPart().getContent().addAll(locationOfItem , XHTMLImporter.convert(xhtml, null)  );   
		    }
		}
        wordMLPackage.save(new File(OUT_RESOURCES + "myPath.docx") );

    }

}

package com.ssm.promotion.docx4j.section;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.P;

import com.ssm.promotion.docx4j.item.DocText;
import com.ssm.promotion.docx4j.style.custom.DocxStyleEnum;
import com.ssm.promotion.docx4j.utils.SectionFactory;

/**
 * Class to manages section with title and simple text
 */
public class DocSectionText extends DocSimpleSection<DocText> {

	/**
	 * Default Implementation: with title
	 *
	 * @param title
	 * @param content
	 */
	public DocSectionText(String title, String content) {
		super(new DocText(title, "Heading1"), new DocText(content, DocxStyleEnum.TEXT_NORMAL));
	}

	/**
	 * Default Implementation: without title
	 *
	 * @param content
	 */
	public DocSectionText(String content) {
		super(new DocText(content, DocxStyleEnum.TEXT_NORMAL));
	}


	public DocSectionText(DocText text) {
		super(text);
	}

	public DocSectionText(DocText title, DocText content) {
		super(title, content);
	}

	@Override
	protected boolean hasValidContent() {
		if (this.hasContent())
			if (this.getContent().getText() != null)
				return true;
		return false;
	}

	@Override
	public DocText getContent() {
		return this.content;
	}


	@Override
	public P retrieveObject(WordprocessingMLPackage wordMLPackage) {
		if (this.hasValidContent())
			return SectionFactory.fromDocTextToParagraph(this.getContent());
		return null;
	}
}

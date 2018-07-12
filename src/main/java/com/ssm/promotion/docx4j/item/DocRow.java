package com.ssm.promotion.docx4j.item;


import java.util.List;

import com.ssm.promotion.docx4j.style.IDocxObjectStyle;
import com.ssm.promotion.docx4j.style.custom.DocxStyleEnum;


public class DocRow extends DocItem {

	private boolean header;
	private List<Object> row;

	/**
	 * Default Implementation
	 *
	 * @param row
	 */
	public DocRow(List<Object> row) {
		this(row, DocxStyleEnum.TABLE_NO_BORDER);
	}

	public DocRow(List<Object> row, IDocxObjectStyle style) {
		this(row, style, false);
	}

	public DocRow(List<Object> row, IDocxObjectStyle style, boolean header) {
		this.row = row;
		this.style = style;
		this.header = header;
	}

	public List<Object> getRow() {
		return row;
	}

	public boolean isHeader() {
		return header;
	}

	public void setHeader(boolean header) {
		this.header = header;
	}

}

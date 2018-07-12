package com.ssm.promotion.docx4j.item;

import java.io.Serializable;

import com.ssm.promotion.docx4j.style.IDocxObjectStyle;

/**
 * Abstract Class to manage style
 */
public abstract class DocItem implements Serializable {

	protected String styleId;
	protected IDocxObjectStyle style;

	public IDocxObjectStyle getStyle() {
		return style;
	}

	public void setStyle(IDocxObjectStyle style) {
		this.style = style;
	}

	public String getStyleId() {
		return styleId;
	}

	public void setStyleId(String styleId) {
		this.styleId = styleId;
	}
}

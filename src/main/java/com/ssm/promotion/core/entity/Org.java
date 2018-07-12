package com.ssm.promotion.core.entity;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SapOrg")
public class Org implements Serializable {
	   private static final long serialVersionUID = 7446548875479453298L;
	 
	    private String parentId;
	 
		private String parentDesc;
	 
	    private String childId;
	 
	    private String childDesc;
	 
	    private String childShort;
	 
	    private String orgSort;

	    public String getParentId() {
			return parentId;
		}

		public void setParentId(String parentId) {
			this.parentId = parentId;
		}

		public String getParentDesc() {
			return parentDesc;
		}

		public void setParentDesc(String parentDesc) {
			this.parentDesc = parentDesc;
		}

		public String getChildId() {
			return childId;
		}

		public void setChildId(String childId) {
			this.childId = childId;
		}

		public String getChildDesc() {
			return childDesc;
		}

		public void setChildDesc(String childDesc) {
			this.childDesc = childDesc;
		}

		public String getChildShort() {
			return childShort;
		}

		public void setChildShort(String childShort) {
			this.childShort = childShort;
		}

		public String getOrgSort() {
			return orgSort;
		}

		public void setOrgSort(String orgSort) {
			this.orgSort = orgSort;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

	    @Override
	    public String toString() {
	        return "Org [parent_id=" + parentId + ", parent_desc=" + parentDesc + ", child_id=" + childId
	                + ", child_desc=" + childDesc + ", child_short=" + childShort + ", org_sort=" + orgSort
	                + "]";
	    }
}

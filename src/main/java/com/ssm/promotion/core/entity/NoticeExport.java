package com.ssm.promotion.core.entity;

import java.io.Serializable;
import java.util.Date;

public class NoticeExport implements Serializable {

    private String versionNo = "";
    private String headerContent = "";
    private String dateString = null;
    private String contactUser = "";
    private String contactPhone = "";
    private String currentYear = "";
    private String detailContent = "";

    public String toString(){
        return "versionNo:" + this.versionNo + "headerContent:" + this.headerContent + "contactUser:" + this.contactUser;
    }
    public String getDetailContent() {
        return detailContent;
    }
    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }
    public String getHeaderContent() {
        return headerContent.replaceAll("&nbsp;", " ");
    }

    public void setHeaderContent(String headerContent) {
        this.headerContent = headerContent;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String date) {
        this.dateString = date;
    }

    public String getContactUser() {
        return contactUser;
    }

    public void setContactUser(String contactUser) {
        this.contactUser = contactUser;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(String currentYear) {
        this.currentYear = currentYear;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }
}

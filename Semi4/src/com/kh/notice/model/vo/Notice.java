package com.kh.notice.model.vo;

import java.sql.Date;

public class Notice {
	private int noticeNo; //Notice_NO	NUMBER
	private String noticeTitle; //Notice_TITLE	VARCHAR2(50 BYTE)
	private  String noticeContent; //Notice_CONTENT	VARCHAR2(4000 BYTE)
	private int noticeCount; //Notice_COUNT	NUMBER
	private Date createDate; //CREATE_DATE	DATE
	private Date modifyDate; //MODIFY_DATE	DATE
	private String status; //STATUS	VARCHAR2(2 BYTE)
	private String noticeWriter; //Notice_WRITER	VARCHAR2(20 BYTE)
	public Notice() {
		super();
	}
	
	public Notice(int noticeNo, String noticeTitle, String noticeContent, int noticeCount, Date createDate,
			Date modifyDate, String status, String noticeWriter) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeCount = noticeCount;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
		this.noticeWriter = noticeWriter;
	}
	
	public Notice(int noticeNo, String noticeTitle, String noticeWriter,int noticeCount, Date createDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeCount = noticeCount;
		this.noticeWriter = noticeWriter;
		this.createDate = createDate;
	}
	

	public Notice(int noticeNo, String noticeTitle, String noticeContent, String noticeWriter, Date createDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.createDate = createDate;
	}
	
	

	public Notice(int noticeNo, String noticeTitle, String noticeWriter, String noticeContent, int noticeCount,
			Date createDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.noticeContent = noticeContent;
		this.noticeCount = noticeCount;
		this.createDate = createDate;
	}
	
	

	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getNoticeCount() {
		return noticeCount;
	}
	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeCount=" + noticeCount + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", status=" + status + ", noticeWriter=" + noticeWriter + "]";
	}

}



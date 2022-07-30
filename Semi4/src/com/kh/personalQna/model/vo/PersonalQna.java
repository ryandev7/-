package com.kh.personalQna.model.vo;

import java.sql.Date;

public class PersonalQna {
	
	private int pqnaNo; // PQNA_NO	NUMBER
	private String pqnaTitle; // PQNA_TITLE	VARCHAR2(100 BYTE)
	private String pqnaContent; // PQNA_CONTENT	VARCHAR2(4000 BYTE)
	private String status; // STATUS	VARCHAR2(10 BYTE)
	private Date createDate; // CREATE_DATE	DATE
	private Date modifyDate;  // MODIFY_DATE	DATE
	private String pqnaAnswerStatus; // PQNA_ANSWER_ST	VARCHAR2(1 BYTE)
	private String pqnaWriter; // PQNA_WRITER	VARCHAR2(40 BYTE)
	
	public PersonalQna() {
		super();
	}

	public PersonalQna(int pqnaNo, String pqnaTitle, String pqnaContent, String status, Date createDate,
			Date modifyDate, String pqnaAnswerStatus, String pqnaWriter) {
		super();
		this.pqnaNo = pqnaNo;
		this.pqnaTitle = pqnaTitle;
		this.pqnaContent = pqnaContent;
		this.status = status;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.pqnaAnswerStatus = pqnaAnswerStatus;
		this.pqnaWriter = pqnaWriter;
	}
	
	// selectPqna 생성자
	public PersonalQna(int pqnaNo, String pqnaTitle, String pqnaContent, Date createDate, String pqnaWriter, String pqnaAnswerStatus) {
		super();
		this.pqnaNo = pqnaNo;
		this.pqnaTitle = pqnaTitle;
		this.pqnaContent = pqnaContent;
		this.createDate = createDate;
		this.pqnaWriter = pqnaWriter;
		this.pqnaAnswerStatus = pqnaAnswerStatus;
	}

	public int getPqnaNo() {
		return pqnaNo;
	}

	public void setPqnaNo(int pqnaNo) {
		this.pqnaNo = pqnaNo;
	}

	public String getPqnaTitle() {
		return pqnaTitle;
	}

	public void setPqnaTitle(String pqnaTitle) {
		this.pqnaTitle = pqnaTitle;
	}

	public String getPqnaContent() {
		return pqnaContent;
	}

	public void setPqnaContent(String pqnaContent) {
		this.pqnaContent = pqnaContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getPqnaAnswerStatus() {
		return pqnaAnswerStatus;
	}

	public void setPqnaAnswerStatus(String pqnaAnswerStatus) {
		this.pqnaAnswerStatus = pqnaAnswerStatus;
	}

	public String getPqnaWriter() {
		return pqnaWriter;
	}

	public void setPqnaWriter(String pqnaWriter) {
		this.pqnaWriter = pqnaWriter;
	}

	@Override
	public String toString() {
		return "PersonalQna [pqnaNo=" + pqnaNo + ", pqnaTitle=" + pqnaTitle + ", pqnaContent=" + pqnaContent
				+ ", status=" + status + ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", pqnaAnswerStatus="
				+ pqnaAnswerStatus + ", pqnaWriter=" + pqnaWriter + "]";
	}
	
	

	
}

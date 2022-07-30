package com.kh.personalQnaAnswer.model.vo;

import java.sql.Date;

public class PersonalQnaAnswer {
	
	private int pqnaNo;  //PQNA_NO	NUMBER
	private String pqnaWriter; //PQNA_WRITER	VARCHAR2(20 BYTE)
	private String pqnaContent; //PQNA_CONTENT	VARCHAR2(4000 BYTE)
	private Date createDate; //CREATE_DATE	DATE
	private Date modifyDate; //MODIFY_DATE	DATE
	private String status; //STATUS	VARCHAR2(10 BYTE)
	
	
	public PersonalQnaAnswer() {
		super();
	}


	public PersonalQnaAnswer(int pqnaNo, String pqnaWriter, String pqnaContent, Date createDate, Date modifyDate,
			String status) {
		super();
		this.pqnaNo = pqnaNo;
		this.pqnaWriter = pqnaWriter;
		this.pqnaContent = pqnaContent;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}
	


	public int getPqnaNo() {
		return pqnaNo;
	}


	public void setPqnaNo(int pqnaNo) {
		this.pqnaNo = pqnaNo;
	}


	public String getPqnaWriter() {
		return pqnaWriter;
	}


	public void setPqnaWriter(String pqnaWriter) {
		this.pqnaWriter = pqnaWriter;
	}


	public String getPqnaContent() {
		return pqnaContent;
	}


	public void setPqnaContent(String pqnaContent) {
		this.pqnaContent = pqnaContent;
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


	@Override
	public String toString() {
		return "PersonalQnaAnswer [pqnaNo=" + pqnaNo + ", pqnaWriter=" + pqnaWriter + ", pqnaContent=" + pqnaContent
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status=" + status + "]";
	}
	
	
	
	

}

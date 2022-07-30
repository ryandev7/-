package com.kh.product.model.vo;

import java.sql.Date;

public class QNA {
	private int qnaNo; // QNA_NO	NUMBER
	private String qnaTitle; // QNA_TITLE	VARCHAR2(100 BYTE)
	private String qnaContent; // QNA_CONTENT	VARCHAR2(4000 BYTE)
	private String status; // STATUS	VARCHAR2(1 BYTE)
	private Date createDate; // CREATE_DATE	DATE
	private Date modifyDate; // MODIFY_DATE	DATE
	private int productNo; // PRODUCT_NO	NUMBER
	private String qnaWriter; // QNA_WRITER	VARCHAR2(20 BYTE)
	private String qnaAnswerSt; // QNA_ANSWER_ST	VARCHAR2(1 BYTE)
	
	public QNA() {
		super();
	}

	public QNA(int qnaNo, String qnaTitle, String qnaContent, String status, Date createDate, Date modifyDate,
			int productNo, String qnaWriter, String qnaAnswerSt) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.status = status;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.productNo = productNo;
		this.qnaWriter = qnaWriter;
		this.qnaAnswerSt = qnaAnswerSt;
	}
	
	
	public QNA(int qnaNo, String qnaTitle, String qnaContent, Date createDate, String qnaWriter, String qnaAnswerSt) {
		super();
		this.qnaNo = qnaNo;
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.createDate = createDate;
		this.qnaWriter = qnaWriter;
		this.qnaAnswerSt = qnaAnswerSt;
	}
	
	

	public QNA(String qnaTitle, String qnaContent, Date createDate) {
		super();
		this.qnaTitle = qnaTitle;
		this.qnaContent = qnaContent;
		this.createDate = createDate;
	}
	
	

	public QNA(int qnaNo, String qnaContent, Date createDate) {
		super();
		this.qnaNo = qnaNo;
		this.qnaContent = qnaContent;
		this.createDate = createDate;
	}
	
	public QNA(int qnaNo, String qnaTitle, String qnaContent, Date createDate) {
	      super();
	      this.qnaNo = qnaNo;
	      this.qnaTitle = qnaTitle;
	      this.qnaContent = qnaContent;
	      this.createDate = createDate;
	   }
	
	

	public int getQnaNo() {
		return qnaNo;
	}

	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}

	public String getQnaTitle() {
		return qnaTitle;
	}

	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}

	public String getQnaContent() {
		return qnaContent;
	}

	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
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

	public int getProductNo() {
		return productNo;
	}

	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}

	public String getQnaWriter() {
		return qnaWriter;
	}

	public void setQnaWriter(String qnaWriter) {
		this.qnaWriter = qnaWriter;
	}

	public String getQnaAnswerSt() {
		return qnaAnswerSt;
	}

	public void setQnaAnswerSt(String qnaAnswerSt) {
		this.qnaAnswerSt = qnaAnswerSt;
	}

	@Override
	public String toString() {
		return "QNA [qnaNo=" + qnaNo + ", qnaTitle=" + qnaTitle + ", qnaContent=" + qnaContent + ", status=" + status
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", productNo=" + productNo
				+ ", qnaWriter=" + qnaWriter + ", qnaAnswerSt=" + qnaAnswerSt + "]";
	}
	
	
}
	
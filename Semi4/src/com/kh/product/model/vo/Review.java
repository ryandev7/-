package com.kh.product.model.vo;

import java.sql.Date;

public class Review {
	
	private int rvNo;
	private String rvTitle;
	private String rvContent;
	private Date createDate;
	private Date modifyDate;
	private String status;
	private String rvWriter;
	private int rvPro;
	
	public Review() {
		super();
	}

	public Review(int rvNo, String rvTitle, String rvContent, Date createDate, Date modifyDate,
			String status, String rvWriter, int rvPro) {
		super();
		this.rvNo = rvNo;
		this.rvTitle = rvTitle;
		this.rvContent = rvContent;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
		this.rvWriter = rvWriter;
		this.rvPro = rvPro;
	}
	
	
	

	public Review(int rvNo, String rvTitle, String rvContent, Date createDate, String rvWriter) {
		super();
		this.rvNo = rvNo;
		this.rvTitle = rvTitle;
		this.rvContent = rvContent;
		this.createDate = createDate;
		this.rvWriter = rvWriter;
	}
	
	public Review(int rvNo, String rvTitle, String rvContent, Date createDate) {
	      super();
	      this.rvNo = rvNo;
	      this.rvTitle = rvTitle;
	      this.rvContent = rvContent;
	      this.createDate = createDate;
	   }
	
	

	public int getRvNo() {
		return rvNo;
	}

	public void setRvNo(int rvNo) {
		this.rvNo = rvNo;
	}

	public String getRvTitle() {
		return rvTitle;
	}

	public void setRvTitle(String rvTitle) {
		this.rvTitle = rvTitle;
	}

	public String getRvContent() {
		return rvContent;
	}

	public void setRvContent(String rvContent) {
		this.rvContent = rvContent;
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

	public String getRvWriter() {
		return rvWriter;
	}

	public void setRvWriter(String rvWriter) {
		this.rvWriter = rvWriter;
	}

	public int getRvPro() {
		return rvPro;
	}

	public void setRvPro(int rvPro) {
		this.rvPro = rvPro;
	}

	@Override
	public String toString() {
		return "Review [rvNo=" + rvNo + ", rvTitle=" + rvTitle + ", rvContent=" + rvContent + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", status=" + status
				+ ", rvWriter=" + rvWriter + ", rvPro=" + rvPro + "]";
	}
	
	

}

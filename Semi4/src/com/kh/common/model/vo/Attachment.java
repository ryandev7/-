package com.kh.common.model.vo;

import java.sql.Date;

public class Attachment {
	private int fileNo;
	private String originName;
	private String changeName;
	private String filePath;
	private Date uploadDate;
	private int fileLevel; ///(1 썸네일/ 2본문)
	private String status;
	private int peNo; // 레시피 번호
	private int rvNo; // 리뷰 번호
	private int productNo; // 식재료 번호
	private int stepNo; // 요리순서 번호
	
	
	
	public Attachment() {
	}
	
	public Attachment(int fileNo, String originName, String changeName, String filePath, Date uploadDate,
			int fileLevel, String status, int peNo, int rvNo, int productNo, int stepNo) {
		this.fileNo = fileNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.status = status;
		this.peNo = peNo;
		this.rvNo = rvNo;
		this.productNo = productNo;
		this.stepNo = stepNo;
	}
	
	

	public Attachment(int fileNo, String originName, String changeName, String filePath, int rvNo) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.rvNo = rvNo;
	}

	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public int getFileLevel() {
		return fileLevel;
	}
	public void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPeNo() {
		return peNo;
	}
	public void setPeNo(int peNo) {
		this.peNo = peNo;
	}
	public int getRvNo() {
		return rvNo;
	}
	public void setRvNo(int rvNo) {
		this.rvNo = rvNo;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public int getStepNo() {
		return stepNo;
	}
	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}
	@Override
	public String toString() {
		return "Attachment [fileNo=" + fileNo + ", originName=" + originName + ", changeName=" + changeName
				+ ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", fileLevel=" + fileLevel + ", status="
				+ status + ", peNo=" + peNo + ", rvNo=" + rvNo + ", productNo=" + productNo + ", stepNo=" + stepNo
				+ "]";
	}	
}

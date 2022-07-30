package com.kh.recipan.model.vo;

import java.sql.Date;

public class Recipan {
	private int peNo;
	private String peTitle;
	private String peIntroduce;
	private String peWriter;
	private String peFoodAmount;
	private String peCookTime;
	private String peCookLevel;
	private int peLikeCount;
	private Date peCreateDate;
	private Date peModifyDate;
	private String prStatus;
	private int boardType;
	private int natCatNo;
	private int kindCatNo;
	private int proCatNo;
	private String titleImg;
	private String nickName;
	private String likeStatus;
	private String natValue;
	public Recipan() {
		super();
	}
	
	
	public Recipan(int peNo, String peTitle, String peIntroduce, String titleImg) {
		super();
		this.peNo = peNo;
		this.peTitle = peTitle;
		this.peIntroduce = peIntroduce;
		this.titleImg = titleImg;
	}
	public Recipan(int boardType, String peTitle, String natValue, Date peCreateDate) {
		super();
		this.boardType = boardType;
		this.peTitle = peTitle;
		this.natValue = natValue;
		this.peCreateDate = peCreateDate;
	}
	public Recipan(int peNo, String peTitle, String peIntroduce, String titleImg, String nickName, String likeStatus) {
		super();
		this.peNo = peNo;
		this.peTitle = peTitle;
		this.peIntroduce = peIntroduce;
		this.titleImg = titleImg;
		this.nickName = nickName;
		this.likeStatus = likeStatus;
	}
	public Recipan(int peNo, String peTitle, String peIntroduce, String peWriter, String peFoodAmount,
			String peCookTime, String peCookLevel, int peLikeCount, Date peCreateDate, Date peModifyDate,
			String prStatus, int boardType, int natCatNo, int kindCatNo, int proCatNo, String titleImg,String likeStatus, String natValue) {
		super();
		this.peNo = peNo;
		this.peTitle = peTitle;
		this.peIntroduce = peIntroduce;
		this.peWriter = peWriter;
		this.peFoodAmount = peFoodAmount;
		this.peCookTime = peCookTime;
		this.peCookLevel = peCookLevel;
		this.peLikeCount = peLikeCount;
		this.peCreateDate = peCreateDate;
		this.peModifyDate = peModifyDate;
		this.prStatus = prStatus;
		this.boardType = boardType;
		this.natCatNo = natCatNo;
		this.kindCatNo = kindCatNo;
		this.proCatNo = proCatNo;
		this.titleImg = titleImg;
		this.likeStatus = likeStatus;
		this.natValue = natValue;
	}


	public Recipan(int peNo, String peTitle, String peIntroduce, String titleImg, String nickName,  String peWriter, int peLikeCount) {
		super();
		this.peNo = peNo;
		this.peTitle = peTitle;
		this.peIntroduce = peIntroduce;
		this.titleImg = titleImg;
		this.nickName = nickName;
		this.likeStatus = likeStatus;
		this.peWriter = peWriter;
		this.peLikeCount = peLikeCount;
	}


	public Recipan(int peNo, String peTitle, String peIntroduce, String peWriter, String peFoodAmount,
			String peCookTime, String peCookLevel, int peLikeCount, Date peCreateDate, Date peModifyDate,
			String prStatus, int boardType, int natCatNo, int kindCatNo, int proCatNo, String titleImg,String likeStatus) {
		super();
		this.peNo = peNo;
		this.peTitle = peTitle;
		this.peIntroduce = peIntroduce;
		this.peWriter = peWriter;
		this.peFoodAmount = peFoodAmount;
		this.peCookTime = peCookTime;
		this.peCookLevel = peCookLevel;
		this.peLikeCount = peLikeCount;
		this.peCreateDate = peCreateDate;
		this.peModifyDate = peModifyDate;
		this.prStatus = prStatus;
		this.boardType = boardType;
		this.natCatNo = natCatNo;
		this.kindCatNo = kindCatNo;
		this.proCatNo = proCatNo;
		this.titleImg = titleImg;
		this.likeStatus = likeStatus;
	}


	
	public String getLikeStatus() {
		return likeStatus;
	}


	public void setLikeStatus(String likeStatus) {
		this.likeStatus = likeStatus;
	}


	public String getNickName() {
		return nickName;
	}


	public void setNickName(String nickName) {
		this.nickName = nickName;
	}


	public String getTitleImg() {
		return titleImg;
	}



	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}



	public int getPeNo() {
		return peNo;
	}



	public void setPeNo(int peNo) {
		this.peNo = peNo;
	}



	public String getPeTitle() {
		return peTitle;
	}



	public void setPeTitle(String peTitle) {
		this.peTitle = peTitle;
	}



	public String getPeIntroduce() {
		return peIntroduce;
	}



	public void setPeIntroduce(String peIntroduce) {
		this.peIntroduce = peIntroduce;
	}



	public String getPeWriter() {
		return peWriter;
	}



	public void setPeWriter(String peWriter) {
		this.peWriter = peWriter;
	}



	public String getPeFoodAmount() {
		return peFoodAmount;
	}



	public void setPeFoodAmount(String peFoodAmount) {
		this.peFoodAmount = peFoodAmount;
	}



	public String getPeCookTime() {
		return peCookTime;
	}



	public void setPeCookTime(String peCookTime) {
		this.peCookTime = peCookTime;
	}



	public String getPeCookLevel() {
		return peCookLevel;
	}



	public void setPeCookLevel(String peCookLevel) {
		this.peCookLevel = peCookLevel;
	}



	public int getPeLikeCount() {
		return peLikeCount;
	}



	public void setPeLikeCount(int peLikeCount) {
		this.peLikeCount = peLikeCount;
	}



	public Date getPeCreateDate() {
		return peCreateDate;
	}



	public void setPeCreateDate(Date peCreateDate) {
		this.peCreateDate = peCreateDate;
	}



	public Date getPeModifyDate() {
		return peModifyDate;
	}



	public void setPeModifyDate(Date peModifyDate) {
		this.peModifyDate = peModifyDate;
	}



	public String getPrStatus() {
		return prStatus;
	}



	public void setPrStatus(String prStatus) {
		this.prStatus = prStatus;
	}



	public int getBoardType() {
		return boardType;
	}



	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}



	public int getNatCatNo() {
		return natCatNo;
	}



	public void setNatCatNo(int natCatNo) {
		this.natCatNo = natCatNo;
	}



	public int getKindCatNo() {
		return kindCatNo;
	}



	public void setKindCatNo(int kindCatNo) {
		this.kindCatNo = kindCatNo;
	}



	public int getProCatNo() {
		return proCatNo;
	}



	public void setProCatNo(int proCatNo) {
		this.proCatNo = proCatNo;
	}
	
	public String getNatValue() {
		return natValue;
	}
	
	public void setNatValue(String natValue) {
		this.natValue = natValue;
	}



	@Override
	public String toString() {
		return "Reciepan [peNo=" + peNo + ", peTitle=" + peTitle + ", peIntroduce=" + peIntroduce + ", peWriter="
				+ peWriter + ", peFoodAmount=" + peFoodAmount + ", peCookTime=" + peCookTime + ", peCookLevel="
				+ peCookLevel + ", peLikeCount=" + peLikeCount + ", peCreateDate=" + peCreateDate + ", peModifyDate="
				+ peModifyDate + ", prStatus=" + prStatus + ", boardType=" + boardType + ", natCatNo=" + natCatNo
				+ ", kindCatNo=" + kindCatNo + ", proCatNo=" + proCatNo + "]";
	}
	
	
}

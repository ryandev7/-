package com.kh.product.model.vo;

public class Product {
	
	private int prNo; // PR_NO	NUMBER
	private String prName; // PR_NAME	VARCHAR2(50 BYTE)
	private String prIntroduce; // PR_INTRODUCE	VARCHAR2(300 BYTE)
	private String prWeight; // PR_WEIGHT	VARCHAR2(50 BYTE)
	private int prPrice; // PR_PRICE	NUMBER
	private String prOrigin; // PR_ORIGIN	VARCHAR2(100 BYTE)
	private String prContent; // PR_CONTENT	VARCHAR2(4000 BYTE)
	private String status; // STATUS	VARCHAR2(2 BYTE)
	private int prLikeCount; // PR_LIKECOUNT	NUMBER
	private int proCatNo; // PRO_CAT_NO	NUMBER
	
	private String titleImg;
	private String prCateName;

	public Product() {
		super();
	}
	
	public Product(int prNo, String prName, String titleImg) {
		super();
		this.prNo = prNo;
		this.prName = prName;
		this.titleImg = titleImg;
	}
	

	public Product(int prNo, String prName, String prIntroduce, String prCateName, String prWeight, String prOrigin,
			int prPrice, String prContent, int proCatNo) {
		super();
		this.prNo = prNo;
		this.prName = prName;
		this.prIntroduce = prIntroduce;
		this.prCateName = prCateName;
		this.prWeight = prWeight;
		this.prOrigin = prOrigin;
		this.prPrice = prPrice;
		this.prContent = prContent;
		this.proCatNo = proCatNo;
	}



	public Product(int prNo, String prName, String prIntroduce, String prCateName, String prWeight, String prOrigin, int prPrice,
			String prContent) {
		super();
		this.prNo = prNo;
		this.prName = prName;
		this.prIntroduce = prIntroduce;
		this.prCateName = prCateName;
		this.prWeight = prWeight;
		this.prOrigin = prOrigin;
		this.prPrice = prPrice;
		this.prContent = prContent;
	}
	
	public Product(int prNo, String prName, String prIntroduce, String prWeight, int prPrice, String prOrigin,
			String prContent, int prLikeCount, int proCatNo) {
		super();
		this.prNo = prNo;
		this.prName = prName;
		this.prIntroduce = prIntroduce;
		this.prWeight = prWeight;
		this.prPrice = prPrice;
		this.prOrigin = prOrigin;
		this.prContent = prContent;
		this.prLikeCount = prLikeCount;
		this.proCatNo = proCatNo;
	}



	public Product(int prNo, String prName, String prIntroduce, String prWeight, int prPrice, String prOrigin,
			String prContent, String status, int prLikeCount, int proCatNo) {
		super();
		this.prNo = prNo;
		this.prName = prName;
		this.prIntroduce = prIntroduce;
		this.prWeight = prWeight;
		this.prPrice = prPrice;
		this.prOrigin = prOrigin;
		this.prContent = prContent;
		this.status = status;
		this.prLikeCount = prLikeCount;
		this.proCatNo = proCatNo;
	}

	public int getPrNo() {
		return prNo;
	}

	public void setPrNo(int prNo) {
		this.prNo = prNo;
	}

	public String getPrName() {
		return prName;
	}

	public void setPrName(String prName) {
		this.prName = prName;
	}

	public String getPrIntroduce() {
		return prIntroduce;
	}

	public void setPrIntroduce(String prIntroduce) {
		this.prIntroduce = prIntroduce;
	}

	public String getPrWeight() {
		return prWeight;
	}

	public void setPrWeight(String prWeight) {
		this.prWeight = prWeight;
	}

	public int getPrPrice() {
		return prPrice;
	}

	public void setPrPrice(int prPrice) {
		this.prPrice = prPrice;
	}

	public String getPrOrigin() {
		return prOrigin;
	}

	public void setPrOrigin(String prOrigin) {
		this.prOrigin = prOrigin;
	}

	public String getPrContent() {
		return prContent;
	}

	public void setPrContent(String prContent) {
		this.prContent = prContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPrLikeCount() {
		return prLikeCount;
	}

	public void setPrLikeCount(int prLikeCount) {
		this.prLikeCount = prLikeCount;
	}

	public int getProCatNo() {
		return proCatNo;
	}

	public void setProCatNo(int proCatNo) {
		this.proCatNo = proCatNo;
	}

	public String getTitleImg() {
		return titleImg;
	}
	
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
	
	public String getPrCateName() {
		return prCateName;
	}

	public void setPrCateName(String prCateName) {
		this.prCateName = prCateName;
	}

	@Override
	public String toString() {
		return "Product [prNo=" + prNo + ", prName=" + prName + ", prIntroduce=" + prIntroduce + ", prWeight="
				+ prWeight + ", prPrice=" + prPrice + ", prOrigin=" + prOrigin + ", prContent=" + prContent
				+ ", status=" + status + ", prLikeCount=" + prLikeCount + ", proCatNo=" + proCatNo + "]";
	}
	
}

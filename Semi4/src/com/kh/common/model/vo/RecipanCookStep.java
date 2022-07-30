package com.kh.common.model.vo;

public class RecipanCookStep {
	private int stepNo; //주식별자 (의미없음)
	private int peNo; // 레시피/레시판번호
 	//요리순서는 내용의 length 로 대체 
	private String[] stepContent; // 요리순서 내용
	private String outPutStepContent;
	private String stepImg;
	
	public RecipanCookStep() {
		super();
	}
	public RecipanCookStep(int stepNo, String outPutStepContent) {
		super();
		this.stepNo = stepNo;
		this.outPutStepContent = outPutStepContent;
	}
	
	public RecipanCookStep(int stepNo, int peNo, String[] stepContent) {
		super();
		this.stepNo = stepNo;
		this.peNo = peNo;
		this.stepContent = stepContent;
	}

	

	public String getStepImg() {
		return stepImg;
	}
	public void setStepImg(String stepImg) {
		this.stepImg = stepImg;
	}
	public String getOutPutStepContent() {
		return outPutStepContent;
	}

	public void setOutPutStepContent(String outPutStepContent) {
		this.outPutStepContent = outPutStepContent;
	}

	public int getStepNo() {
		return stepNo;
	}
	public void setStepNo(int stepNo) {
		this.stepNo = stepNo;
	}
	public int getPeNo() {
		return peNo;
	}
	public void setPeNo(int peNo) {
		this.peNo = peNo;
	}

	public String[] getStepContent() {
		return stepContent;
	}

	public void setStepContent(String[] stepContent) {
		this.stepContent = stepContent;
	}
	
	
}

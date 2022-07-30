package com.kh.common.model.vo;

public class RecipanProSau {
	private int peNo;
	private String[] name;
	private String[] amount;
	private String outputName;
	private String outputAmount;
	
	public RecipanProSau() {
		super();
	}
	public RecipanProSau(String outputName, String outputAmount) {
		super();
		this.outputName = outputName;
		this.outputAmount = outputAmount;
	}
	public int getPeNo() {
		return peNo;
	}
	public void setPeNo(int peNo) {
		this.peNo = peNo;
	}
	
	public String[] getName() {
		return name;
	}
	public void setName(String[] name) {
		this.name = name;
	}
	public String[] getAmount() {
		return amount;
	}
	public void setAmount(String[] amount) {
		this.amount = amount;
	}
	
	public String getOutputName() {
		return outputName;
	}
	public void setOutputName(String outputName) {
		this.outputName = outputName;
	}
	public String getOutputAmount() {
		return outputAmount;
	}
	public void setOutputAmount(String outputAmount) {
		this.outputAmount = outputAmount;
	}
	@Override
	public String toString() {
		return "RecipeProSau [peNo=" + peNo + ", name=" + name + ", amount=" + amount + "]";
	}
	
}

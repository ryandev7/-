package com.kh.cart.model.vo;

public class Cart {
	
	private int cartNo; //CART_NO	NUMBER
	private int cartAmount; //CART_AMOUNT	NUMBER
	private String cartStatus; //CART_STATUS	VARCHAR2(1 BYTE)
	private String userId; //USER_ID	VARCHAR2(30 BYTE)
	private int proNo; //PRO_NO	NUMBER
	private String prName; // 리스트에 뿌려주기위해 상품명 변수 선언
	private int prPrice; // 리스트에 뿌려주기위해 상품가격 변수 선언
	
	public Cart() {
		super();
	}
	
	public Cart(int cartNo, int cartAmount, String cartStatus, String userId, int proNo, String prName, int prPrice) {
		super();
		this.cartNo = cartNo;
		this.cartAmount = cartAmount;
		this.cartStatus = cartStatus;
		this.userId = userId;
		this.proNo = proNo;
		this.prName = prName;
		this.prPrice = prPrice;
	}
	
	public int getCartNo() {
		return cartNo;
	}
	public void setCartNo(int cartNo) {
		this.cartNo = cartNo;
	}
	public int getCartAmount() {
		return cartAmount;
	}
	public void setCartAmount(int cartAmount) {
		this.cartAmount = cartAmount;
	}
	public String getCartStatus() {
		return cartStatus;
	}
	public void setCartStatus(String cartStatus) {
		this.cartStatus = cartStatus;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProNo() {
		return proNo;
	}
	public void setProNo(int proNo) {
		this.proNo = proNo;
	}
	public String getPrName() {
		return prName;
	}
	public void setPrName(String prName) {
		this.prName = prName;
	}
	public int getPrPrice() {
		return prPrice;
	}
	public void setPrPrice(int prPrice) {
		this.prPrice = prPrice;
	}
	
	@Override
	public String toString() {
		return "Cart [cartNo=" + cartNo + ", cartAmount=" + cartAmount + ", cartStatus=" + cartStatus + ", userId="
				+ userId + ", proNo=" + proNo + ", prName=" + prName + ", prPrice=" + prPrice + "]";
	}
	
	

}

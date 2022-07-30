package com.kh.member.model.vo;

import java.sql.Date;

public class Member {              
	private String userId;//USER_ID VARCHAR2(30) NOT NULL UNIQUE,   
	private String userPwd;//USER_PWD VARCHAR2(100) NOT NULL,  
	private String userName;//USER_NAME VARCHAR2(15) NOT NULL,  
	private String email;//EMAIL VARCHAR2(100),            
	private String address;//ADDRESS VARCHAR2(100),
	private String phone;//PHONE VARCHAR2(13),   
	private String nickName;
	private String cookLevel;
	private Date createDate;//ENROLL_DATE DATE DEFAULT SYSDATE,
	private Date modifyDate;//MODIFY_DATE DATE DEFAULT SYSDATE,
	private String status; //STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N'))
	private String favoriteFood;
	
	public Member() {
		super();
	}

	
	
	public Member(String userId, String userName, String phone) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.phone = phone;
	}
	
	public Member(String userName, String phone) {
		super();
		this.userName = userName;
		this.phone = phone;
	}



	public Member(String userId, String userPwd, String userName,  String email, String address, String phone,  String nickName,
			String cookLevel, Date createDate, Date modifyDate, String status, String favoriteFood) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.nickName = nickName;
		this.cookLevel = cookLevel;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.status = status;
		this.favoriteFood = favoriteFood;
	}
	
	

	public Member(String userId, String userPwd, String userName,  String email, String address, String phone,
			String nickName, String cookLevel, String favoriteFood) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.nickName = nickName;
		this.cookLevel = cookLevel;
		this.favoriteFood = favoriteFood;
	}
	
	public Member(String userId, String userName,  String email, String address, String phone,
			String nickName, String cookLevel, String favoriteFood) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.nickName = nickName;
		this.cookLevel = cookLevel;
		this.favoriteFood = favoriteFood;
	}
	
	

	public Member(String userId, String userPwd, String userName, String email, String address, String phone,
			String nickName, Date createDate , String status) {
		super();
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.nickName = nickName;
		this.createDate = createDate;
		this.status = status;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCookLevel() {
		return cookLevel;
	}
	
	
	public void setCookLevel(String cookLevel) {
		this.cookLevel = cookLevel;
	}
	

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getFavoriteFood() {
		return favoriteFood;
	}

	public void setFavoriteFood(String favoriteFood) {
		this.favoriteFood = favoriteFood;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ",  email=" + email + ", address=" + address + ",phone=" + phone
				+ ", nickName=" + nickName + ", cookLevel=" + cookLevel
				+ ", createDate=" + createDate + ", modifyDate=" + modifyDate + ", status=" + status + ", favoriteFood="
				+ favoriteFood + "]";
	}


	
	
	
}

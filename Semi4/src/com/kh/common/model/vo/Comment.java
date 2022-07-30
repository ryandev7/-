package com.kh.common.model.vo;

import java.sql.Date;

public class Comment {
	private int commentNo;
	private String commentContent;
	private String commentWriter;
	private int refPeNo;
	private Date createDate;
	public Comment() {
		super();
	}
	
	public Comment(String commentContent, String commentWriter, Date createDate) {
		super();
		this.commentContent = commentContent;
		this.commentWriter = commentWriter;
		this.createDate = createDate;
	}

	public Comment(int commentNo, String commentContent, String commentWriter, int refPeNo) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.commentWriter = commentWriter;
		this.refPeNo = refPeNo;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentWriter() {
		return commentWriter;
	}
	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}
	public int getRefPeNo() {
		return refPeNo;
	}
	public void setRefPeNo(int refPeNo) {
		this.refPeNo = refPeNo;
	}
	
	
}

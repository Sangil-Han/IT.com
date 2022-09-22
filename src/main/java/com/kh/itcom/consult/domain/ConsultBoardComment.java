package com.kh.itcom.consult.domain;

import java.sql.Date;

public class ConsultBoardComment {
	private int commentNo;
	private int cBoardNo;
	private String userId;
	private String commentContents;
	private String commentRegtime;
	private int commentUp;
	private int commentDown;
	
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public int getcBoardNo() {
		return cBoardNo;
	}
	public void setcBoardNo(int cBoardNo) {
		this.cBoardNo = cBoardNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCommentContents() {
		return commentContents;
	}
	public void setCommentContents(String commentContents) {
		this.commentContents = commentContents;
	}
	public String getCommentRegtime() {
		return commentRegtime;
	}
	public void setCommentRegtime(String commentRegtime) {
		this.commentRegtime = commentRegtime;
	}
	public int getCommentUp() {
		return commentUp;
	}
	public void setCommentUp(int commentUp) {
		this.commentUp = commentUp;
	}
	public int getCommentDown() {
		return commentDown;
	}
	public void setCommentDown(int commentDown) {
		this.commentDown = commentDown;
	}
	@Override
	public String toString() {
		return "ConsultBoardComment [commentNo=" + commentNo + ", cBoardNo=" + cBoardNo + ", userId=" + userId
				+ ", commentContents=" + commentContents + ", commentRegtime=" + commentRegtime + ", commentUp="
				+ commentUp + ", commentDown=" + commentDown + "]";
	}
	
	
	
}

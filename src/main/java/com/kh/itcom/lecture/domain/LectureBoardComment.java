package com.kh.itcom.lecture.domain;

public class LectureBoardComment {
	private int lCommentNo;
	private int lBoardNo;
	private String userId;
	private String lCommentContents;
	private String lCommentRegtime;
	
	public int getlCommentNo() {
		return lCommentNo;
	}
	public void setlCommentNo(int lCommentNo) {
		this.lCommentNo = lCommentNo;
	}
	public int getlBoardNo() {
		return lBoardNo;
	}
	public void setlBoardNo(int lBoardNo) {
		this.lBoardNo = lBoardNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getlCommentContents() {
		return lCommentContents;
	}
	public void setlCommentContents(String lCommentContents) {
		this.lCommentContents = lCommentContents;
	}
	public String getlCommentRegtime() {
		return lCommentRegtime;
	}
	public void setlCommentRegtime(String lCommentRegtime) {
		this.lCommentRegtime = lCommentRegtime;
	}
	
	@Override
	public String toString() {
		return "LectureBoardComment [lCommentNo=" + lCommentNo + ", lBoardNo=" + lBoardNo + ", userId=" + userId
				+ ", lCommentContents=" + lCommentContents + ", lCommentRegtime=" + lCommentRegtime + "]";
	}
}

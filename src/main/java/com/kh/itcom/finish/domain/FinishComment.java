package com.kh.itcom.finish.domain;

public class FinishComment {
	int fCommentNo;
	int fBoardNo;
	String userId;
	String fCommentContents;
	String fCommentRegtime;
	public int getfCommentNo() {
		return fCommentNo;
	}
	public void setfCommentNo(int fCommentNo) {
		this.fCommentNo = fCommentNo;
	}
	public int getfBoardNo() {
		return fBoardNo;
	}
	public void setfBoardNo(int fBoardNo) {
		this.fBoardNo = fBoardNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getfCommentContents() {
		return fCommentContents;
	}
	public void setfCommentContents(String fCommentContents) {
		this.fCommentContents = fCommentContents;
	}
	public String getfCommentRegtime() {
		return fCommentRegtime;
	}
	public void setfCommentRegtime(String fCommentRegtime) {
		this.fCommentRegtime = fCommentRegtime;
	}
	@Override
	public String toString() {
		return "FinishComment [fCommentNo=" + fCommentNo + ", fBoardNo=" + fBoardNo + ", userId=" + userId
				+ ", fCommentContents=" + fCommentContents + ", fCommentRegtime=" + fCommentRegtime + "]";
	}
	
	
}

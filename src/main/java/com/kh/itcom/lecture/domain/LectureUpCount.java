package com.kh.itcom.lecture.domain;

public class LectureUpCount {
	private String userId;
	private int lBoardNo;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getlBoardNo() {
		return lBoardNo;
	}
	public void setlBoardNo(int lBoardNo) {
		this.lBoardNo = lBoardNo;
	}
	
	@Override
	public String toString() {
		return "LectureUpCount [userId=" + userId + ", lBoardNo=" + lBoardNo + "]";
	}
	
}

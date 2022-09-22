package com.kh.itcom.consult.domain;

public class ConsultUpCount {
	private String userId;
	private int cBoardNo;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getcBoardNo() {
		return cBoardNo;
	}
	public void setcBoardNo(int cBoardNo) {
		this.cBoardNo = cBoardNo;
	}
	@Override
	public String toString() {
		return "ConsultUpCount [userId=" + userId + ", cBoardNo=" + cBoardNo + "]";
	}
	
}

package com.kh.itcom.user.domain;

import java.sql.Date;

public class PointHistory {
	private int pointHistoryNo;
	private Date pointDate;
	private String pointName;
	private int pointAmount;

	public int getPointHistoryNo() {
		return pointHistoryNo;
	}

	public void setPointHistoryNo(int pointHistoryNo) {
		this.pointHistoryNo = pointHistoryNo;
	}

	public Date getPointDate() {
		return pointDate;
	}

	public void setPointDate(Date pointDate) {
		this.pointDate = pointDate;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public int getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(int pointAmount) {
		this.pointAmount = pointAmount;
	}

	@Override
	public String toString() {
		return "PointHistory [pointHistoryNo=" + pointHistoryNo + ", pointDate=" + pointDate + ", pointName=" + pointName
				+ ", pointAmount=" + pointAmount + "]";
	}

}

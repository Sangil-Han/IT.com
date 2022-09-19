package com.kh.itcom.lecture.domain;

import java.sql.Date;

public class LectureBoard {
	private int lBoardNo;
	private String userId;
	private String lBoardTitle;
	private String lBoardLocalName;
	private String lBoardCenterName;
	private String lBoardCourseName;
	private String lBoardContents;
	private String lBoardFileName;
	private String lBoardFileRename;
	private String lBoardFilePath;
	private Date lBoardCreateDate;
	private int lBoardCount;
	private int lBoardUpCount;
	private int lBoardDownCount;
	
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
	public String getlBoardTitle() {
		return lBoardTitle;
	}
	public void setlBoardTitle(String lBoardTitle) {
		this.lBoardTitle = lBoardTitle;
	}
	public String getlBoardLocalName() {
		return lBoardLocalName;
	}
	public void setlBoardLocalName(String lBoardLocalName) {
		this.lBoardLocalName = lBoardLocalName;
	}
	public String getlBoardCenterName() {
		return lBoardCenterName;
	}
	public void setlBoardCenterName(String lBoardCenterName) {
		this.lBoardCenterName = lBoardCenterName;
	}
	public String getlBoardCourseName() {
		return lBoardCourseName;
	}
	public void setlBoardCourseName(String lBoardCourseName) {
		this.lBoardCourseName = lBoardCourseName;
	}
	public String getlBoardContents() {
		return lBoardContents;
	}
	public void setlBoardContents(String lBoardContents) {
		this.lBoardContents = lBoardContents;
	}
	public String getlBoardFileName() {
		return lBoardFileName;
	}
	public void setlBoardFileName(String lBoardFileName) {
		this.lBoardFileName = lBoardFileName;
	}
	public String getlBoardFileRename() {
		return lBoardFileRename;
	}
	public void setlBoardFileRename(String lBoardFileRename) {
		this.lBoardFileRename = lBoardFileRename;
	}
	public String getlBoardFilePath() {
		return lBoardFilePath;
	}
	public void setlBoardFilePath(String lBoardFilePath) {
		this.lBoardFilePath = lBoardFilePath;
	}
	public Date getlBoardCreateDate() {
		return lBoardCreateDate;
	}
	public void setlBoardCreateDate(Date lBoardCreateDate) {
		this.lBoardCreateDate = lBoardCreateDate;
	}
	public int getlBoardCount() {
		return lBoardCount;
	}
	public void setlBoardCount(int lBoardCount) {
		this.lBoardCount = lBoardCount;
	}
	public int getlBoardUpCount() {
		return lBoardUpCount;
	}
	public void setlBoardUpCount(int lBoardUpCount) {
		this.lBoardUpCount = lBoardUpCount;
	}
	public int getlBoardDownCount() {
		return lBoardDownCount;
	}
	public void setlBoardDownCount(int lBoardDownCount) {
		this.lBoardDownCount = lBoardDownCount;
	}
	
	@Override
	public String toString() {
		return "LectureBoard [lBoardNo=" + lBoardNo + ", userId=" + userId + ", lBoardTitle=" + lBoardTitle
				+ ", lBoardLocalName=" + lBoardLocalName + ", lBoardCenterName=" + lBoardCenterName
				+ ", lBoardCourseName=" + lBoardCourseName + ", lBoardContents=" + lBoardContents + ", lBoardFileName="
				+ lBoardFileName + ", lBoardFileRename=" + lBoardFileRename + ", lBoardFilePath=" + lBoardFilePath
				+ ", lBoardCreateDate=" + lBoardCreateDate + ", lBoardCount=" + lBoardCount + ", lBoardUpCount="
				+ lBoardUpCount + ", lBoardDownCount=" + lBoardDownCount + "]";
	}
}

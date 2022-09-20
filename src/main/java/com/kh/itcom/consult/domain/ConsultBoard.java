package com.kh.itcom.consult.domain;

import java.sql.Date;

public class ConsultBoard {
	private int cBoardNo;
	private String userId;
	private String cBoardTitle;
	private String cBoardLocalName;
	private String cBoardCenterName;
	private String cBoardCourseName;
	private Date consultDate;
	private String cBoardContents;
	private String cBoardFileName;
	private String cBoardFileRename;
	private String cBoardFilePath;
	private Date cBoardCreateDate;
	private int cBoardCount;
	private int cBoardUpCount;
	private int cBoardDownCount;
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
	public String getcBoardTitle() {
		return cBoardTitle;
	}
	public void setcBoardTitle(String cBoardTitle) {
		this.cBoardTitle = cBoardTitle;
	}
	public String getcBoardLocalName() {
		return cBoardLocalName;
	}
	public void setcBoardLocalName(String cBoardLocalName) {
		this.cBoardLocalName = cBoardLocalName;
	}
	public String getcBoardCenterName() {
		return cBoardCenterName;
	}
	public void setcBoardCenterName(String cBoardCenterName) {
		this.cBoardCenterName = cBoardCenterName;
	}
	public String getcBoardCourseName() {
		return cBoardCourseName;
	}
	public void setcBoardCourseName(String cBoardCourseName) {
		this.cBoardCourseName = cBoardCourseName;
	}
	public Date getConsultDate() {
		return consultDate;
	}
	public void setConsultDate(Date consultDate) {
		this.consultDate = consultDate;
	}
	public String getcBoardContents() {
		return cBoardContents;
	}
	public void setcBoardContents(String cBoardContents) {
		this.cBoardContents = cBoardContents;
	}
	public String getcBoardFileName() {
		return cBoardFileName;
	}
	public void setcBoardFileName(String cBoardFileName) {
		this.cBoardFileName = cBoardFileName;
	}
	public String getcBoardFileRename() {
		return cBoardFileRename;
	}
	public void setcBoardFileRename(String cBoardFileRename) {
		this.cBoardFileRename = cBoardFileRename;
	}
	public String getcBoardFilePath() {
		return cBoardFilePath;
	}
	public void setcBoardFilePath(String cBoardFilePath) {
		this.cBoardFilePath = cBoardFilePath;
	}
	public Date getcBoardCreateDate() {
		return cBoardCreateDate;
	}
	public void setcBoardCreateDate(Date cBoardCreateDate) {
		this.cBoardCreateDate = cBoardCreateDate;
	}
	public int getcBoardCount() {
		return cBoardCount;
	}
	public void setcBoardCount(int cBoardCount) {
		this.cBoardCount = cBoardCount;
	}
	public int getcBoardUpCount() {
		return cBoardUpCount;
	}
	public void setcBoardUpCount(int cBoardUpCount) {
		this.cBoardUpCount = cBoardUpCount;
	}
	public int getcBoardDownCount() {
		return cBoardDownCount;
	}
	public void setcBoardDownCount(int cBoardDownCount) {
		this.cBoardDownCount = cBoardDownCount;
	}
	@Override
	public String toString() {
		return "ConsultBoard [cBoardNo=" + cBoardNo + ", userId=" + userId + ", cBoardTitle=" + cBoardTitle
				+ ", cBoardLocalName=" + cBoardLocalName + ", cBoardCenterName=" + cBoardCenterName
				+ ", cBoardCourseName=" + cBoardCourseName + ", consultDate=" + consultDate + ", cBoardContents="
				+ cBoardContents + ", cBoardFileName=" + cBoardFileName + ", cBoardFileRename=" + cBoardFileRename
				+ ", cBoardFilePath=" + cBoardFilePath + ", cBoardCreateDate=" + cBoardCreateDate + ", cBoardCount="
				+ cBoardCount + ", cBoardUpCount=" + cBoardUpCount + ", cBoardDownCount=" + cBoardDownCount + "]";
	}
	
}

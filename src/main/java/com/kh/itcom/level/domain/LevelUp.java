package com.kh.itcom.level.domain;

import java.sql.Date;

public class LevelUp {

	private int applicationNo;
	private String userId;
	private String applicationLv;
	private String certFileName;
	private String certFileRename;
	private String certFilePath;
	private String applicationStatus;
	private Date applicationDate;
	private Date lvChangeDate;

	public int getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(int applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getApplicationLv() {
		return applicationLv;
	}

	public void setApplicationLv(String applicationLv) {
		this.applicationLv = applicationLv;
	}

	public String getCertFileName() {
		return certFileName;
	}

	public void setCertFileName(String certFileName) {
		this.certFileName = certFileName;
	}

	public String getCertFileRename() {
		return certFileRename;
	}

	public void setCertFileRename(String certFileRename) {
		this.certFileRename = certFileRename;
	}

	public String getCertFilePath() {
		return certFilePath;
	}

	public void setCertFilePath(String certFilePath) {
		this.certFilePath = certFilePath;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Date getLvChangeDate() {
		return lvChangeDate;
	}

	public void setLvChangeDate(Date lvChangeDate) {
		this.lvChangeDate = lvChangeDate;
	}

	@Override
	public String toString() {
		return "LevelUp [applicationNo=" + applicationNo + ", userId=" + userId + ", applicationLv=" + applicationLv
				+ ", certFileName=" + certFileName + ", certFileRename=" + certFileRename + ", certFilePath="
				+ certFilePath + ", applicationStatus=" + applicationStatus + ", applicationDate=" + applicationDate
				+ ", lvChangeDate=" + lvChangeDate + "]";
	}

}

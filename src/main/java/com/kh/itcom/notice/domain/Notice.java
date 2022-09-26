package com.kh.itcom.notice.domain;

import java.sql.Date;

public class Notice {
	private int noticeNo;
	private String adminId;
	private String noticeTitle;
	private String noticeContents;
	private Date createDate;
	private String noticeFileName;
	private String noticeFileRename;
	private String noticeFilePath;

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public String getNoticeContents() {
		return noticeContents;
	}

	public void setNoticeContents(String noticeContents) {
		this.noticeContents = noticeContents;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getNoticeFileName() {
		return noticeFileName;
	}

	public void setNoticeFileName(String noticeFileName) {
		this.noticeFileName = noticeFileName;
	}

	public String getNoticeFileRename() {
		return noticeFileRename;
	}

	public void setNoticeFileRename(String noticeFileRename) {
		this.noticeFileRename = noticeFileRename;
	}

	public String getNoticeFilePath() {
		return noticeFilePath;
	}

	public void setNoticeFilePath(String noticeFilePath) {
		this.noticeFilePath = noticeFilePath;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", adminId=" + adminId + ", noticeTitle=" + noticeTitle
				+ ", noticeContents=" + noticeContents + ", createDate=" + createDate + ", noticeFileName="
				+ noticeFileName + ", noticeFileRename=" + noticeFileRename + ", noticeFilePath=" + noticeFilePath
				+ "]";
	}

}

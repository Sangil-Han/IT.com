package com.kh.itcom.user.domain;

import java.sql.Date;

public class MyPost {
	private String boardName;
	private String postTitle;
	private Date createDate;

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "MyPost [boardName=" + boardName + ", postTitle=" + postTitle + ", createDate=" + createDate + "]";
	}

}

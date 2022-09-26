package com.kh.itcom.common.domain;

public class PageInfo {
	private int currentPage; // 현재 페이징 넘버
	private int rowCount; // 총 데이터 개수
	private int rowLimit; // 페이지당 데이터 개수
	private int pageLimit; // 페이징 넘버 개수
	private int pageCount; // 총 페이징 넘버 개수
	private int startPage;
	private int endPage;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getRowLimit() {
		return rowLimit;
	}

	public void setRowLimit(int rowLimit) {
		this.rowLimit = rowLimit;
	}

	public int getPageLimit() {
		return pageLimit;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageInfo [currentPage=" + currentPage + ", rowCount=" + rowCount + ", rowLimit=" + rowLimit
				+ ", pageLimit=" + pageLimit + ", pageCount=" + pageCount + ", startPage=" + startPage + ", endPage="
				+ endPage + "]";
	}

}

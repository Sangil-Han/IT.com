package com.kh.itcom.user.domain;

import java.util.HashMap;
import java.util.List;

public class Checked {

	private List<String> checkedUsers;
	private List<String> applicationLv;

	public List<String> getCheckedUsers() {
		return checkedUsers;
	}

	public void setCheckedUsers(List<String> checkedUsers) {
		this.checkedUsers = checkedUsers;
	}

	public List<String> getApplicationLv() {
		return applicationLv;
	}

	public void setApplicationLv(List<String> applicationLv) {
		this.applicationLv = applicationLv;
	}

	@Override
	public String toString() {
		return "Checked [checkedUsers=" + checkedUsers + ", applicationLv=" + applicationLv + "]";
	}

}

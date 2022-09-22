package com.kh.itcom.admin.service;

import java.util.HashMap;
import java.util.List;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.user.domain.User;

public interface AdminService {

	// 아이디 체크
	int checkId(String id);

	// 관리자 로그인
	Admin loginAdmin(Admin admin);

	// 회원 목록
	List<User> printUserList(HashMap<String, Integer> pageInfo);

	// 총 회원 수
	int printTotalUserCount();
}

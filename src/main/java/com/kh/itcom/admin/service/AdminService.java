package com.kh.itcom.admin.service;

import java.util.List;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.user.domain.LevelUp;
import com.kh.itcom.user.domain.User;

public interface AdminService {

	int approveLevelUp(List<String> checkedUsers);

	int denyLevelUp(List<String> checkedUsers);

	// 회원 삭제
	int removeUsers(List<String> checkedUsers);

	// 아이디 체크
	int checkAdminId(String id);

	// 관리자 로그인
	Admin loginAdmin(Admin admin);

	// 총 회원 수
	int printTotalUserCount();

	// 회원 목록
	List<User> printUserList(PageInfo upi);

	int printTotalLevelUpCount();

	// 등업 신청 목록
	List<LevelUp> printLevelUpList(PageInfo lupi);
}

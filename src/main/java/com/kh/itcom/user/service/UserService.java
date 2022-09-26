package com.kh.itcom.user.service;

import java.util.HashMap;
import java.util.List;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.user.domain.LevelUp;
import com.kh.itcom.user.domain.MyPost;
import com.kh.itcom.user.domain.PointHistory;
import com.kh.itcom.user.domain.User;

public interface UserService {

	// 회원가입
	int joinUser(User user);

	// 회원 탈퇴
	int removeUser(HashMap<String, String> userInfo);

	// 회원 로그인
	User loginUser(User user);

	// 총 포인트 내역 개수
	int printTotalPointCount(String userId);

	// 포인트 내역
	List<PointHistory> printPointHistory(String userId, PageInfo phpi);

	// 등업 신청
	int applyLevelUp(LevelUp lvUp);

	// 회원 아이디 중복 확인
	int checkUserId(String userId);

	// 현재 비밀번호 확인 
	int checkPw(User user);

	// 비밀번호 재설정
	int resetPw(User user);

	// 등급 변동 내역
	List<LevelUp> printLevelHistory(String userId);

	// 작성글 조회
	List<MyPost> printMyPostList(String userId, PageInfo mppi);

	// 작성글 개수
	int printTotalMyPostCount(String userId);

}

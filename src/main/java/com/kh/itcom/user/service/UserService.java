package com.kh.itcom.user.service;

import java.util.HashMap;
import java.util.List;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.point.domain.PointHistory;
import com.kh.itcom.user.domain.User;

public interface UserService {

	// 회원가입
	int joinUser(User user);

	// 회원 탈퇴
	int removeUser(HashMap<String, String> userInfo);

	// 회원 로그인
	User loginUser(User user);

}

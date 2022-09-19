package com.kh.itcom.user.service;

import java.util.HashMap;

import com.kh.itcom.user.domain.User;

public interface UserService {

	// 회원가입
	int joinUser(User user);

	// 회원 탈퇴
	int removeUser(HashMap<String, String> userInfo);

	// 로그인
	User loginUser(User user);

}

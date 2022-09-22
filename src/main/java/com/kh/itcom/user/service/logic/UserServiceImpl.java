package com.kh.itcom.user.service.logic;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.point.domain.PointHistory;
import com.kh.itcom.user.domain.User;
import com.kh.itcom.user.service.UserService;
import com.kh.itcom.user.store.UserStore;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private UserStore uStore;

	// 회원가입
	@Override
	public int joinUser(User user) {
		int result = uStore.insertUser(session, user);
		return result;
	}
	
	// 회원 탈퇴
	@Override
	public int removeUser(HashMap<String, String> userInfo) {
		int result = uStore.deleteUser(session, userInfo);
		return result;
	}

	// 로그인
	@Override
	public User loginUser(User user) {
		User loginUser = uStore.selectLoginUser(session, user);
		return loginUser;
	}

}

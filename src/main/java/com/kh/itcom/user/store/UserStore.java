package com.kh.itcom.user.store;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.user.domain.User;

public interface UserStore {

	// 회원가입
	int insertUser(SqlSession session, User user);

	// 회원 탈퇴
	int deleteUser(SqlSession session, HashMap<String, String> userInfo);

	// 로그인
	User selectLoginUser(SqlSession session, User user);

}

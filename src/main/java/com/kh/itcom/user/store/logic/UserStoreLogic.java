package com.kh.itcom.user.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.point.domain.PointHistory;
import com.kh.itcom.user.domain.User;
import com.kh.itcom.user.store.UserStore;

@Repository
public class UserStoreLogic implements UserStore {

	@Override
	public int insertUser(SqlSession session, User user) {
		int result = session.insert("UserMapper.insertUser", user);
		return result;
	}

	@Override
	public int deleteUser(SqlSession session, HashMap<String, String> userInfo) {
		int result = session.update("UserMapper.deleteUser", userInfo);
		return result;
	}

	@Override
	public User selectLoginUser(SqlSession session, User user) {
		User loginUser = session.selectOne("UserMapper.selectLoginUser", user);
		return loginUser;
	}

}

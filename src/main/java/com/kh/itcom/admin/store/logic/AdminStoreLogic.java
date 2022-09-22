package com.kh.itcom.admin.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.admin.store.AdminStore;
import com.kh.itcom.user.domain.User;

@Repository
public class AdminStoreLogic implements AdminStore {

	// 관리자 아이디 판별
	@Override
	public int selectCountAdminById(SqlSession session, String id) {
		int check = session.selectOne("AdminMapper.selecCountAdminById", id);
		return check;
	}

	// 관리자 로그인
	@Override
	public Admin selectLoginAdmin(SqlSession session, Admin admin) {
		Admin loginAdmin = session.selectOne("AdminMapper.selectLoginAdmin", admin);
		return loginAdmin;
	}

	// 전체 회원 수
	@Override
	public int selectCountAllUser(SqlSession session) {
		int count = session.selectOne("AdminMapper.selectCountAllUser");
		return count;
	}

	// 회원 목록
	@Override
	public List<User> selectUserList(SqlSession session, HashMap<String, Integer> pageInfo) {
		int offset = (pageInfo.get("currentPage") - 1) * pageInfo.get("userLimit");
		RowBounds rowBounds = new RowBounds(offset, pageInfo.get("userLimit"));
		List<User> uList = session.selectList("AdminMapper.selectUserList", null, rowBounds);
		return uList;
	}

}

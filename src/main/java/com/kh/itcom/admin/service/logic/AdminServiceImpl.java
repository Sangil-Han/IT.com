package com.kh.itcom.admin.service.logic;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.admin.service.AdminService;
import com.kh.itcom.admin.store.AdminStore;
import com.kh.itcom.user.domain.User;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private AdminStore aStore;

	// 관리자 아이디 판별
	@Override
	public int checkId(String id) {
		int check = aStore.selectCountAdminById(session, id);
		return check;
	}

	// 관리자 로그인
	@Override
	public Admin loginAdmin(Admin admin) {
		Admin loginAdmin = aStore.selectLoginAdmin(session, admin);
		return loginAdmin;
	}

	// 전체 회원 수
	@Override
	public int printTotalUserCount() {
		int count = aStore.selectCountAllUser(session);
		return count;
	}

	// 회원 목록
	@Override
	public List<User> printUserList(HashMap<String, Integer> pageInfo) {
		List<User> uList = aStore.selectUserList(session, pageInfo);
		return uList;
	}

}

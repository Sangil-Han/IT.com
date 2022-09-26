package com.kh.itcom.admin.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.admin.service.AdminService;
import com.kh.itcom.admin.store.AdminStore;
import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.user.domain.LevelUp;
import com.kh.itcom.user.domain.User;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private AdminStore aStore;

	// 관리자 아이디 판별
	@Override
	public int checkAdminId(String id) {
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
	public List<User> printUserList(PageInfo upi) {
		List<User> uList = aStore.selectUserList(session, upi);
		return uList;
	}

	// 회원 삭제
	@Override
	public int removeUsers(List<String> idList) {
		int result = aStore.deleteUsers(session, idList);
		return result;
	}

	// 등업 신청 목록
	@Override
	public List<LevelUp> printLevelUpList(PageInfo lupi) {
		List<LevelUp> luList = aStore.selectLevelUpList(session, lupi);
		return luList;
	}

}

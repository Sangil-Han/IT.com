package com.kh.itcom.admin.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.admin.store.AdminStore;
import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.user.domain.LevelUp;
import com.kh.itcom.user.domain.User;

@Repository
public class AdminStoreLogic implements AdminStore {

	// 등업 신청 승인
	@Override
	public int updateLevelApproval(SqlSession session, List<String> checkedUsers) {
		int result = session.update("UserMapper.updateLevel", checkedUsers);
		result = session.update("LevelMapper.updateLevelApproval", checkedUsers);
		return result;
	}

	// 등업 신청 거절
	@Override
	public int updateLevelDenial(SqlSession session, List<String> checkedUsers) {
		int result = session.update("LevelMapper.updateLevelDenial", checkedUsers);
		return result;
	}

	// 회원 삭제
	@Override
	public int deleteUsers(SqlSession session, List<String> checkedUsers) {
		int result = session.update("UserMapper.deleteUsers", checkedUsers);
		return result;
	}

	// 아이디 체크
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

	// 총 회원 수
	@Override
	public int selectCountAllUser(SqlSession session) {
		int count = session.selectOne("UserMapper.selectCountAllUser");
		return count;
	}

	// 회원 목록
	@Override
	public List<User> selectUserList(SqlSession session, PageInfo upi) {
		int offset = (upi.getCurrentPage() - 1) * upi.getRowLimit();
		RowBounds rowBounds = new RowBounds(offset, upi.getRowLimit());
		List<User> uList = session.selectList("UserMapper.selectUserList", null, rowBounds);
		return uList;
	}

	// 총 등업 신청 수
	@Override
	public int selectCountAllLevelUp(SqlSession session) {
		int count = session.selectOne("LevelMapper.selectCountAllLevelUp");
		return count;
	}

	// 등업 신청 목록
	@Override
	public List<LevelUp> selectLevelUpList(SqlSession session, PageInfo lupi) {
		int offset = (lupi.getCurrentPage() - 1) * lupi.getRowLimit();
		RowBounds rowBounds = new RowBounds(offset, lupi.getRowLimit());
		List<LevelUp> luList = session.selectList("LevelMapper.selectLevelUpList", null, rowBounds);
		return luList;
	}

}

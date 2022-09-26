package com.kh.itcom.admin.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.user.domain.LevelUp;
import com.kh.itcom.user.domain.User;

public interface AdminStore {

	// 아이디 체크
	int selectCountAdminById(SqlSession session, String id);

	// 관리자 로그인
	Admin selectLoginAdmin(SqlSession session, Admin admin);

	// 회원 목록
	List<User> selectUserList(SqlSession session, PageInfo upi);

	// 총 회원 수
	int selectCountAllUser(SqlSession session);

	// 회원 삭제
	int deleteUsers(SqlSession session, List<String> idList);

	// 등업 신청 목록
	List<LevelUp> selectLevelUpList(SqlSession session, PageInfo lupi);
}

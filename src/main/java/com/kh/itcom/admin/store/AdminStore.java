package com.kh.itcom.admin.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.user.domain.LevelUp;
import com.kh.itcom.user.domain.User;

public interface AdminStore {

	// 등업 신청 승인
	int updateLevelApproval(SqlSession session, List<String> checkedUsers);

	// 등업 신청 거절
	int updateLevelDenial(SqlSession session, List<String> checkedUsers);

	// 회원 삭제
	int deleteUsers(SqlSession session, List<String> checkedUsers);

	// 아이디 체크
	int selectCountAdminById(SqlSession session, String id);

	// 관리자 로그인
	Admin selectLoginAdmin(SqlSession session, Admin admin);

	// 총 회원 수
	int selectCountAllUser(SqlSession session);

	// 회원 목록
	List<User> selectUserList(SqlSession session, PageInfo upi);

	// 총 등업 신청 수
	int selectCountAllLevelUp(SqlSession session);

	// 등업 신청 목록
	List<LevelUp> selectLevelUpList(SqlSession session, PageInfo lupi);
}

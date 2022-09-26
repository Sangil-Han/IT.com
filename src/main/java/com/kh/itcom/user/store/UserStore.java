package com.kh.itcom.user.store;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.user.domain.LevelUp;
import com.kh.itcom.user.domain.MyPost;
import com.kh.itcom.user.domain.PointHistory;
import com.kh.itcom.user.domain.User;

public interface UserStore {

	// 회원가입
	int insertUser(SqlSession session, User user);

	// 회원 탈퇴
	int deleteUser(SqlSession session, HashMap<String, String> userInfo);

	// 회원 로그인
	User selectLoginUser(SqlSession session, User user);

	// 총 포인트 내역 개수
	int selectCountPoint(SqlSession session, String userId);

	// 포인트 내역
	List<PointHistory> selectPointHistory(SqlSession session, String userId, PageInfo phpi);

	// 등업 신청
	int insertLevelUp(SqlSession session, LevelUp lvUp);

	// 회원 아이디 중복 확인
	int selectCountUserById(SqlSession session, String userId);

	// 현재 비밀번호 확인
	int selectCountUserByPw(SqlSession session, User user);

	// 비밀번호 재설정
	int updatePw(SqlSession session, User user);

	// 등급 변동 내역
	List<LevelUp> selectLevelHistory(SqlSession session, String userId);

	// 작성글 조회
	List<MyPost> selectMyPostList(SqlSession session, String userId, PageInfo mppi);

	// 작성글 개수
	int selectCountMyPost(SqlSession session, String userId);

}

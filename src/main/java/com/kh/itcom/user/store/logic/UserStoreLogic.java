package com.kh.itcom.user.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.itcom.admin.domain.Admin;
import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.user.domain.LevelUp;
import com.kh.itcom.user.domain.MyPost;
import com.kh.itcom.user.domain.PointHistory;
import com.kh.itcom.user.domain.User;
import com.kh.itcom.user.store.UserStore;

@Repository
public class UserStoreLogic implements UserStore {

	// 회원가입
	@Override
	public int insertUser(SqlSession session, User user) {
		int result = session.insert("UserMapper.insertUser", user);
		return result;
	}

	// 회원 탈퇴
	@Override
	public int deleteUser(SqlSession session, HashMap<String, String> userInfo) {
		int result = session.update("UserMapper.deleteUser", userInfo);
		return result;
	}

	// 로그인
	@Override
	public User selectLoginUser(SqlSession session, User user) {
		User loginUser = session.selectOne("UserMapper.selectLoginUser", user);
		return loginUser;
	}

	// 총 포인트 내역 개수
	@Override
	public int selectCountPoint(SqlSession session, String userId) {
		int count = session.selectOne("PointMapper.selectCountPoint", userId);
		return count;
	}

	// 포인트 내역
	@Override
	public List<PointHistory> selectPointHistory(SqlSession session, String userId, PageInfo phpi) {
		int offset = (phpi.getCurrentPage() - 1) * phpi.getRowLimit();
		RowBounds rowBounds = new RowBounds(offset, phpi.getRowLimit());
		List<PointHistory> phList = session.selectList("PointMapper.selectPointHistory", userId, rowBounds);
		return phList;
	}

	// 등업 신청
	@Override
	public int insertLevelUp(SqlSession session, LevelUp lvUp) {
		int result = session.insert("LevelMapper.insertLevelUp", lvUp);
		return result;
	}

	// 회원 아이디 중복 확인
	@Override
	public int selectCountUserById(SqlSession session, String userId) {
		int check = session.selectOne("UserMapper.selectCountUserById", userId);
		return check;
	}

	// 현재 비밀번호 확인
	@Override
	public int selectCountUserByPw(SqlSession session, User user) {
		int check = session.selectOne("UserMapper.selectCountUserByPw", user);
		return check;
	}

	// 비밀번호 재설정
	@Override
	public int updatePw(SqlSession session, User user) {
		int result = session.update("UserMapper.updatePw", user);
		return result;
	}

	// 등급 변동 내역
	@Override
	public List<LevelUp> selectLevelHistory(SqlSession session, String userId) {
		List<LevelUp> lhList = session.selectList("LevelMapper.selectLevelHistory", userId);
		return lhList;
	}

	// 작성글 조회
	@Override
	public List<MyPost> selectMyPostList(SqlSession session, String userId, PageInfo mppi) {
		int offset = (mppi.getCurrentPage() - 1) * mppi.getRowLimit();
		RowBounds rowBounds = new RowBounds(offset, mppi.getRowLimit());
		List<MyPost> mpList = session.selectList("UserMapper.selectMyPostList", userId, rowBounds);
		return mpList;
	}

	// 작성글 개수
	@Override
	public int selectCountMyPost(SqlSession session, String userId) {
		int count = session.selectOne("UserMapper.selectCountMyPost", userId);
		return count;
	}

}

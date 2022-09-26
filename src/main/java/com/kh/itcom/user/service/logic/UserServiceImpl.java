package com.kh.itcom.user.service.logic;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.common.domain.PageInfo;
import com.kh.itcom.user.domain.LevelUp;
import com.kh.itcom.user.domain.MyPost;
import com.kh.itcom.user.domain.PointHistory;
import com.kh.itcom.user.domain.User;
import com.kh.itcom.user.service.UserService;
import com.kh.itcom.user.store.UserStore;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private UserStore uStore;

	// 회원가입
	@Override
	public int joinUser(User user) {
		int result = uStore.insertUser(session, user);
		return result;
	}

	// 회원 탈퇴
	@Override
	public int removeUser(HashMap<String, String> userInfo) {
		int result = uStore.deleteUser(session, userInfo);
		return result;
	}

	// 로그인
	@Override
	public User loginUser(User user) {
		User loginUser = uStore.selectLoginUser(session, user);
		return loginUser;
	}

	// 총 포인트 내역 개수
	@Override
	public int printTotalPointCount(String userId) {
		int count = uStore.selectCountPoint(session, userId);
		return count;
	}

	// 포인트 내역
	@Override
	public List<PointHistory> printPointHistory(String userId, PageInfo phpi) {
		List<PointHistory> phList = uStore.selectPointHistory(session, userId, phpi);
		return phList;
	}

	// 등업 신청
	@Override
	public int applyLevelUp(LevelUp lvUp) {
		int result = uStore.insertLevelUp(session, lvUp);
		return result;
	}


	// 회원 아이디 중복 확인
	@Override
	public int checkUserId(String userId) {
		int check = uStore.selectCountUserById(session, userId);
		return check;
	}

	// 현재 비밀번호 확인
	@Override
	public int checkPw(User user) {
		int check = uStore.selectCountUserByPw(session, user);
		return check;
	}

	// 비밀번호 재설정
	@Override
	public int resetPw(User user) {
		int result = uStore.updatePw(session, user);
		return result;
	}

	// 등급 변동 내역
	@Override
	public List<LevelUp> printLevelHistory(String userId) {
		List<LevelUp> lhList = uStore.selectLevelHistory(session, userId);
		return lhList;
	}

	// 작성글 조회
	@Override
	public List<MyPost> printMyPostList(String userId, PageInfo mppi) {
		List<MyPost> mpList = uStore.selectMyPostList(session, userId, mppi);
		return mpList;
	}

	// 작성글 개수
	@Override
	public int printTotalMyPostCount(String userId) {
		int count = uStore.selectCountMyPost(session, userId);
		return count;
	}

}

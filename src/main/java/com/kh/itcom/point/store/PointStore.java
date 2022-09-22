package com.kh.itcom.point.store;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.point.domain.PointHistory;

public interface PointStore {

	// 총 포인트 내역 개수
	int selectCountAllPoint(SqlSession session, String userId);

	// 포인트 내역
	List<PointHistory> selectPointHistory(SqlSession session, String userId, HashMap<String, Integer> pageInfo);

}

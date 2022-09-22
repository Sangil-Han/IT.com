package com.kh.itcom.point.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.itcom.point.domain.PointHistory;
import com.kh.itcom.point.store.PointStore;

@Repository
public class PointStoreLogic implements PointStore {

	@Override
	public int selectCountAllPoint(SqlSession session, String userId) {
		int count = session.selectOne("PointMapper.selectCountAllPoint", userId);
		return count;
	}

	@Override
	public List<PointHistory> selectPointHistory(SqlSession session, String userId, HashMap<String, Integer> pageInfo) {
		int offset = (pageInfo.get("currentPage") - 1) * pageInfo.get("pointLimit");
		RowBounds rowBounds = new RowBounds(offset, pageInfo.get("pointLimit"));
		List<PointHistory> phList = session.selectList("PointMapper.selectPointHistory", userId, rowBounds);
		return phList;
	}
	
}

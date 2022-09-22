package com.kh.itcom.point.service.logic;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.point.domain.PointHistory;
import com.kh.itcom.point.service.PointService;
import com.kh.itcom.point.store.PointStore;

@Service
public class PointServiceImpl implements PointService {

	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private PointStore pStore;
	
	// 총 포인트 내역 개수
	@Override
	public int printTotalPointCount(String userId) {
		int count = pStore.selectCountAllPoint(session, userId);
		return count;
	}

	// 포인트 내역
	@Override
	public List<PointHistory> printPointHistory(String userId, HashMap<String, Integer> pageInfo) {
		List<PointHistory> phList = pStore.selectPointHistory(session, userId, pageInfo);
		return phList;
	}

}

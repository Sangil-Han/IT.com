package com.kh.itcom.point.service;

import java.util.HashMap;
import java.util.List;

import com.kh.itcom.point.domain.PointHistory;

public interface PointService {

	// 총 포인트 내역 개수
	int printTotalPointCount(String userId);

	// 포인트 내역
	List<PointHistory> printPointHistory(String userId, HashMap<String, Integer> pageInfo);

}

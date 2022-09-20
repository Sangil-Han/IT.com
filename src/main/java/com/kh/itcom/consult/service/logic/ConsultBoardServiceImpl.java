package com.kh.itcom.consult.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.consult.domain.ConsultBoard;
import com.kh.itcom.consult.service.ConsultBoardService;
import com.kh.itcom.consult.store.ConsultBoardStore;

@Service
public class ConsultBoardServiceImpl implements ConsultBoardService{
	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private ConsultBoardStore cStore;
	
	@Override
	public int registerBoard(ConsultBoard cBoard) {
		int result = cStore.insertBoard(session, cBoard);
		return result;
	}

	@Override
	public List<ConsultBoard> printAllBoard(int currentPage, int boardLimit) {
		List<ConsultBoard> cList = cStore.selectAllBoard(session, currentPage, boardLimit);
		return cList;
	}

	@Override
	public int getTotalCount(String searchCondition, String searchValue) {
		int totalCount = cStore.selectTotalCount(session, searchCondition, searchValue);
		return totalCount;
	}

	@Override
	public List<ConsultBoard> printAllByValue(String searchCondition, String searchValue, int currentPage,
			int boardLimit) {
		 List<ConsultBoard> cList = cStore.selectAllByValue(session,searchCondition,searchValue,currentPage,boardLimit);
		return cList;
	}

	@Override
	public ConsultBoard printOneByNo(Integer cBoardNo) {
		ConsultBoard cBoard = cStore.selectOneByNo(session, cBoardNo);
		return cBoard;
	}
}

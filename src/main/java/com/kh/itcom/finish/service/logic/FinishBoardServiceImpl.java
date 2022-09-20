package com.kh.itcom.finish.service.logic;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.finish.service.FinishBoardService;
import com.kh.itcom.finish.store.FinishBoardStore;

@Service
public class FinishBoardServiceImpl implements FinishBoardService{

	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private FinishBoardStore fStore;
	
	@Override
	public int registerBoard(FinishBoard fBoard) {
		int result=fStore.insertBoard(session, fBoard);
		return result;
	}

	@Override
	public int getTotalCount(String searchOption, String searchValue) {
		int totalCount=fStore.selectTotalCount(session, searchOption, searchValue);
		return totalCount;
	}

	@Override
	public List<FinishBoard> printAllBoard(int currentPage, int boardLimit) {
		List<FinishBoard> fList=fStore.selectAllBoard(session, currentPage, boardLimit);
		return fList;
	}

	@Override
	public List<FinishBoard> printAllByValue(String searchOption, String searchValue, int currentPage, int boardLimit) {
		List<FinishBoard> fList=fStore.selectAllByValue(session, searchOption, searchValue, currentPage, boardLimit);
		return fList;
	}

}

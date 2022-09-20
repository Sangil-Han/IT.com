package com.kh.itcom.consult.store.logic;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.itcom.consult.domain.ConsultBoard;
import com.kh.itcom.consult.store.ConsultBoardStore;

@Repository
public class ConsultBoardStoreLogic implements ConsultBoardStore {

	@Override
	public int insertBoard(SqlSession session, ConsultBoard cBoard) {
		int result = session.insert("CBoardMapper.insertBoard", cBoard);
		return result;
	}

	@Override
	public List<ConsultBoard> selectAllBoard(SqlSession session, int currentPage, int boardLimit) {
		int offset = (currentPage - 1)*boardLimit;
		RowBounds rowBounds = new RowBounds(offset, boardLimit);
		List<ConsultBoard> cList = session.selectList("CBoardMapper.selectAllBoard", null, rowBounds);
		return cList;
	}

	@Override
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		int totalCount = session.selectOne("CBoardMapper.selectTotalCount", paramMap);
		return totalCount;
	}

	@Override
	public List<ConsultBoard> selectAllByValue(SqlSessionTemplate session, String searchCondition, String searchValue,
			int currentPage, int boardLimit) {
		int offset = (currentPage - 1)*boardLimit;
		RowBounds rowBounds = new RowBounds(offset, boardLimit);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		List<ConsultBoard> cList = session.selectList("CBoardMapper.selectAllByValue",paramMap,rowBounds);
		return cList;
	}

	@Override
	public ConsultBoard selectOneByNo(SqlSessionTemplate session, Integer cBoardNo) {
		ConsultBoard cBoard = session.selectOne("CBoardMapper.selectOneByNo", cBoardNo);
		return null;
	}
	
}

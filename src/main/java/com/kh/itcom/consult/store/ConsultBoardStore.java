package com.kh.itcom.consult.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.consult.domain.ConsultBoard;

public interface ConsultBoardStore {
	
	public List<ConsultBoard> selectAllBoard(SqlSession session, int currentPage, int boardLimit);
	
	public int insertBoard(SqlSession session, ConsultBoard cBoard);
	
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue);

	public List<ConsultBoard> selectAllByValue(SqlSessionTemplate session, String searchCondition, String searchValue,
			int currentPage, int boardLimit);

	public ConsultBoard selectOneByNo(SqlSessionTemplate session, Integer cBoardNo);
}

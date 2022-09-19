package com.kh.itcom.finish.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.finish.domain.FinishBoard;

public interface FinishBoardStore {

	int insertBoard(SqlSession session, FinishBoard fBoard);

	int selectTotalCount(SqlSessionTemplate session, String searchOption, String searchValue);

	List<FinishBoard> selectAllBoard(SqlSessionTemplate session, int currentPage, int boardLimit);

	List<FinishBoard> selectAllByValue(SqlSessionTemplate session, String searchOption, String searchValue,
			int currentPage, int boardLimit);
}

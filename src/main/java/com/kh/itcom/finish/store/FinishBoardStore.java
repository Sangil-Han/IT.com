package com.kh.itcom.finish.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.finish.domain.FinishComment;

public interface FinishBoardStore {

	int insertBoard(SqlSession session, FinishBoard fBoard);

	int selectTotalCount(SqlSessionTemplate session, String searchOption, String searchValue);

	List<FinishBoard> selectAllBoard(SqlSessionTemplate session, int currentPage, int boardLimit);

	List<FinishBoard> selectAllByValue(SqlSessionTemplate session, String searchOption, String searchValue,
			int currentPage, int boardLimit);

	FinishBoard selectOneByNo(SqlSessionTemplate session, int fBoardNo);

	void updateBoardCount(SqlSessionTemplate session, int fBoardNo);

	int updateBoard(SqlSessionTemplate session, FinishBoard fBoard);

	int insertComment(SqlSessionTemplate session, FinishComment fComment);
	
	int updateUserPoint(SqlSessionTemplate session, String userId, String point);
}

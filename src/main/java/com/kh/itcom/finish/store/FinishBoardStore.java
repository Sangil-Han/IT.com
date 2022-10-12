package com.kh.itcom.finish.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.finish.domain.FinishComment;

public interface FinishBoardStore {

	int insertBoard(SqlSession session, FinishBoard fBoard);

	int insertComment(SqlSessionTemplate session, FinishComment fComment);

	int insertUpDownCount(SqlSessionTemplate session, Integer fBoardNo, String userId, String upOrDown);

	int updateBoard(SqlSessionTemplate session, FinishBoard fBoard);

	int updateBoardCount(SqlSessionTemplate session, int fBoardNo);

	int updateUserPoint(SqlSessionTemplate session, String userId, String point);

	int updateComment(SqlSessionTemplate session, Map<String, Object> inputMap);

	int deleteComment(SqlSessionTemplate session, Integer fCommentNo);

	int selectTotalCount(SqlSessionTemplate session, String searchOption, String searchValue);

	int selectCountUp(SqlSessionTemplate session, int fBoardNo);

	int selectCountDown(SqlSessionTemplate session, int fBoardNo);

	int selectUserRecordUpDown(SqlSessionTemplate session, String userId, int fBoardNo);

	FinishBoard selectOneByNo(SqlSessionTemplate session, int fBoardNo);

	List<FinishBoard> selectAllBoard(SqlSessionTemplate session, int currentPage, int boardLimit);

	List<FinishBoard> selectAllByValue(SqlSessionTemplate session, String searchOption, String searchValue,
			int currentPage, int boardLimit);

	List<FinishComment> selectAllComment(SqlSessionTemplate session, int fBoardNo);
}

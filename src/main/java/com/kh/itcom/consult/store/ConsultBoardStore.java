package com.kh.itcom.consult.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;

import com.kh.itcom.consult.domain.ConsultBoard;
import com.kh.itcom.consult.domain.ConsultBoardComment;
import com.kh.itcom.consult.domain.ConsultDownCount;
import com.kh.itcom.consult.domain.ConsultUpCount;
import com.kh.itcom.consult.domain.ConsultViewCount;
import com.kh.itcom.user.domain.User;

public interface ConsultBoardStore {
	
	public int insertBoard(SqlSession session, ConsultBoard cBoard);

	public int updateBoard(SqlSessionTemplate session, ConsultBoard cBoard);

	public List<ConsultBoard> selectAllBoard(SqlSession session, int currentPage, int boardLimit);
	
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue);

	public List<ConsultBoard> selectAllByValue(SqlSession session, String searchCondition, String searchValue,
			int currentPage, int boardLimit);
	
	public ConsultBoard selectOneByNo(SqlSession session, Integer cBoardNo);

	///////////////////////////// 댓글 기능 ////////////////////////////////////////
	public int insertComment(SqlSession session, ConsultBoardComment comment);

	public List<ConsultBoardComment> selectAllComment(SqlSessionTemplate session, Integer cBoardNo);

	public int deleteComment(SqlSessionTemplate session, Integer commentNo);


	public int insertUpCount(SqlSessionTemplate session, ConsultUpCount upCount);

	public int deleteUpCount(SqlSessionTemplate session, ConsultUpCount upCount);

	public int selectUpCount(SqlSessionTemplate session, ConsultUpCount upCount);

	public int selectTotalUpCount(SqlSessionTemplate session, Integer cBoardNo);

	public int selectDownCount(SqlSessionTemplate session, ConsultDownCount downCount);

	public int insertDownCount(SqlSessionTemplate session, ConsultDownCount downCount);

	public int deleteDownCount(SqlSessionTemplate session, ConsultDownCount downCount);

	public int selectTotalDownCount(SqlSessionTemplate session, Integer cBoardNo);

	public int updateComment(SqlSessionTemplate session, ConsultBoardComment comment);

	public int insertViewCount(SqlSessionTemplate session, ConsultViewCount viewCount);

	public int selectViewCountCheck(SqlSessionTemplate session, ConsultViewCount viewCount);

	public int selectTotalViewCount(SqlSessionTemplate session, ConsultViewCount viewCount);

	public int updateBoardViewCount(SqlSessionTemplate session, Integer cBoardNo);

	public int updateBoardUp(SqlSessionTemplate session, int consultBoardNo);

	public int deleteBoard(SqlSessionTemplate session, int cBoardNo);

	public int updatePoint(SqlSessionTemplate session, User loginUser);

	public void updateViewable(SqlSessionTemplate session, User loginUser);

	public User selectUser(SqlSessionTemplate session, User loginUser);

}
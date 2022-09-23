package com.kh.itcom.consult.store.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.itcom.consult.domain.ConsultBoard;
import com.kh.itcom.consult.domain.ConsultBoardComment;
import com.kh.itcom.consult.domain.ConsultDownCount;
import com.kh.itcom.consult.domain.ConsultUpCount;
import com.kh.itcom.consult.domain.ConsultViewCount;
import com.kh.itcom.consult.store.ConsultBoardStore;

@Repository
public class ConsultBoardStoreLogic implements ConsultBoardStore {

	@Override
	public int insertBoard(SqlSession session, ConsultBoard cBoard) {
		int result = session.insert("CBoardMapper.insertBoard", cBoard);
		return result;
	}

	@Override
	public int updateBoard(SqlSessionTemplate session, ConsultBoard cBoard) {
		int result = session.update("CBoardMapper.updateBoard",cBoard);
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
	public List<ConsultBoard> selectAllByValue(SqlSession session, String searchCondition, String searchValue,
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
	public ConsultBoard selectOneByNo(SqlSession session, Integer cBoardNo) {
		ConsultBoard cBoard = session.selectOne("CBoardMapper.selectOneByNo", cBoardNo);
		return cBoard;
	}

	@Override
	public int insertComment(SqlSession session, ConsultBoardComment comment) {
		int result = session.insert("CBoardMapper.insertComment",comment);
		return result;
	}

	@Override
	public List<ConsultBoardComment> selectAllComment(SqlSessionTemplate session, Integer cBoardNo) {
		List<ConsultBoardComment> cList = session.selectList("CBoardMapper.selectAllComment", cBoardNo);
		return cList;
	}

	@Override
	public int deleteComment(SqlSessionTemplate session, Integer commentNo) {
		int result= session.delete("CBoardMapper.deleteComment",commentNo);
		return result;
	}

	@Override
	public int boardCount(SqlSessionTemplate session, Integer cBoardNo) {
		int result = session.update("CBoardMapper.updateCount",cBoardNo);
		return result;
	}

	@Override
	public int insertUpCount(SqlSessionTemplate session, ConsultUpCount upCount) {
		int result = session.insert("CBoardMapper.insertUpCount", upCount);
		return result;
	}

	@Override
	public int selectUpCount(SqlSessionTemplate session, ConsultUpCount upCount) {
		int result = session.selectOne("CBoardMapper.selectUpCount",upCount);
		return result;
	}

	@Override
	public int selectTotalUpCount(SqlSessionTemplate session, Integer cBoardNo) {
		int result = session.selectOne("CBoardMapper.selectTotalUpCount", cBoardNo);
		return result;
	}

	@Override
	public int deleteUpCount(SqlSessionTemplate session, ConsultUpCount upCount) {
		int result = session.delete("CBoardMapper.deleteUpCount", upCount);
		return result;
	}

	@Override
	public int insertDownCount(SqlSessionTemplate session, ConsultDownCount downCount) {
		int result = session.insert("CBoardMapper.insertDownCount", downCount);
		return result;
	}

	@Override
	public int selectDownCount(SqlSessionTemplate session, ConsultDownCount downCount) {
		int result = session.selectOne("CBoardMapper.selectDownCount",downCount);
		return result;
	}

	@Override
	public int deleteDownCount(SqlSessionTemplate session, ConsultDownCount downCount) {
		int result = session.delete("CBoardMapper.deleteDownCount", downCount);
		return result;
	}

	@Override
	public int selectTotalDownCount(SqlSessionTemplate session, Integer cBoardNo) {
		int result = session.selectOne("CBoardMapper.selectTotalDownCount", cBoardNo);
		return result;
	}

	@Override
	public int updateComment(SqlSessionTemplate session, ConsultBoardComment comment) {
		int result = session.update("CBoardMapper.updateComment",comment);
		return result;
	}

	@Override
	public int insertViewCount(SqlSessionTemplate session, ConsultViewCount viewCount) {
		int result = session.insert("CBoardMapper.insertViewCount", viewCount);
		return result;
	}

	@Override
	public int selectViewCountCheck(SqlSessionTemplate session, ConsultViewCount viewCount) {
		int result = session.selectOne("CBoardMapper.selectViewCountCheck", viewCount);
		return result;
	}

	@Override
	public int selectTotalViewCount(SqlSessionTemplate session, ConsultViewCount viewCount) {
		int result = session.selectOne("CBoardMapper.selectTotalViewCount", viewCount);
		return result;
	}
	
}

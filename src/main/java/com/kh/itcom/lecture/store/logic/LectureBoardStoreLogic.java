package com.kh.itcom.lecture.store.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.itcom.consult.domain.ConsultBoardComment;
import com.kh.itcom.lecture.domain.LectureBoard;
import com.kh.itcom.lecture.domain.LectureBoardComment;
import com.kh.itcom.lecture.store.LectureBoardStore;

@Repository
public class LectureBoardStoreLogic implements LectureBoardStore {
	// Read

	@Override
	public int insertLecture(SqlSession session, LectureBoard lectureboard) {
		int result = session.insert("LectureMapper.insertLecture", lectureboard);
		return result;
	}

	@Override
	public List<LectureBoard> selectAllLectureBoard(SqlSession session, int currentPage, int lboardLimit) {
		int offset = (currentPage-1)*lboardLimit;
		RowBounds rowBounds = new RowBounds(offset, lboardLimit);
		List<LectureBoard> lbList = session.selectList("LectureMapper.selectAllLectureBoard", null, rowBounds);
		return lbList;
	}

	@Override
	public LectureBoard selectOneByNo(SqlSession session, Integer lBoardNo) {
		LectureBoard lectureboard = session.selectOne("LectureMapper.selectOneByNo", lBoardNo);
		return lectureboard;
	}

	@Override
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue) {
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		int totalCount = session.selectOne("LectureMapper.selectTotalCount", paramMap);
		return totalCount;
	}

	@Override
	public List<LectureBoard> selectAllByValue(SqlSession session, String searchCondition, String searchValue, int currentPage, int lboardLimit) {
		int offset = (currentPage-1)*lboardLimit;
		RowBounds rowBounds 
		= new RowBounds(offset, lboardLimit);
		HashMap<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchValue", searchValue);
		List<LectureBoard> lbList 
		= session.selectList("LectureMapper.selectAllByValue", paramMap, rowBounds);
		return lbList;
	}

	@Override
	public int updateLectureBoard(SqlSession session, LectureBoard lectureboard) {
		int result = session.update("LectureMapper.updateLectureBoard", lectureboard);
		return result;
	}

	@Override
	public int updateLectureCount(SqlSession session, int lBoardNo) {
		int result = session.update("LectureMapper.updateLectureCount", lBoardNo);
		return result;
	}

	// 댓글
	@Override
	public int insertComment(SqlSession session, LectureBoardComment lbComment) {
		int result = session.insert("LectureMapper.insertComment", lbComment);
		return result;
	}

	@Override
	public void updateComment(SqlSession session, Map<String, Object> inputMap) {
		session.update("LectureMapper.updateComment", inputMap);
	}
	
	@Override
	public int deleteComment(SqlSession session, Integer lCommentNo) {
		int result= session.delete("LectureMapper.deleteComment",lCommentNo);
		return result;
	}

	@Override
	public List<LectureBoardComment> selectAllComment(SqlSession session, Integer lBoardNo) {
		List<LectureBoardComment> lcList = session.selectList("LectureMapper.selectAllComment", lBoardNo);
		return lcList;
	}

}

package com.kh.itcom.lecture.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.itcom.lecture.domain.LectureBoard;

public interface LectureBoardStore {
	public int insertLecture(SqlSession session, LectureBoard lectureboard);
	
	public List<LectureBoard> selectAllLectureBoard(SqlSession session, int currentPage, int lboardLimit);
	
	public LectureBoard selectOneByNo(SqlSession session, Integer lBoardNo);
	
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue);
	
	public List<LectureBoard> selectAllByValue(SqlSession session, String searchCondition, String searchValue, int currentPage, int boardLimit);

	public int updateLectureBoard(SqlSession session, LectureBoard lectureboard);

	public int updateLectureCount(SqlSession session, int lBoardNo);
}

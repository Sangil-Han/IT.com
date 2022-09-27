package com.kh.itcom.lecture.store;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.itcom.lecture.domain.LectureBoard;
import com.kh.itcom.lecture.domain.LectureBoardComment;
import com.kh.itcom.lecture.domain.LectureUpCount;

public interface LectureBoardStore {
	public int insertLecture(SqlSession session, LectureBoard lectureboard);
	
	public List<LectureBoard> selectAllLectureBoard(SqlSession session, int currentPage, int lboardLimit);
	
	public LectureBoard selectOneByNo(SqlSession session, Integer lBoardNo);
	
	public int selectTotalCount(SqlSession session, String searchCondition, String searchValue);
	
	public List<LectureBoard> selectAllByValue(SqlSession session, String searchCondition, String searchValue, int currentPage, int boardLimit);

	public int updateLectureBoard(SqlSession session, LectureBoard lectureboard);

	public int updateLectureCount(SqlSession session, int lBoardNo);

	// 댓글
	public int insertComment(SqlSession session, LectureBoardComment lbComment);

	public void updateComment(SqlSession session, Map<String, Object> inputMap);

	public int deleteComment(SqlSession session, Integer lCommentNo);

	public List<LectureBoardComment> selectAllComment(SqlSession session, Integer lBoardNo);

	public int selectUpCount(SqlSession session, LectureUpCount lUpCount);

	public int insertUpCount(SqlSession session, LectureUpCount lUpCount);

	public int updateBoardUp(SqlSession session, int lectureBoardNo);

	public int deleteUpCount(SqlSession session, LectureUpCount lUpCount);
}

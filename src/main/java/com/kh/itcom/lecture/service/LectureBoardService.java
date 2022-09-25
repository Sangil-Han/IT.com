package com.kh.itcom.lecture.service;

import java.util.List;

import com.kh.itcom.lecture.domain.LectureBoard;
import com.kh.itcom.lecture.domain.LectureBoardComment;

public interface LectureBoardService {
	public int registerLecture(LectureBoard lectureboard);
	
	public LectureBoard printOneByNo(Integer lBoardNo);

	public int getTotalCount(String searchCondition, String searchValue);

	public List<LectureBoard> printAllByValue(String searchCondition, String searchValue, int currentPage, int lboardLimit);

	public List<LectureBoard> printAllLectureBoard(int currentPage, int lboardLimit);

	public int modifyLecture(LectureBoard lectureboard);

	// 댓글
	public int registerComment(LectureBoardComment lbComment);

	public int modifyComment(LectureBoardComment lbComment);

	public int deleteComment(Integer lCommentNo);

	public List<LectureBoardComment> printAllLectureBoardComment(Integer lBoardNo);

	// 댓글 목록 조회
}

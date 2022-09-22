package com.kh.itcom.lecture.service;

import java.util.List;

import com.kh.itcom.lecture.domain.LectureBoard;

public interface LectureBoardService {
	public int registerLecture(LectureBoard lectureboard);
	

	public LectureBoard printOneByNo(Integer lBoardNo);

	public int getTotalCount(String searchCondition, String searchValue);

	public List<LectureBoard> printAllByValue(String searchCondition, String searchValue, int currentPage, int lboardLimit);

	public List<LectureBoard> printAllLectureBoard(int currentPage, int lboardLimit);


	public int modifyLecture(LectureBoard lectureboard);
}

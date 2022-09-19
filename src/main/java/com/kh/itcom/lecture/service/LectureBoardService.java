package com.kh.itcom.lecture.service;

import java.util.List;

import com.kh.itcom.lecture.domain.LectureBoard;

public interface LectureBoardService {
	public int registerLecture(LectureBoard lectureboard);
	
	public List<LectureBoard> printAllLectureBoard();
}

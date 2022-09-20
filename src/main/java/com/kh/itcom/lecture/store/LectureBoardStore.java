package com.kh.itcom.lecture.store;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.itcom.lecture.domain.LectureBoard;

public interface LectureBoardStore {
	public int insertLecture(SqlSession session, LectureBoard lectureboard);
	
	public List<LectureBoard> selectAllLectureBoard(SqlSession session);
}

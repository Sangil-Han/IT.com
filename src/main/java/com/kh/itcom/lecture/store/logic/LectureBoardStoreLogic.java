package com.kh.itcom.lecture.store.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.itcom.lecture.domain.LectureBoard;
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
	public List<LectureBoard> selectAllLectureBoard(SqlSession session) {
		List<LectureBoard> lbList = session.selectList("LectureMapper.selectAllLectureBoard");
		return lbList;
	}
}

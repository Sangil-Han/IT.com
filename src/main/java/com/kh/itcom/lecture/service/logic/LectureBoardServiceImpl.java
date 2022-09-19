package com.kh.itcom.lecture.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.lecture.domain.LectureBoard;
import com.kh.itcom.lecture.service.LectureBoardService;
import com.kh.itcom.lecture.store.LectureBoardStore;

@Service
public class LectureBoardServiceImpl implements LectureBoardService {
	// LectureBoardStore(Logic)과 CrawlingStoreLogic 2개 씀
	@Autowired
	private SqlSession session;
	@Autowired
	private LectureBoardStore lbStore;
	@Override
	public int registerLecture(LectureBoard lectureboard) {
		int result = lbStore.insertLecture(session, lectureboard);
		return result;
	}
	@Override
	public List<LectureBoard> printAllLectureBoard() {
		List<LectureBoard> lbList = lbStore.selectAllLectureBoard(session);
		return lbList;
	}
}

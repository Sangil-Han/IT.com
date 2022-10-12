package com.kh.itcom.finish.service.logic;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.finish.domain.FinishComment;
import com.kh.itcom.finish.service.FinishBoardService;
import com.kh.itcom.finish.store.FinishBoardStore;

@Service
public class FinishBoardServiceImpl implements FinishBoardService{

	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private FinishBoardStore fStore;
	
	@Override
	public int registerBoard(FinishBoard fBoard) {
		int result=fStore.insertBoard(session, fBoard);
		return result;
	}

	@Override
	public int registerComment(FinishComment fComment) {
		int result=fStore.insertComment(session, fComment);
		return result;
	}

	@Override
	public int modifyBoard(FinishBoard fBoard) {
		int result=fStore.updateBoard(session, fBoard);
		return result;
	}

	@Override
	public int modifyComment(Map<String, Object> inputMap) {
		int result=fStore.updateComment(session, inputMap);
		return result;
	}

	@Override
	public int usePoint(String userId, String point) {
		int result=fStore.updateUserPoint(session, userId, point);
		return result;
	}

	// 게시글 추천/비추천
	@Override
	public int addUpDownCount(Integer fBoardNo, String userId, String upOrDown) {
		int result=fStore.insertUpDownCount(session, fBoardNo, userId, upOrDown);
		return result;
	}

	// 댓글 삭제
	@Override
	public int removeComment(Integer fCommentNo) {
		int result=fStore.deleteComment(session, fCommentNo);
		return result;
	}

	@Override
	public int getTotalCount(String searchOption, String searchValue) {
		int totalCount=fStore.selectTotalCount(session, searchOption, searchValue);
		return totalCount;
	}

	@Override
	public int getCountUp(int fBoardNo) {
		int count=fStore.selectCountUp(session, fBoardNo);
		return count;
	}

	@Override
	public int getCountDown(int fBoardNo) {
		int count=fStore.selectCountDown(session, fBoardNo);
		return count;
	}

	@Override
	public int getUserRecordUpCount(String userId, int fBoardNo) {
		int result=fStore.selectUserRecordUpDown(session, userId, fBoardNo);
		return result;
	}

	@Override
	public FinishBoard printOneByNo(int fBoardNo) {
		FinishBoard fBoard=fStore.selectOneByNo(session, fBoardNo);
		if(fBoard!=null) {
			fStore.updateBoardCount(session, fBoardNo);
		}
		return fBoard;
	}

	@Override
	public List<FinishBoard> printAllBoard(int currentPage, int boardLimit) {
		List<FinishBoard> fList=fStore.selectAllBoard(session, currentPage, boardLimit);
		return fList;
	}

	@Override
	public List<FinishBoard> printAllByValue(String searchOption, String searchValue, int currentPage, int boardLimit) {
		List<FinishBoard> fList=fStore.selectAllByValue(session, searchOption, searchValue, currentPage, boardLimit);
		return fList;
	}

	@Override
	public List<FinishComment> printAllComment(int fBoardNo) {
		List<FinishComment> cList=fStore.selectAllComment(session, fBoardNo);
		return cList;
	}
}

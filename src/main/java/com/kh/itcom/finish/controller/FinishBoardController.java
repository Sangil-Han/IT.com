package com.kh.itcom.finish.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.itcom.finish.domain.FinishBoard;
import com.kh.itcom.finish.domain.FinishComment;
import com.kh.itcom.finish.service.FinishBoardService;
import com.kh.itcom.user.domain.User;

@Controller
public class FinishBoardController {

	@Autowired
	private FinishBoardService fService;

	// 글쓰기 폼으로 이동
	@RequestMapping(value = "/finish/registerView.do", method = RequestMethod.GET)
	public String registerView() {

		return "finish/finishRegisterForm";
	}

	// 글쓰기
	@RequestMapping(value = "/finish/register.do", method = RequestMethod.POST)
	public ModelAndView registerBoard(ModelAndView mv, @ModelAttribute FinishBoard fBoard, HttpServletRequest request,
			@RequestParam(value = "uploadFile", required = false) MultipartFile uploadFile) {
		try {
			String fBoardFilename = uploadFile.getOriginalFilename();
			if (!uploadFile.getOriginalFilename().equals("")) {
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savePath = root + "\\fuploadFiles";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String fBoardFileRename = sdf.format(new Date(System.currentTimeMillis())) + "."
						+ fBoardFilename.substring(fBoardFilename.lastIndexOf(".") + 1);
				// 1.png, img.png
				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdir();
				}
				uploadFile.transferTo(new File(savePath + "\\" + fBoardFileRename));
				// 파일을 buploadFile 경로에 저장하는 메소드
				String boardFilepath = savePath + "\\" + fBoardFileRename;
				fBoard.setfBoardFileName(fBoardFilename);
				fBoard.setfBoardFileRename(fBoardFileRename);
				fBoard.setfBoardFilePath(boardFilepath);
			}
			int result = fService.registerBoard(fBoard);
			if (result > 0) {
				mv.setViewName("redirect:/finish/listView.do");

			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// 게시글 목록
	@RequestMapping(value = "/finish/listView.do", method = RequestMethod.GET)
	public ModelAndView listView(ModelAndView mv, @RequestParam(value = "page", required = false) Integer page) {
		// 페이징 처리
		int currentPage = (page != null) ? page : 1;
		int totalCount = fService.getTotalCount("", ""); // 총게시물수
		int boardLimit = 10; // 한 페이지의 게시물수
		int naviLimit = 5; // 한 번에 보여지는 페이지 수
		int maxPage; // 최대 페이지 넘버
		int startNavi; // 한 번에 보여지는 페이지에서 시작 넘버 1, 6, 11 등..
		int endNavi; // 한 번에 보여지는 페이지에서 마지막 넘버 5, 10, 15 등..
		maxPage = (int) ((double) totalCount / boardLimit + 0.9);
		startNavi = ((int) ((double) currentPage / naviLimit + 0.9) - 1) * naviLimit + 1;
		endNavi = startNavi + naviLimit - 1;
		if (maxPage < endNavi) {
			endNavi = maxPage;
		}

		List<FinishBoard> fList = fService.printAllBoard(currentPage, boardLimit);
		if (!fList.isEmpty()) {
			mv.addObject("urlVal", "listView");
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("maxPage", maxPage);
			mv.addObject("fList", fList);
			mv.addObject("currentPage", currentPage);
		}
		mv.setViewName("finish/finishListView");
		return mv;
	}

	// 게시글 검색
	@RequestMapping(value = "/finish/search.do", method = RequestMethod.GET)
	public ModelAndView boardSearchList(ModelAndView mv, @RequestParam("searchValue") String searchValue,
			@RequestParam("searchOption") String searchOption,
			@RequestParam(value = "page", required = false) Integer page) {
		try {
			int currentPage = (page != null) ? page : 1;
			int totalCount = fService.getTotalCount(searchOption, searchValue); // 총게시물수
			int boardLimit = 10; // 한 페이지의 게시물수
			int naviLimit = 5; // 한 번에 보여지는 페이지 수
			int maxPage; // 최대 페이지 넘버
			int startNavi; // 한 번에 보여지는 페이지에서 시작 넘버 1, 6, 11 등..
			int endNavi; // 한 번에 보여지는 페이지에서 마지막 넘버 5, 10, 15 등..
			maxPage = (int) ((double) totalCount / boardLimit + 0.9);
			startNavi = ((int) ((double) currentPage / naviLimit + 0.9) - 1) * naviLimit + 1;
			endNavi = startNavi + naviLimit - 1;
			if (maxPage < endNavi) {
				endNavi = maxPage;
			}
			List<FinishBoard> fList = fService.printAllByValue(searchOption, searchValue, currentPage, boardLimit);
			if (!fList.isEmpty()) {
				mv.addObject("fList", fList);
			} else {
				mv.addObject("fList", null);
			}
			mv.addObject("urlVal", "search");
			mv.addObject("searchOption", searchOption);
			mv.addObject("searchValue", searchValue);
			mv.addObject("startNavi", startNavi);
			mv.addObject("endNavi", endNavi);
			mv.addObject("maxPage", maxPage);
			mv.addObject("currentPage", currentPage);
			mv.setViewName("finish/finishListView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}

	// 게시글 상세 페이지
	@RequestMapping(value = "/finish/detailView.do", method = RequestMethod.GET)
	public ModelAndView boardDetailView(ModelAndView mv, @RequestParam("fBoardNo") int fBoardNo,
			@RequestParam("page") Integer page, @RequestParam(value = "point") String point,
			HttpSession session) {
		try {
			FinishBoard fBoard = fService.printOneByNo(fBoardNo);
			int upCount=fService.getCountUp(fBoardNo);
			int downCount=fService.getCountDown(fBoardNo);
			String userId=((User) session.getAttribute("loginUser")).getUserId();
			String isRecomm="";
			if(fService.getUserRecordUpCount(userId, fBoardNo)>0) {
				isRecomm="Y";
			}
			else {
				isRecomm="N";
			}
			List<FinishComment> cList = fService.printAllComment(fBoardNo);
			fService.usePoint(userId, point);
			session.setAttribute("boardNo", fBoard.getfBoardNo());
			mv.addObject("fBoard", fBoard);
			mv.addObject("cList", cList);
			mv.addObject("page", page);
			mv.addObject("upCount", upCount);
			mv.addObject("downCount", downCount);
			mv.addObject("isRecomm", isRecomm);
			mv.setViewName("finish/finishDetailView");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// 게시글 수정 폼으로 이동
	@RequestMapping(value = "/finish/modifyView.do", method = RequestMethod.GET)
	public ModelAndView modifyView(ModelAndView mv, @RequestParam("fBoardNo") Integer fBoardNo,
			@RequestParam("page") Integer page) {
		try {
			FinishBoard fBoard = fService.printOneByNo(fBoardNo);
			mv.addObject("fBoard", fBoard);
			mv.addObject("page", page);
			mv.setViewName("finish/finishModifyForm");
		} catch (Exception e) {
			mv.addObject("msg", e.toString());
			mv.setViewName("common/errorPage");
		}
		return mv;
	}

	// 게시글 수정
	@RequestMapping(value = "/finish/modify.do", method = RequestMethod.POST)
	public ModelAndView boardModify(ModelAndView mv, @ModelAttribute FinishBoard fBoard,
			@RequestParam("page") Integer page,
			@RequestParam(value = "reloadFile", required = false) MultipartFile reloadFile,
			HttpServletRequest request) {
		try {
			String fBoardFileName = reloadFile.getOriginalFilename();
			// 파일을 첨부했을 때
			if (reloadFile != null && !fBoardFileName.equals("")) {
				String root = request.getSession().getServletContext().getRealPath("resources");
				String savedPath = root + "\\fuploadFiles";
				// 파일이 이미 존재하면 삭제
				File file = new File(savedPath + "\\" + fBoard.getfBoardFileRename());
				if (file.exists()) {
					file.delete();
				}

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
				String fBoardFileRename = sdf.format(new Date(System.currentTimeMillis())) + "."
						+ fBoardFileName.substring(fBoardFileName.lastIndexOf(".") + 1);
				String fBoardFilePath = savedPath + "\\" + fBoardFileRename;
				reloadFile.transferTo(new File(fBoardFilePath));
				fBoard.setfBoardFileName(fBoardFileName);
				fBoard.setfBoardFileRename(fBoardFileRename);
				fBoard.setfBoardFilePath(fBoardFilePath);
			}
			int result = fService.modifyBoard(fBoard);

			if (result > 0) {
				mv.setViewName("redirect:/finish/listView.do?page=" + page);
			}
		} catch (Exception e) {
			mv.addObject("msg", e.toString()).setViewName("common/errorPage");
		}
		return mv;
	}

	@RequestMapping(value = "/finish/addComment.do", method = RequestMethod.POST)
	public ModelAndView addComment(ModelAndView mv, @ModelAttribute FinishComment fComment, HttpSession session,
			@RequestParam("page") int page) {

		User user = (User) session.getAttribute("loginUser");
		String commentUserId = user.getUserId();
		int fBoardNo = fComment.getfBoardNo();
		fComment.setUserId(commentUserId);

		// 댓글 작성 날짜 및 시간 String으로 변환
		Date date = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
		String fCommentRegtime = transFormat.format(date);

		fComment.setfCommentRegtime(fCommentRegtime);

		int result = fService.registerComment(fComment);

		if (result > 0) {
			mv.setViewName("redirect:/finish/detailView.do?fBoardNo=" + fBoardNo + "&page=" + page + "&point=0");
		}
		return mv;
	}

	@RequestMapping(value = "/finish/removeComment.do", method = RequestMethod.GET)
	public String removeComment(@RequestParam("fCommentNo") Integer fCommentNo,
			@RequestParam("fBoardNo") Integer fBoardNo, @RequestParam("page") Integer page) {
		int result = fService.removeComment(fCommentNo);
		if (result > 0) {
			return "redirect:/finish/detailView.do?fBoardNo=" + fBoardNo + "&page=" + page + "&point=0";
		} else {
			return "common/errorPage";
		}
	}

	@RequestMapping(value = "/finish/addUpCount.do", method = RequestMethod.POST)
	public String addUpCount(@RequestParam("fBoardNo") Integer fBoardNo, @RequestParam("userId") String userId,
			@RequestParam("page") Integer page) {
			int result = fService.addUpDownCount(fBoardNo, userId, "UP");
			if (result > 0) {
				return "redirect:/finish/detailView.do?fBoardNo=" + fBoardNo+"&page="+page+"&point=0";
			}
			else {
				return "common.errorPage";
			}
	}
	@RequestMapping(value="/finish/addDownCount.do", method=RequestMethod.POST)
	public String addDownCount(@RequestParam("fBoardNo") Integer fBoardNo, @RequestParam("userId") String userId,
			@RequestParam("page") Integer page) {
			int result = fService.addUpDownCount(fBoardNo, userId, "DOWN");
			if (result > 0) {
				return "redirect:/finish/detailView.do?fBoardNo=" + fBoardNo+"&page="+page+"&point=0";
			}
			else {
				return "common.errorPage";
			}
	}
	
	@ResponseBody
	@RequestMapping(value="/finish/modifyComment.do", method=RequestMethod.POST)
	public Map<String, Object> modifyComment(@RequestBody Map<String, Object> inputMap) {
		
		String modifiedComment=(String) inputMap.get("commentText");
		String fBoardNo=(String) inputMap.get("fCommentNo");
		
		fService.modifyComment(inputMap);
		
		Map<String, Object> returnMap=new HashMap<>();
		returnMap.put("modifiedComment", inputMap.get("commentText"));
		
		return returnMap;
	}
}
